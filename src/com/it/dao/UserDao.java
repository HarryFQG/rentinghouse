package com.it.dao;

import java.util.List;

import com.it.entity.User;



public interface UserDao extends IDao<User>{

	/**
	 * UserDao 中写自己特有的方法
	 */
	/**
	 * 下午1:53:12
	 * 用户登录
	 * @param name
	 * @param pwd
	 * @return
	 *
	 */
	public User findCondition(String name,String pwd);
	
	
	/**
	 * 下午1:55:04
	 * 获得总页数
	 * @param pageSize
	 * @return
	 *
	 */
	public int totalPage(int pageSize);
	
	/**
	 * 下午1:55:18
	 * 批量删除
	 * @param list
	 * @return
	 *
	 */
	public int[] DeleteAll(List<Integer> list);
	/**
	 * 下午7:16:48
	 * 批量查询用户
	 * @param list
	 * @return
	 *
	 */
	public List<User> findUser(List<Integer> list);
	
	public List<User> findByUidList(List<Integer> list);


	public int findByName(String username);
	
	
	
	
	
	
}
