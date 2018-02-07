package com.it.dao;

import java.util.Date;
import java.util.List;

import com.it.entity.Order;


public interface OrderDao extends IDao<Order>{
	public int findMaxHouseId();
	public Order findCondition(int uid,Date date);
	public List<Order> findByUid(int uid,int status);
	public int totalPage(int id,int status,int pageSize);
	public int totalCount(int id,int status);
	List<Order> findByUid1(List<Integer> listoid, int status);
}
