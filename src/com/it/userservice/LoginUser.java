package com.it.userservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.UserDao;
import com.it.dao.UserDaoImpl;
import com.it.entity.User;

public class LoginUser extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginUser() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type==null){
			doPost(request, response);
		}else if(type.equals("loginOut")){
			this.loginOut(request,response);
		}else {
			doPost(request, response);
			
		}
		
	}

	private void loginOut(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user =(User) request.getSession().getAttribute("user");
		user.setUlogin(0);
		request.getSession().removeAttribute("user");
		UserDao userDao=new UserDaoImpl();
		
		int count = userDao.update(user);
		response.sendRedirect("Index");
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=request.getParameter("userName");
		String pwd=request.getParameter("password");
		String code=request.getParameter("code");
		String valicode=(String) request.getSession().getAttribute("valicode"); 
		if(!code.equalsIgnoreCase(valicode)){
			request.setAttribute("Msg", "验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			UserDao  userDao=new UserDaoImpl();
			User user = userDao.findCondition(name, pwd);
			if(user!=null){
				if(user.getUlogin()==1){
					request.setAttribute("Msg", "用户已登录！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else{				
					user.setUlogin(1);
					userDao.update(user);
					request.setAttribute("Msg", "登录成功！");
					request.getSession().setAttribute("user", user);
					System.out.println("login----:"+user);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
			}else{
				request.setAttribute("Msg", "用户名不存在，或用户名和密码错误！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			
		}
		
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
