package com.sachin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.dao.CustomerDAO;
import com.sachin.model.Customer;
import com.sachin.model.User;

@Service
public class CustomerService 
{
	@Autowired
	CustomerDAO customerdao;
	
	
	@Transactional
	public List<Customer> fetchCustomerDetails(int pageid)
	{
		int total=4;    
        if(pageid==1){}    
        else{    
            pageid=(pageid-1)*total+1;    
        }    
        System.out.println(pageid);  
		
		
		List<Customer> result = customerdao.getCustomerDetails(pageid,total);
	
	
		return result;
	}
	
	@Transactional
	public Customer saveDetails(Customer customer)
	{
		Customer cust = customerdao.updateDetails(customer);
		
		return cust;
	}
	
	@Transactional
	public Customer getCustomerById(int id)
	{
		Customer cust = customerdao.getCustomerById(id);
		
		return cust;
	}

	@Transactional
	public Customer deleteDetails(Customer customer)
	{
		Customer cust = customerdao.updateDeleteDetails(customer);
		
		return cust;
	}
	
	
	
	@Transactional
	public List<Customer> fetchCustomerReport(int pageid,String keywords,String sortingColumns,String advanceSearch )
	{
		 
		
		
		List<Customer> result = customerdao.getCustomerReport(pageid,keywords,sortingColumns,advanceSearch);
		
	
		return result;
	}
	
	
	
	
	
	
	
	@Transactional
	public List<Customer> filterCustomer(String keywords,int pageid)
	{
		System.out.println(pageid);
		int total=4;    
        if(pageid==1){}    
        else{    
        	pageid=(pageid-1)*total+1;    
        }  
		
		
		List<Customer> result = customerdao.filterList(keywords,pageid,total);
		return result;
		
		
		
	
		
	}
	
	
	@Transactional
	public List<Customer> advanceFilterCustomer(String advanceSearch,String keywords,int pageid)
	{
		System.out.println(pageid);
		int total=4;    
        if(pageid==1){}    
        else{    
        	pageid=(pageid-1)*total+1;    
        }  
		
		
		List<Customer> result = customerdao.advanceFilterList(advanceSearch,keywords,pageid,total);
		return result;
		
		
		
	
		
	}
	
	@Transactional
	public List<Customer> sortByDetails(String sortingcolumn,int pageid)
	{
		 
		System.out.println(pageid);
		int total=4;    
        if(pageid==1){}    
        else{    
        	pageid=(pageid-1)*total+1;    
        }  
		
		List<Customer> result = customerdao.getByDetails(sortingcolumn,pageid,total);
		
	
		
		return result;
	}
	
	public int pagecondition()
	{
		int number = 0;
		
		if((CustomerDAO.size % 4) !=0 )
		{
			number = (CustomerDAO.size / 4) + 1;
		}
		else
		{
			number = (CustomerDAO.size / 4);
		}
		
		return number;
	}
	
	
	
}
