package com.sachin.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;
import com.sachin.model.Customer;
import com.sachin.model.User;
import com.sachin.service.CustomerPDF;
import com.sachin.service.CustomerService;
import com.sachin.service.UserLoginService;

import jakarta.servlet.ServletException;

@RestController
public class HomeController 
{
	@Autowired
	UserLoginService loginservice;
	
	@Autowired
	CustomerService customerservice;
	
	int pageno = 0;
	String keywords = "";
	String sortingColumns = "";
	String advanceSearch = "";
	int startPage = 0;
	int endPage = 0;

	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//         http
//         .authorizeRequests()
//         .antMatchers("/CustomerDetailsSpringMVC/**").permitAll()
//         .anyRequest().authenticated()
//         .and()
//         .formLogin()
//         .loginPage("/login")
//         .permitAll();
//    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, javax.servlet.ServletException 
	{
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("user", new User());

	    return mav;
	  }

	
	  //@RequestMapping(value = "/loginProcess", method = RequestMethod.GET)
	  @PostMapping(value ="/loginProcess")
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("user") User user) throws IOException {
	    ModelAndView mav = null;
	    	System.out.println(request.getHeaders("username"));
	    	
	    		System.out.println("inside if");
	    		Map<String, String> map = new HashMap<String, String>();
	        Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            String value = request.getHeader(key);
	            
	            map.put(key, value);
	        }
	        if((map.get("username")!=null) && (map.get("password")!=null))
	        {
	        user.setUsername(map.get("username"));
	        user.setPassword(map.get("password"));
	        }
	    	
//	        System.out.println(request.toString());
//	        
//	    HttpSession session = request.getSession(false);
//	   
//	    if(session!=null)
//	    {
//	    	 session.setAttribute("user", user);
//		 	    user = (User) session.getAttribute("user");
	    
	        
	    if (loginservice.validatingLogin(user)) {
	    	
	    	HttpSession session = request.getSession();
	    	session.setAttribute("user", user);
	    	
	    	
	    	
//	    	response.sendRedirect("userlogin");
	    	
	    mav = new ModelAndView("userlogin");
	    mav.addObject("username", user.getUsername());
	    
    	//if(users==null)
	    
	    }
	    else
	    {
	    	mav = new ModelAndView("login");
		    mav.addObject("message", "Username or Password is wrong!!");
	    }
//	    }
//	    else if(user==null){
//	  
//	    	response.sendRedirect("login.jsp");
////	    mav = new ModelAndView("login");
////	    mav.addObject("message", "Please login again!!");
//	    	
//	    }
//	    else
//	    {
//	    	response.sendRedirect("login.jsp");
//
////	    	mav = new ModelAndView("login");
////		    mav.addObject("message", "Username or Password is wrong!!");
//	    }

	    return mav;
	  }
	
	
	  @RequestMapping(value = "/customerdetails/{pageid}", method = RequestMethod.GET)
	  public ModelAndView customerDetails(HttpServletRequest request, HttpServletResponse response , @PathVariable int pageid 
	  ) {
	    ModelAndView mav = null;

	    pageno = pageid;
	    
	    
	    
	    List<Customer> result = customerservice.fetchCustomerDetails(pageno);
	    
	    
	    //startPage = (int) (pageid - 5 > 0?pageid - 5:1);
	    startPage = 1;
	    endPage = customerservice.pagecondition();

	    	mav = new ModelAndView("customerdetails");
		    mav.addObject("details", result);
		    mav.addObject("startpage", startPage);
		    mav.addObject("endpage", endPage);
		    
	   

	    return mav;
	  }
	  
	  
	
	  
	  @RequestMapping(value = "/customereditdetails/{id}", method = RequestMethod.GET)
	  public ModelAndView customerEditDetails( @PathVariable int id
			  )
			    
	{
		  System.out.println(id);
		 
		 Customer customer = customerservice.getCustomerById(id);
		  
		  
	    ModelAndView mav = new ModelAndView("editcustomerdetails");
	    mav.addObject("customer", customer);
	    

	    return mav;
	  }
	  
	  @RequestMapping(value ="/saveeditdetail", method = RequestMethod.GET)
	  public ModelAndView saveEditDetails( HttpServletRequest request, HttpServletResponse response ,
	  @ModelAttribute("customer") Customer customer) {
	    ModelAndView mav = null;

	    Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
       
            String value = request.getHeader(key);
          
            map.put(key, value);
            
        
        }
        if((map.get("id")!=null) && (map.get("name")!=null) && (map.get("age")!=null) && (map.get("gender")!=null))
        {
        	String a =map.get("id");
        	int id=Integer.parseInt(a);
        	
        	String b =map.get("age");
        	int age=Integer.parseInt(b);
        
        customer.setId(id);
        customer.setName(map.get("name"));
        customer.setAge(age);
        customer.setGender(map.get("gender"));
        
        }
        
        
//        if((map.get("id")!=null) && (map.get("age")!=null))
//        {
//        	
//        	String a =map.get("id");
//        	int id=Integer.parseInt(a);
//        	
//        	String b =map.get("age");
//        	int age=Integer.parseInt(b);
//        	customer.setId(id);
//        	customer.setAge(age);
//        }
	    
	    
	    customerservice.saveDetails(customer);
	    
    
	    List<Customer> result = customerservice.fetchCustomerDetails(1);
	    
	    
	    mav = new ModelAndView("customerdetails");
	    mav.addObject("details", result);

	    return mav;
	  }
	  
	  @RequestMapping(value = "/customerdeletedetails/{id}", method = RequestMethod.GET)
	  public ModelAndView customerDeleteDetails( @PathVariable int id,@ModelAttribute("customer") Customer customer)
			   
	{
		  ModelAndView mav = null;
		  System.out.println(id);
		  Customer cust = new Customer();
		  cust.setId(id);
		  
		  customerservice.deleteDetails(customer);
		    
		    
		    List<Customer> result = customerservice.fetchCustomerDetails(1);
		    
		    
		    mav = new ModelAndView("customerdetails");
		    mav.addObject("details", result);

	    return mav;
	  }
	  
	  
	  
	  @RequestMapping(value ="/saveadddetail", method = RequestMethod.GET)
	  public ModelAndView saveAddDetails( HttpServletRequest request, HttpServletResponse response ,
	  @ModelAttribute("customer") Customer customer) {
	    ModelAndView mav = null;

	    Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
       
            String value = request.getHeader(key);
          
            
            map.put(key, value);
            
        
        }
        if((map.get("name")!=null) && (map.get("age")!=null) && (map.get("gender")!=null))
        {
        	
        	
        	String b =map.get("age");
        	int age=Integer.parseInt(b);
        
        customer.setName(map.get("name"));
        customer.setAge(age);
        customer.setGender(map.get("gender"));
        
        }
        
        
//        if((map.get("id")!=null) && (map.get("age")!=null))
//        {
//        	
//        	String a =map.get("id");
//        	int id=Integer.parseInt(a);
//        	
//        	String b =map.get("age");
//        	int age=Integer.parseInt(b);
//        	customer.setId(id);
//        	customer.setAge(age);
//        }
	    
	    
	    customerservice.saveDetails(customer);
	    
    
	    List<Customer> result = customerservice.fetchCustomerDetails(1);
	    
	    
	    mav = new ModelAndView("customerdetails");
	    mav.addObject("details", result);

	    return mav;
	  }
	
	  
	  @RequestMapping(value = "/adddetail", method = RequestMethod.GET)
	  public ModelAndView addDetails( 
			   ) 
	{
	
		  Customer cust = new Customer();
		 
		  
		  
		  
	    ModelAndView mav = new ModelAndView("addcustomerdetails");
	    mav.addObject("customer", cust);
	    

	    return mav;
	  }
	  
	  
	  @RequestMapping("/customerreport/{pageid}")
	    public void exportToPDF(HttpServletResponse response, @PathVariable("pageid") int pageid) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String Key = "Content-Disposition";
	        String Value = "attachment; filename=customer_" + currentDateTime + ".pdf";
	        response.setHeader(Key, Value);
	         
	        List<Customer> listCustomer = customerservice.fetchCustomerReport(pageid,keywords,sortingColumns,advanceSearch);
	         
	        CustomerPDF exporter = new CustomerPDF(listCustomer);
	        exporter.export(response);
	         
	    }
	  
	 
	  
	  
	  @RequestMapping(value ="/filterdetail/{pageid}", method = RequestMethod.GET)
	  public ModelAndView filterCustomerDetail( HttpServletRequest request, HttpServletResponse response,@PathVariable int pageid, String keyword   
	  ) {
	    ModelAndView mav = null;
	    
	   
	    Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            System.out.println(key);
            String value = request.getHeader(key);
            System.out.println(value);
            
            map.put(key, value);
        }
        if((map.get("keyword")!=null) )
        {
        	if((map.get("keyword")!=""))
        	{
        	keyword = map.get("keyword");
        	}
        }
	    

        if((keyword !=null))
        {
	    keywords = keyword;
        }
	 
	    List<Customer> result = customerservice.filterCustomer(keywords,pageid);
	    
	    
	    
	    startPage = 1;
	    endPage = customerservice.pagecondition();
	    
	    
	    mav = new ModelAndView("filterdetails");
	    mav.addObject("details", result);
	    mav.addObject("keyword", keywords);
	    mav.addObject("startpage", startPage);
	    mav.addObject("endpage", endPage);

	    return mav;
	  }
	  
	  
	  //@RequestMapping(value ="/filterdetails/{pageid}", method = RequestMethod.GET)
//	  @GetMapping(value ="/filterdetails/{pageid}")
//	  public ModelAndView filterCustomerDetails( HttpServletRequest request, HttpServletResponse response,@PathVariable int pageid   
//	  ) {
//	    ModelAndView mav = null;
//	    
//	   
//	    
//	    System.out.println(keywords);
//	    
//	    List<Customer> result = customerservice.filterCustomer(keywords,pageid);
//
//	    
//	    
//	    mav = new ModelAndView("filterdetails");
//	    mav.addObject("details", result);
//	    mav.addObject("keyword", keywords);
//
//	    return mav;
//	  }
	  
	  
	  @RequestMapping(value ="/advancefilterdetail/{advancesearch}", method = RequestMethod.GET)
	  public ModelAndView AdvancefilterCustomerDetail( HttpServletRequest request, HttpServletResponse response, @PathVariable String advancesearch   
	  ) {
	    ModelAndView mav = null;
	    
	    advanceSearch = advancesearch;

	    System.out.println(advancesearch);
	    
	    mav = new ModelAndView("advancefilterdetails");
	    return mav;
	  }
	  
	  
	  @RequestMapping(value ="/advancefilterdetails/{pageid}", method = RequestMethod.GET)
	  public ModelAndView advanceFilterCustomerDetail( HttpServletRequest request, HttpServletResponse response,@PathVariable int pageid, String keyword   
	  ) {
	    ModelAndView mav = null;
	    
	   
	    Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            System.out.println(key);
            String value = request.getHeader(key);
            System.out.println(key);
            
            map.put(key, value);
        }
        if((map.get("keyword")!=null) )
        {
        	if((map.get("keyword")!=""))
        	{
        	keyword = map.get("keyword");
        	}
        }
	    

        if((keyword !=null))
        {
	    keywords = keyword;
        }
	    
	    System.out.println(keyword);
	    
	    List<Customer> result = customerservice.advanceFilterCustomer(advanceSearch,keywords,pageid);

	    startPage = 1;
	    endPage = customerservice.pagecondition();
	    
	    mav = new ModelAndView("advancefilterdetails");
	    mav.addObject("details", result);
	    mav.addObject("keyword", keyword);
	    mav.addObject("startpage", startPage);
	    mav.addObject("endpage", endPage);

	    return mav;
	  }
	  
	  
	  @RequestMapping(value = "/sort/{sortingcolumn}/{pageid}", method = RequestMethod.GET)
	  public ModelAndView sortByDetail(HttpServletRequest request, HttpServletResponse response ,@PathVariable int pageid , 
			  @PathVariable String sortingcolumn 
			  
	  ) {
	    ModelAndView mav = null;

	    sortingColumns = sortingcolumn;
	    
	    List<Customer> result = customerservice.sortByDetails(sortingColumns,pageid);
	    
	    startPage = 1;
	    endPage = customerservice.pagecondition();

	    	mav = new ModelAndView("sortdetails");
		    mav.addObject("details", result);
		    mav.addObject("sortingColumns", sortingColumns);
		    mav.addObject("startpage", startPage);
		    mav.addObject("endpage", endPage);
	   

	    return mav;
	  }


	
	  
	  
//	  @RequestMapping(value = "/sortdetails/{pageid}", method = RequestMethod.GET)
//	  public ModelAndView sortDetails(HttpServletRequest request, HttpServletResponse response ,@PathVariable int pageid  
//			   
//			  
//	  ) {
//	    ModelAndView mav = null;
//
//	    System.out.println(sortingColumns);
//	    
//	    
//	    List<Customer> result = customerservice.sortByDetails(sortingColumns,pageid);
//
//	    	mav = new ModelAndView("sortdetails");
//		    mav.addObject("details", result);
//	   
//
//	    return mav;
//	  }
	  
}
