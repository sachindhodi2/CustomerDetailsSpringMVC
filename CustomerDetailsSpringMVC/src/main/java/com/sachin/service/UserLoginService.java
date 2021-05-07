package com.sachin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sachin.dao.UserDAO;
import com.sachin.model.User;

@Service
public class UserLoginService 
{
	@Autowired
	UserDAO userdao;

	@Transactional
	public boolean validatingLogin(User user)
	{
		String result = userdao.validateLogin(user);
		if(result==null)
		{
		 return false;
		}
		if(result.equals(user.getUsername()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
