package com.it.tag;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.it.dao.UserDao;
import com.it.dao.UserDaoImpl;
import com.it.entity.User;

/**
 * 
 * @author Administrator 
 *
 */
public class UserListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		User user=(User)event.getSession().getAttribute("user");
		event.getSession().removeAttribute("houseList");
		user.setUlogin(0);
		UserDao userDao=new UserDaoImpl();
		userDao.update(user);
	}

}
