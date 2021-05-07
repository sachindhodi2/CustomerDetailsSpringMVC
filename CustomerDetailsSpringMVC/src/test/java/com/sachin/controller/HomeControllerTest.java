package com.sachin.controller;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.InjectService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.sachin.dao.CustomerDAO;
import com.sachin.dao.UserDAO;
import com.sachin.model.Customer;
import com.sachin.model.User;
import com.sachin.service.CustomerService;
import com.sachin.service.UserLoginService;

import jakarta.servlet.ServletException;
import junit.framework.TestCase;


public class HomeControllerTest extends TestCase {

	@Autowired
	Customer customer;
	
	@Autowired
	User user;
	
	
	
//	@InjectMocks
//	HomeController controllers;
	
//	@Mock
//	CustomerService service;
	
	@Mock
	UserLoginService userLoginService;
	
	 @Mock
	 CustomerDAO dao;
	
	 
	 @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	 
//	HttpServletRequest request;
//	HttpServletResponse response ;
	
//	@Test
//	public void testSaveAddDetails() 
//	{
//		customer.setId(20);
//		customer.setName("neeraj");
//		customer.setAge(88);
//		customer.setGender("female");
//		
//		when(service.saveDetails(customer)).thenReturn(customer);
//	     ModelAndView mav = controllers.saveAddDetails(null, null, customer);
//		
//	}
	
	 @Test
	 public void testLogin() throws ServletException, IOException, javax.servlet.ServletException
	 {
		 HomeController controller = new HomeController();
		 ModelAndView modelAndView = controller.showLogin(null, null);
	        assertEquals("login", modelAndView.getViewName());
	 }
	 
	 @Test
	 public void testLoginProcess() throws IOException
	 {
		 User user = new User("sachin","dhodi");
		 UserLoginService service = new UserLoginService();
		 UserDAO dao = new UserDAO();
		 HomeController controller = new HomeController();
//		 user.setUsername("sachin");
//		 user.setPassword("dhodi");
		 
		 
		 MockHttpServletRequest request = new MockHttpServletRequest();
		 MockHttpServletResponse response = new MockHttpServletResponse();
		 
		
//		 ModelAndView modelAndView = controller.loginProcess(request, response, user);
//	        assertEquals("userlogin", modelAndView.getViewName());
//		 ModelAndView modelAndView =controller.loginProcess(request, response, user);
//		 service.validatingLogin(user);
//		 Assert.assertEquals("userlogin", modelAndView.getViewName());
		
		 request.addParameter("username", "sachin");
	        request.addParameter("password", "dhodi");        
	        ModelAndView modelAndView = controller.loginProcess(request, response, user);
	        assertEquals("userlogin", modelAndView.getViewName());
		 
		 
//		 String result = dao.validateLogin(user);
//		 assertEquals("username", result.toString());
//		 //when(dao.validateLogin(user)).thenReturn(getName());
//		 verify(dao).validateLogin(user);
//		 assertEquals(dao, getName());
		 
	 }
	 
	 
	 @Test
	 public void testSaveAddDetail()
	 {
		 Customer customer = new Customer();
		 CustomerService service = new CustomerService();
			HomeController controller = new HomeController();
			CustomerDAO dao = new CustomerDAO();
		 customer.setId(20);
		 customer.setName("ashu");
		 customer.setAge(55);
		 customer.setGender("male");
		
		 MockHttpServletRequest request = new MockHttpServletRequest();
		 MockHttpServletResponse response = new MockHttpServletResponse();
		 //Customer customer = dao.updateDetails(customers);
		// verify(dao, times(1)).updateDetails(customer);
		 when(dao.updateDetails(customer)).thenReturn(customer);
		 verify(dao).updateDetails(customer);
	        //assertEquals("customer", modelAndView.getViewName());
	 }
	 
	 public void testLoginService()
	 {
		 HomeController controller = new HomeController();
		 UserLoginService service = new UserLoginService();
		 UserDAO dao = new UserDAO();
		 user.setUsername("sachin");
		 user.setPassword("dhodi");
		 when(dao.validateLogin(user)).thenReturn(getName());
	 }
	 
	 
//		@Test
//		public void testLoginProcesss() 
//		{
//			HomeController controller = new HomeController();
//			 UserLoginService service = new UserLoginService();
//			 UserDAO dao = new UserDAO();
//			
//			//User user = new User("sachin","dhodi");
//			 User user = new User();
//			 user.setUsername("sachin");
//			 user.setPassword("dhodi");
//			 
//			//controller.loginProcess(null, null, user);
//			 MockHttpServletRequest request = new MockHttpServletRequest();
//			 MockHttpServletResponse response = new MockHttpServletResponse();
//		
//		ModelAndView modelAndView = controller.loginProcess(request, response, user);
//	        assertEquals("userlogin", modelAndView.getViewName());
//
//		 // assertNotNull(modelAndView);
//				//assertEquals(modelAndView.getViewName(), HttpStatus.OK);
//			
//			//ModelAndView modelAndView = controller.loginProcess(null, null, user);
//	        //assertEquals("userlogin", modelAndView.getViewName());
//			//service.validatingLogin(user);
//			// dao.validateLogin(user);
//			 
////			Customer customer = new Customer(19,"ashu",66,"male");
////			dao.updateDetails(customer);
//			//service.saveDetails(customer);
//			
//			//verify(controller, times(1)).loginProcess(null, null, user);
//			
//			//controller.saveAddDetails(null, null, customer);
//			
////			when(service.saveDetails(customer)).thenReturn(customer);
////		     ModelAndView mav = controller.saveAddDetails(null, null, customer);
////			
//		}
	 
		@Test
		public void testSaveAddDetails() 
		{
//			CustomerService service = new CustomerService();
			HomeController controller = new HomeController();
			CustomerDAO dao = new CustomerDAO();
			
			
			Customer customer = new Customer();
			customer.setName("ashu");
			customer.setAge(44);
			customer.setGender("male");
			
			
			//service.saveDetails(customer);
			
			assertEquals("Sucessfully added",true,dao.updateDetails(customer));	
			
			//controller.saveAddDetails(null, null, customer);
			
//			when(service.saveDetails(customer)).thenReturn(customer);
//		     ModelAndView mav = controller.saveAddDetails(null, null, customer);
//			
		}
}
