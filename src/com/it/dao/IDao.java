package com.it.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {
	public int add(T t);
	/**
	 * ����1:46:11 
	 * @param s �������л��Ķ����磺Integer��String���ô���չ�Ը���
	 * @return
	 */
	public int delete(Serializable s);
	
	public int update(T t);
	/**
	 * ����1:48:44
	 * ��ѯ���м�¼
	 * @return
	 *
	 */
	public List<T> list();
	/**
	 * ����1:49:28
	 * ��ҳ���в�ѯ
	 * @param currentPage
	 * @param pageSize
	 * @return
	 *
	 */
	public List<T> listByPage(int currentPage,int pageSize);
	
	/**
	 * ����1:49:42
	 * ����Id���в�ѯ
	 * @param id
	 * @return
	 *
	 */
	public T findById(int id);
	
	/**
	 * ����1:50:04
	 * ����ܼ�¼��
	 * @return
	 *
	 */
	public int getCount();
	
}
