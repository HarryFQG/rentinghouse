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
		//������⣺ͼ����	ͼ��߶�	���ָ�ʽ	������
		ValidateCode  code=new ValidateCode(100, 40, 4, 15);
		String  valicode = code.getCode();//��ȡ��֤��
		//��������
		request.getSession().setAttribute("valicode", valicode);
		code.write(response.getOutputStream());	
	}
	
}
