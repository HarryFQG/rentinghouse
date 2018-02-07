package com.it.dao;

import java.util.List;

import com.it.entity.OrderDetail;

public interface OrderDetailDao extends IDao<OrderDetail>{

	public List<OrderDetail> litsDetail(List<Integer> listoid);

	List<OrderDetail> litsDetail2(List<Integer> listhid);
	public OrderDetail findByOid(Integer oid);
	
}
