package com.it.userservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.it.dao.CommentDao;
import com.it.dao.CommentDaoImpl;
import com.it.dao.UserDao;
import com.it.dao.UserDaoImpl;
import com.it.entity.Comment;
import com.it.entity.User;

public class LetterList extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String type=request.getParameter("type5");
		if(type.equals("letterList")){				//�鿴�����б�
			this.letterList(request,response);
		}else if(type.equals("letterDetail")){		//�鿴��������
			this.letterDetail(request,response);
		}else if(type.equals("addLetter")){//�������
			this.addLetter(request,response);
		}
		
		
	}

	/**�������
	 * 2017��8��18������6:07:22 
	 * @param request
	 * @param response
	 * @throws IOException 
	 *
	 */
	private void addLetter(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer fangDongId=Integer.parseInt(request.getParameter("fangDongId"));
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		String str=request.getParameter("sendContent");
		Date date=new Date();
		
		UserDao userDao=new UserDaoImpl();
		User fangDong = userDao.findById(fangDongId);
		User user=userDao.findById(userId);
		
		
		Comment comment=new Comment();
		comment.setCcid(userId);//���ͷ�id
		comment.setCrid(fangDongId);//���ܷ�Id
		comment.setCcontent(str);
		comment.setCcreateTime(date);
		comment.setCcname(user.getUname());
		comment.setCrname(fangDong.getUname());
		comment.setCstatus(3);
		comment.setCreplay("");
		comment.setCreplayTmie(date);
		
		CommentDao commentDao=new CommentDaoImpl();
		if(str!=null&&str!=""){
			commentDao.add(comment);
		}List<Comment> listComments = commentDao.findById2(fangDongId,userId);
		
		
		//����JSON
		PrintWriter out = response.getWriter();
		JSONArray json=JSONArray.fromObject(listComments);
		
		System.out.println(json.toString());
		out.print(json.toString());
		
		out.flush();
		out.close();
		
		
		
		
		
		
		
		
		
	}

	/**
	 * 2017��8��18������5:04:53 
	 * @param request
	 * @param response
	 * ��������
	 * @throws IOException 
	 */
	private void letterDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		
		Integer userId =Integer.parseInt(request.getParameter("userId")) ;//�Ñ���ID
		Integer fangDongId=Integer.parseInt(request.getParameter("fangDongId"));//����Ի�������Id
		CommentDao commentDao=new CommentDaoImpl();
		
		
		List<Comment> listComment1= commentDao.findById2(fangDongId, userId);
		/*List<Comment> listComment2= commentDao.findById2( userId,fangDongId);
		listComment1.addAll(listComment2);*/
		System.out.println("------>listComment:"+listComment1);
		/*System.out.println("\n\n------>listComment2:"+listComment2);*/
		request.getSession().setAttribute("listComment", listComment1);
		request.getSession().setAttribute("fangDongId", fangDongId);
		request.getSession().setAttribute("letteruserId", userId);
		
		response.sendRedirect("manage/letter.jsp");
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * 2017��8��18������5:04:49 
	 * @param request
	 * @param response
	 * �鿴�����б�
	 * @throws IOException 
	 */
	private void letterList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Integer userId=Integer.parseInt(request.getParameter("userId"));
		CommentDao commentDao=new CommentDaoImpl();
		
		List<Comment> ruserlist = commentDao.findByIdList(userId);//��ȡ��ϵ�˵�Id
		/*List<Integer> ridList=new ArrayList<>();
		if(ruserlist!=null){
			for(User u:ruserlist){
				ridList.add(u.getUid())	;			
			}
		}*/
		
		
		System.out.println(",------,"+ruserlist);
		/*UserDao userDao=new UserDaoImpl();
		List<User> ruserList = userDao.findByUidList(ridList);
		System.out.println("-----"+ruserList);*/
		request.getSession().setAttribute("ruserList", ruserlist);
		
		
		response.sendRedirect("manage/letterList.jsp");
		
		
		
		
		
		
		
	}

}
