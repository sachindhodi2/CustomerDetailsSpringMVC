package com.sachin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;

public class CommonServlet extends HttpServlet
{
	protected void validate(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException, javax.servlet.ServletException
	{
		if(request.getSession().getAttribute("user")==null)
		{
			request.setAttribute("err","You haven't logged in to the system");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
