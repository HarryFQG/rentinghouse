package com.it.serviceSundry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dsna.util.images.ValidateCode;

public class ValiCode extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//参数详解：图像宽度	图像高度	数字格式	干扰线
		ValidateCode  code=new ValidateCode(100, 40, 4, 15);
		String  valicode = code.getCode();//获取验证码
		//输出浏览器
		request.getSession().setAttribute("valicode", valicode);
		code.write(response.getOutputStream());	
	}
	
}
