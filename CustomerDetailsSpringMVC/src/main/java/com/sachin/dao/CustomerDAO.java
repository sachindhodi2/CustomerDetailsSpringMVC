package com.sachin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sachin.model.Customer;
import com.sachin.model.User;

@Repository
public class CustomerDAO 
{
	public static int size;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Customer> getCustomerDetails(int pageid,int total) 
	{
		Session session = sessionFactory.openSession();
		
			Query  query = session.createQuery("from Customer");
			
			System.out.println("yoyo"+query.getResultList().size());
			size = query.getResultList().size();
					
					query.setFirstResult(pageid-1);
			
					query.setMaxResults(total);
					
		
		
		
		List<Customer> customerlist = query.getResultList();
		
		return customerlist;
	}
	
	
	public Customer updateDetails(Customer customer)
	{
	
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
		return customer;
	}
	
	
	public Customer getCustomerById(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		
		//Query  query = session.createQuery("from Customer  "+id);
		
		Customer customer =  (Customer)session.get(Customer.class, id);
		//session.saveOrUpdate(customer);
		return customer;
	}
	
	
	public Customer updateDeleteDetails(Customer customer)
	{
	
		Session session = sessionFactory.getCurrentSession();
		session.delete(customer);
		return customer;
	}
	
	
	public List<Customer> getCustomerReport(int pageid,String keywords,String sortingColumns,String advanceSearch) 
	{
		
		Session session = sessionFactory.openSession();
//		if(pageid==1)
//		{
//			Query  query = session.createQuery("from Customer");
//			List<Customer> customerlist = query.getResultList();
//			return customerlist;
//
//
//		}	
		 if(pageid==2)
		{
			Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE ?1 ");
			query.setParameter(1,"%"+keywords+"%");
			List<Customer> customerlist = query.getResultList();
			return customerlist;


		}
		 if(pageid==3)
		{
			Query  query = session.createQuery("from Customer ORDER BY "+sortingColumns);
			List<Customer> customerlist = query.getResultList();
			return customerlist;



		}
		else if(pageid==4)
		{
			
			if(advanceSearch.equals("startswith"))
			{
				Query  query = session.createQuery("from Customer where name like :name OR gender like :name OR age like :name");
				query.setParameter("name",keywords + "%");
				List<Customer> customerlist = query.list();
				return customerlist;
				
			}
			else if(advanceSearch.equals("endswith"))
			{
				Query  query = session.createQuery("from Customer where name like :name OR gender like :name OR age like :name");
				query.setParameter("name","%"+keywords);
				List<Customer> customerlist = query.list();
				return customerlist;
				
			}
			
			
			else if(advanceSearch.equals("inbetween"))
			{
				Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE ?1 ");
				query.setParameter(1,"%"+keywords+"%");
				List<Customer> customerlist = query.list();
				return customerlist;
				
			}
			
			else if(advanceSearch.equals("wordsearch"))
			{
				Query  query = session.createQuery("from Customer where name like :name OR gender like :name OR age like :name" );
				query.setParameter("name",keywords);
				List<Customer> customerlist = query.list();
				return customerlist;
				
			}
				Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE ?1 ");
				query.setParameter(1,"%"+keywords+"%");
				List<Customer> customerlist = query.list();
				return customerlist;

		}
		 
		 
		 
		 
		
		Query  query = session.createQuery("from Customer");
	
		
		List<Customer> customerlist = query.getResultList();
		
		return customerlist;
	}
	
	
	
	public List<Customer> filterList(String keywords,int pageid,int total) 
	{
		Session session = sessionFactory.openSession();
		
	
			//Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE :keyword");
		Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE ?1 ");
			
			
		
			//Query  query = session.createQuery("FROM Customer where :keyword in(id,name,age,gender)");
			
			//query.setParameter(1,keyword + "%");
			query.setParameter(1,"%"+keywords+"%");
			
			size = query.getResultList().size();
			
			query.setFirstResult(pageid-1);
			
			query.setMaxResults(total);
			
		List<Customer> customerlist = query.list();
		
		System.out.println(customerlist);
		
		return customerlist;
	}
	
	
	
	public List<Customer> advanceFilterList(String advanceSearch,String keywords,int pageid,int total) 
	{
		
		Session session = sessionFactory.openSession();
		
		System.out.println("yo yo"+advanceSearch);
		
		if(advanceSearch.equals("startswith"))
		{
			System.out.println("inside if");
			Query  query = session.createQuery("from Customer where name like :name OR gender like :name OR age like :name");
			
			
			
			query.setParameter("name",keywords + "%");
			
			size = query.getResultList().size();
			
			query.setFirstResult(pageid-1);
			
			query.setMaxResults(total);
			
			List<Customer> customerlist = query.list();
			
			System.out.println(customerlist);
			
			return customerlist;
			
		}
		else if(advanceSearch.equals("endswith"))
		{
			System.out.println("inside if");
			Query  query = session.createQuery("from Customer where name like :name OR gender like :name OR age like :name");
			query.setParameter("name","%"+keywords);
			
			size = query.getResultList().size();
			
			query.setFirstResult(pageid-1);
			
			query.setMaxResults(total);
			
			List<Customer> customerlist = query.list();
			
			System.out.println(customerlist);
			
			return customerlist;
			
		}
		
		
		else if(advanceSearch.equals("inbetween"))
		{
			System.out.println("inside if");
			Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE ?1 ");
			query.setParameter(1,"%"+keywords+"%");
			
			size = query.getResultList().size();
			
			query.setFirstResult(pageid-1);
			
			query.setMaxResults(total);
			
			List<Customer> customerlist = query.list();
			
			System.out.println(customerlist);
			
			return customerlist;
			
		}
		
		else if(advanceSearch.equals("wordsearch"))
		{
			System.out.println("inside if");
			Query  query = session.createQuery("from Customer where name like :name OR gender like :name OR age like :name" );
			query.setParameter("name",keywords);
			
			size = query.getResultList().size();
			
			query.setFirstResult(pageid-1);
			
			query.setMaxResults(total);
			
			List<Customer> customerlist = query.list();
			
			System.out.println(customerlist);
			
			return customerlist;
			
		}
	
			//Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE :keyword");
		Query  query = session.createQuery("SELECT c FROM Customer c WHERE CONCAT(c.id, ' ', c.name, ' ', c.age, ' ', c.gender) LIKE ?1 ");
			
			//Query  query = session.createQuery("FROM Customer where :keyword in(id,name,age,gender)");
			
			//query.setParameter(1,keyword + "%");
			query.setParameter(1,"%"+keywords+"%");
			
			size = query.getResultList().size();
			
			query.setFirstResult(pageid-1);
			
			query.setMaxResults(total);
			
		List<Customer> customerlist = query.list();
		
		System.out.println(customerlist);
		
		
		
		
		
		return customerlist;
	}
	
//	Query  query = session.createQuery("from Customer where name like :name");
//	
//	query.setParameter("name",keyword + "%");
	
//	Query  query = session.createQuery("FROM Customer where :keyword in(id,name,age,gender)");
//
//	query.setParameter("keyword",keyword + "%");
	
	
	
	public List<Customer> getByDetails(String sortingcolumn,int pageid,int total) 
	{
		Session session = sessionFactory.openSession();
		
			Query  query = session.createQuery("from Customer ORDER BY "+sortingcolumn);
			
			size = query.getResultList().size();
			
			//query.setParameter("sortingcolumn",sortingcolumn);
			query.setFirstResult(pageid-1);
			
			query.setMaxResults(total);		
			
			
		List<Customer> customerlist = query.getResultList();
		
		return customerlist;
	}
	
	
}
