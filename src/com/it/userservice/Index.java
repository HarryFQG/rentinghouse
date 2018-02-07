package com.it.userservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.adminservice.HouseR;
import com.it.entity.ResultModel;

public class Index extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HouseR houser=new HouseR();
		ResultModel rm = houser.indexComtomer(request);		
		response.sendRedirect("index.jsp");
		
	}

}
