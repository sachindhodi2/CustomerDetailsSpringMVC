package com.sachin.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.sachin.dao.CustomerDAO;
import com.sachin.dao.UserDAO;
import com.sachin.model.Customer;
import com.sachin.model.User;
import com.sachin.service.CustomerService;
import com.sachin.service.UserLoginService;

public class HomeControllerTest2 {

	@Test
	public void testShowLogin() {
		 HomeController controller = new HomeController();
		 ModelAndView modelAndView = controller.showLogin(null, null);
	        assertEquals("login", modelAndView.getViewName());
	}

	@Test
	public void testLoginProcess() 
	{
		User user = new User();
		 //User user = new User("sachin","dhodi");
		 UserLoginService service = new UserLoginService();
		 UserDAO dao = new UserDAO();
		 HomeController controller = new HomeController();
		 user.setUsername("sachin");
		 user.setPassword("dhodi");
	     assertEquals("sachin", user.getUsername());

		MockHttpServletRequest request = new MockHttpServletRequest();
		 MockHttpServletResponse response = new MockHttpServletResponse();
		 
		 ModelAndView modelAndView = controller.loginProcess(request, response, user);
	        assertEquals("userlogin", modelAndView.getViewName());
	}

	@Test
	public void testCustomerDetails() {
		 Customer customer = new Customer(1,"sachin",23,"male");
		 
		 CustomerService service = new CustomerService();
			HomeController controller = new HomeController();
			CustomerDAO dao = new CustomerDAO();
			
			int pageid = 1 ;
			
			int total = 4;
//			List<Customer> customers = dao.getCustomerDetails(pageid , total );
//			 
//			Assert.assertEquals(customer.getName(), customers.get(1).getName());
			
			MockHttpServletRequest request = new MockHttpServletRequest();
			 MockHttpServletResponse response = new MockHttpServletResponse();
			
			 ModelAndView modelAndView = controller.customerDetails(request, response, total);
		        assertEquals("customerdetails", modelAndView.getViewName());
	}

	@Test
	public void testCustomerEditDetails() {
		 Customer customer = new Customer();
		customer.setId(1);
		 customer.setName("sachin");
		CustomerDAO dao = new CustomerDAO();
		
//		 Integer id = new Integer(1);
//		Customer customers = dao.getCustomerById(id);
//		Assert.assertEquals(customer.getName(), customers.getName());
		
		 when(dao.updateDetails(customer)).thenReturn(customer);
		 verify(dao).updateDetails(customer);

	}

	@Test
	public void testSaveEditDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testCustomerDeleteDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDetails() 
	{
		CustomerDAO dao = new CustomerDAO();
		
		Customer customer = new Customer();
		customer.setId(19);
		customer.setName("ashu");
		customer.setAge(44);
		customer.setGender("male");
		
		
		
		assertEquals("Sucessfully added",dao.updateDetails(customer));		}

	@Test
	public void testAddDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testExportToPDF() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterCustomerDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvancefilterCustomerDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdvanceFilterCustomerDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByDetail() {
		fail("Not yet implemented");
	}

}
