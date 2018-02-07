package com.it.dao;

import java.util.List;

import com.it.entity.User;



public interface UserDao extends IDao<User>{

	/**
	 * UserDao ��д�Լ����еķ���
	 */
	/**
	 * ����1:53:12
	 * �û���¼
	 * @param name
	 * @param pwd
	 * @return
	 *
	 */
	public User findCondition(String name,String pwd);
	
	
	/**
	 * ����1:55:04
	 * �����ҳ��
	 * @param pageSize
	 * @return
	 *
	 */
	public int totalPage(int pageSize);
	
	/**
	 * ����1:55:18
	 * ����ɾ��
	 * @param list
	 * @return
	 *
	 */
	public int[] DeleteAll(List<Integer> list);
	/**
	 * ����7:16:48
	 * ������ѯ�û�
	 * @param list
	 * @return
	 *
	 */
	public List<User> findUser(List<Integer> list);
	
	public List<User> findByUidList(List<Integer> list);


	public int findByName(String username);
	
	
	
	
	
	
}
