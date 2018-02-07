package com.it.userservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.it.dao.UserDao;
import com.it.dao.UserDaoImpl;
import com.it.entity.User;

public class AddUser extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddUser() {
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

		doPost(request, response);
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
		String type = request.getParameter("type");
		System.out.println("---type:---:"+type);
		if(type!=null&&type.equals("updateUser")){
			this.updateUser(request,response);	//更新用户
			
		}else if(type!=null&&type.equals("updatePwd")){
			this.updatePwd(request,response);	//更新用户密码
		}else if(type!=null&&type.equals("listUser")){
			this.listUser(request,response);	//用户列表
		}else if(type!=null&&type.equals("deleteUser")){
			this.deleteUser(request,response);//删除用户
			
		}else if(type!=null&&type.equals("valiName")){//验证用户名是否存在
			this.valiName(request,response);
			
		}
		else{		
			String uname=request.getParameter("userName");
			String pwd=request.getParameter("password");
			String email= request.getParameter("email");
			String mobile=request.getParameter("mobile");
			User user=new User();
			user.setUname(uname);
			user.setUpwd(pwd);;
			user.setEmail(email);
			user.setMobile(mobile);
			user.setUlogin(0);
			user.setStatus(1);
			UserDao userDao=new UserDaoImpl();
			int count=userDao.add(user);
			if(count>0){
				System.out.println("adduser---:");
				request.setAttribute("Msg", "注册成功！");
				request.getRequestDispatcher("index.jsp").forward(request, response);;
			}else{
				request.setAttribute("Msg", "注册失败！");
				request.getRequestDispatcher("register.jsp").forward(request, response);	
			}
		}
		
	}
	

	/**
	 * 验证用户名是否已经存在
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void valiName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		
		UserDao userDao=new UserDaoImpl();
		int count =userDao.findByName(username);
		PrintWriter writer = response.getWriter();
		JSONObject json=new JSONObject();
		
		
		if(count==0){
			json.fromObject("true");
			writer.print(json.toString());
			
		}else{
			json.fromObject("false");
			writer.print(json.toString());
		}
		
		
		
	}

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String idstr=request.getParameter("id");
		Integer id=Integer.parseInt(String.valueOf(idstr));
		UserDao userDao=new UserDaoImpl();
		userDao.delete(id);
		response.sendRedirect("manage/adminCms.jsp");		
	}

	/**
	 * 上午11:02:04
	 * 查询所有用户
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void listUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String strPage = request.getParameter("pageCurrent");		
		UserDao userDao=new UserDaoImpl();		
		int totalPage=userDao.totalPage(5);
		int pac=0;
		if(strPage==null){
			pac=1;
		}else{
			pac=Integer.parseInt(strPage);
			if(pac>totalPage){
				pac=1;
			}else if(pac<1){
				pac=totalPage;
			}		
		}
		
		List<User> userPageList =userDao.listByPage(pac, 5);		
		request.getSession().setAttribute("Msg", request.getAttribute("Msg"));		
		request.getSession().setAttribute("userList", userPageList);
		request.getSession().setAttribute("pageCurrent",pac);
		request.getSession().setAttribute("totalPage",totalPage);
		request.getSession().setAttribute("totalRow",userDao.getCount());
		System.out.println("listUser:"+strPage);
		response.sendRedirect("manage/adminUser.jsp");	
		
	}

	private void updatePwd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user=((User)request.getSession().getAttribute("user"));
		String pwd=request.getParameter("password");		
		user.setUpwd(pwd);
		user.setStatus(1);user.setUlogin(1);
		UserDao userDao=new UserDaoImpl();
		userDao.update(user);
		response.sendRedirect("manage/cms.jsp");		
	}

	/**
	 * 下午6:25:57
	 * 更新用户
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int uid=((User)request.getSession().getAttribute("user")).getUid();
		String uname=request.getParameter("userName");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String pwd=request.getParameter("password");
		User user=new User();
		user.setUid(uid);
		user.setUname(uname);
		user.setUpwd(pwd);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setStatus(1);user.setUlogin(1);
		UserDao userDao =new UserDaoImpl();
		int update = userDao.update(user);
		response.sendRedirect("manage/cms.jsp");		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
	}

}
