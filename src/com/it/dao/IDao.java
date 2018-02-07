package com.it.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {
	public int add(T t);
	/**
	 * 下午1:46:11 
	 * @param s 可以序列化的对象如：Integer，String；好处扩展性更好
	 * @return
	 */
	public int delete(Serializable s);
	
	public int update(T t);
	/**
	 * 下午1:48:44
	 * 查询所有记录
	 * @return
	 *
	 */
	public List<T> list();
	/**
	 * 下午1:49:28
	 * 分页进行查询
	 * @param currentPage
	 * @param pageSize
	 * @return
	 *
	 */
	public List<T> listByPage(int currentPage,int pageSize);
	
	/**
	 * 下午1:49:42
	 * 根据Id进行查询
	 * @param id
	 * @return
	 *
	 */
	public T findById(int id);
	
	/**
	 * 下午1:50:04
	 * 获得总记录数
	 * @return
	 *
	 */
	public int getCount();
	
}
