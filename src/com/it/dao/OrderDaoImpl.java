package com.it.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.it.entity.Order;
import com.it.util.DbUtilPool;


public class OrderDaoImpl implements OrderDao {

	QueryRunner query=null;
	public OrderDaoImpl(){
		try {
			query=new QueryRunner(DbUtilPool.getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建UserDaoImpl失败。");
		}
		
	}
	
	@Override
	public int add(Order t) {
		String sql="insert into tb_order(order_user_id,order_user_name,order_create_time,order_cost,order_status,order_type)values(?,?,?,?,?,?);";		
		try {
			return  query.update(sql, t.getOuid(),t.getOuname(),t.getOcreateTime(),t.getOcost(),t.getOstatus(),t.getOpayType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("添加新订单失败");
		}
	}

	@Override
	public int delete(Serializable s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Order t) {
		String sql="update tb_order set order_user_id=?,order_user_name=?,order_create_time=?,order_cost=?,order_status=?,order_type=? where order_id=?";
		try {
			return query.update(sql, t.getOuid(),t.getOuname(),t.getOcreateTime(),t.getOcost(),t.getOstatus(),t.getOpayType(),t.getOid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("更新Order失败！！");
		}
		
		
	}

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> listByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(int id) {
		String sql="select order_id as oid,order_user_id as ouid,order_user_name as ouname,order_create_time as ocreateTime,order_cost as ocost,order_status as ostatus ,order_type as opayType from tb_order where order_id=?";
		try {
			return query.query(sql, new BeanHandler<Order>(Order.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("更据id查询order链表失败！");
		}
	}
	
	@Override
	public List<Order> findByUid(int uid,int status) {
		String sql="select order_id as oid,order_user_id as ouid,order_user_name as ouname,order_create_time as ocreateTime,order_cost as ocost,order_status as ostatus ,order_type as opayType from tb_order where order_user_id=? and order_status=?";
		try {
			return query.query(sql, new BeanListHandler<Order>(Order.class), uid,status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("更据Uid查询order链表失败！");
		}
	}
	
	
	@Override
	public List<Order> findByUid1(List<Integer> listoid,int status) {
		String sql="select order_id as oid,order_user_id as ouid,order_user_name as ouname,order_create_time as ocreateTime,order_cost as ocost,order_status as ostatus ,order_type as opayType from tb_order where order_id=? and order_status=?";
		List<Order> list1=new ArrayList<Order>();
		for(int i=0;i<listoid.size();i++){
			try {
				Order order = query.query(sql, new BeanHandler<Order>(Order.class),listoid.get(i),status);				
				System.out.println("------------orderDao:"+order);
				if(!list1.contains(order)){
					list1.add(order);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("查询失败！");
			}			
		}		
		return list1;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findMaxHouseId() {
		String sql="SELECT MAX(house_id) FROM tb_house";
		try {			
			return query.query(sql, new ScalarHandler<Integer>());
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("查询Order的Id的最大值失败");
		}

	
	}

	@Override
	public Order findCondition(int uid, Date date) {
		String sql="select order_id as oid,order_user_id as ouid,order_user_name as ouname,order_create_time as ocreateTime,order_cost as ocost,order_status as ostatus ,order_type as opayType from tb_order where order_create_time=? and order_user_id=?";
		try {
			System.out.println("new java.sql.Timestamp(date.getTime())-----"+new java.sql.Timestamp(date.getTime()));
			return query.query(sql, new BeanHandler<Order>(Order.class),new java.sql.Timestamp(date.getTime()),uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Order条件查询失败。");
		}
	}

	@Override
	public int totalPage(int id,int status,int pageSize) {
		return (int)Math.ceil(this.totalCount(id,status)/(pageSize*1.0));
		
	}

	@Override
	public int totalCount(int id, int status) {
		String sql="select count(*) from tb_order where order_user_id=? and order_status=?";
		
		try {
			return Integer.parseInt( query.query(sql, new ScalarHandler<Long>(),id,status).toString());
		} catch (NumberFormatException | SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException("查询用户订单总数量失败");
		}
	}

	
}