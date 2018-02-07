package com.it.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.it.entity.User;
import com.it.util.DbUtilPool;

public class UserDaoImpl implements UserDao{

	QueryRunner query=null;
	public UserDaoImpl(){
		try {
			query=new QueryRunner(DbUtilPool.getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建UserDaoImpl失败。");
		}
		
	}
	
	@Override
	public int add(User t) {
		String sql="insert into tb_user (user_name,user_pwd,user_email,user_mobile,user_status) values(?,?,?,?,?);";
		try {
			return query.update(sql, t.getUname(),t.getUpwd(),t.getEmail(),t.getMobile(),t.getStatus());
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("添加User失败！");
		}		
		
	}

	@Override
	public int delete(Serializable s) {
		String sql="delete from tb_user where user_id=?";
		try {
			return 	query.update(sql,s);
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException("删除user失败");
		}
	}

	@Override
	public int update(User t) {
		String sql ="update tb_user set user_name=?,user_pwd=?,user_email=?,user_mobile=?,user_status=?,user_login=? where user_id=?";
		try {
			return query.update(sql,t.getUname(),t.getUpwd(),t.getEmail(),t.getMobile(),t.getStatus(),t.getUlogin(),t.getUid());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新user失败");
		}
	}

	@Override
	public List<User> list() {
		String sql="select user_id as uid, user_name as uname ,user_pwd as upwd ,user_email as email,user_mobile as mobile,user_status as status,user_login as ulogin from tb_user; ";
		try {
			return query.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException("查询所有user失败！");
		}
		
	}

	@Override
	public List<User> listByPage(int currentPage, int pageSize) {
		String sql="select user_id as uid, user_name as uname ,user_pwd as upwd ,user_email as email,user_mobile as mobile,user_status as status,user_login as ulogin from tb_user limit ?,?; ";
		try {
			return query.query(sql, new BeanListHandler<User>(User.class),(currentPage-1)*pageSize,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("分页失败！");
		}
		
	}
	@Override
	public User findCondition(String name, String pwd) {
		String sql="select user_id as uid, user_name as uname ,user_pwd as upwd ,user_email as email,user_mobile as mobile,user_status as status,user_login as ulogin from tb_user where user_pwd=? and user_name=?; ";
		
		try {
			return query.query(sql, new BeanHandler<User>(User.class),pwd,name);
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("user登录查询失败");
		}
	}

	
	@Override
	public User findById(int id) {
		String sql="select user_id as uid, user_name as uname ,user_pwd as upwd ,user_email as email,user_mobile as mobile,user_status as status,user_login as ulogin from tb_user where user_id=?;";
		try {
			return query.query(sql, new BeanHandler<User>(User.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更据用户id查询失败！");
		}
	}

	@Override
	public int getCount() {
		String sql="select count(*) from tb_user";
		
		try {
			return Integer.parseInt( query.query(sql, new ScalarHandler<Long>()).toString());
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("查询用户总数量失败");
		}
	}

	
	@Override
	public int totalPage(int pageSize) {
		double d=pageSize*1.0;
		return (int) Math.ceil(getCount()/d);
	}

	@Override
	public int[] DeleteAll(List<Integer> list) {
		String sql="delete from tb_user where user_id=?";
		Object[][] params=new Object[list.size()][1];
		for(int i=0;i<list.size();i++){
			params[i][0]=list.get(i);			
		}
		
		try {
			
			return  query.batch(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("批量删除失败！");
		}
		
	}

	@Override
	public List<User> findUser(List<Integer> list) {
		String sql="select user_id as uid, user_name as uname ,user_pwd as upwd ,user_email as email,user_mobile as mobile,user_status as status,user_login as ulogin from tb_user where user_id=?;";
		List<User> list1=new ArrayList<User>();
		for(int i=0;i<list.size();i++){
			try {
				User user = query.query(sql, new BeanHandler<User>(User.class),list.get(i));				
				if(!list1.contains(user)){
					list1.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("查询失败！");
			}			
		}		
		return list1;
	}

	@Override
	public List<User> findByUidList(List<Integer> list) {
		String sql="";
		
		
		return null;
	}

	@Override
	public int findByName(String username) {
		String sql="select count(user_name) from tb_user where user_name=?";
		
		try {
			return Integer.parseInt(query.query(sql, new ScalarHandler<Long>(), username).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("userDao更据username查找姓名失败。");
		}
		
	}

}
