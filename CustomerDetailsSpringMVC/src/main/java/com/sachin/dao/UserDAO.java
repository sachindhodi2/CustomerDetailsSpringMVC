package com.sachin.dao;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sachin.model.User;

@Repository
public class UserDAO 
{

	@Autowired
	private SessionFactory sessionFactory;
	
	public String validateLogin(User user) 
	{
		Session session = sessionFactory.openSession();
		
		Query  query = session.createQuery("select username from User where username=:username and password=:password");
		
		query.setParameter("username",user.getUsername());
		query.setParameter("password",user.getPassword());
		String username =  (String) query.uniqueResult();
		return username;
	}
	
}
