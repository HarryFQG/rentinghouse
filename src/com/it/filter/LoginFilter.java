package com.it.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.entity.User;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		User user=(User)req.getSession().getAttribute("user");
		
		
			
		String path=req.getRequestURI();
		String type=req.getParameter("type");
		System.out.println("path:"+path);
		//System.out.println("loginFilter:---->>"+user+",--->,"+type);
		if(path.endsWith("duanzuwang/")){
			chain.doFilter(request, response);			
		}else if(path.indexOf("index")!=-1){
			//res.sendRedirect("Index");
			chain.doFilter(request, response);				
		}else if(path.indexOf("houseR")!=-1){//当为houseR  servlet时
			String type1 = req.getParameter("type1");
			if(type1==null){				
				chain.doFilter(request, response);				
			}else if(type1!=null&&user!=null){
				chain.doFilter(request, response);
			}else if(type1.equals("detailHouse")){
				chain.doFilter(request, response);
			}else if(type1.equals("listHouse")){
				chain.doFilter(request, response);				
			}else{
				req.getSession().setAttribute("Msg", "您还未登录，请重新登录");
				//未登录，请重新登录  
				res.sendRedirect("login.jsp");
			}
		}else if(path.indexOf("register.jsp")!=-1){
			chain.doFilter(request, response);
		}else if(path.indexOf("addUser")!=-1){//为AddUser  Servelt时
			if(type==null){
				chain.doFilter(request, response);
			}else if(type!=null&&user!=null){
				chain.doFilter(request, response);
			}else{
				req.getSession().setAttribute("Msg", "您还未登录，请重新登录");
				//未登录，请重新登录  
				res.sendRedirect("login.jsp");
			}
		}else if(path.indexOf("register")!=-1){
			chain.doFilter(request, response);
		}else if(path.indexOf("details")!=-1){
			chain.doFilter(request, response);
		}else if(path.indexOf("login")!=-1){
			chain.doFilter(request, response);
			
		}else if(path.indexOf("valiCode")!=-1){
			chain.doFilter(request, response);			
		}else if(user!=null&&path.indexOf("cms")!=-1){//去后台界面
			chain.doFilter(request, response);			
		}else if(user==null&&path.indexOf("cms")!=-1){
			req.getSession().setAttribute("Msg", "您还未登录，请重新登录");
			//未登录，请重新登录  
			res.sendRedirect(req.getContextPath()+"/login.jsp");
		}else if(user!=null){//注销用户
			chain.doFilter(request, response);		
		}else if(path.indexOf("scripts")!=-1||path.indexOf(".css")!=-1||path.indexOf("images")!=-1||path.indexOf("upload")!=-1){
			chain.doFilter(request, response);		
		}else{
			req.getSession().setAttribute("Msg", "您还未登录，请重新登录");
			//未登录，请重新登录  
			res.sendRedirect("login.jsp");
		}
		
		
		/*if(path.indexOf("index.jsp")!=-1||path.indexOf("houseR")!=-1||path.indexOf("register.jsp")!=-1||path.indexOf("addUser")!=-1||path.indexOf("details")!=-1){
			
			if(path.indexOf("index.jsp")!=-1){
				chain.doFilter(request, response);				
			}else if(path.indexOf("houseR")!=-1){
				String type1 = req.getParameter("type1");
				if(type1!=null){
					
				}
			}
			
			
			
			
			
			
			
		}else{
			
			
		};
		*/
		
		
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
