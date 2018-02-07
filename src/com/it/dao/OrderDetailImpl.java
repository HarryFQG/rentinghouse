package com.it.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.it.entity.OrderDetail;
import com.it.util.DbUtilPool;

public class OrderDetailImpl  implements OrderDetailDao{

	QueryRunner query=null;
	public OrderDetailImpl(){
		try {
			query=new QueryRunner(DbUtilPool.getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建UserDaoImpl失败。");
		}		
	}
	
	@Override
	public int add(OrderDetail t) {
		String sql="insert into tb_order_detail(odo_id,odh_id,od_days,od_price,od_cost)values(?,?,?,?,?)";
		try {
			return query.update(sql, t.getOdoid(),t.getOdhid(),t.getOdday(),t.getOdprice(),t.getOdcost());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("添加新的OrderDetail失败！");
		}
		
		
	}

	@Override
	public int delete(Serializable s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(OrderDetail t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDetail> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> listByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDetail> litsDetail(List<Integer> listoid) {
		String sql="select od_id as odid,odo_id as odoid ,odh_id as odhid,od_days as odday,od_price as odprice,od_cost as adcost from tb_order_detail where odo_id=?;";
		List<OrderDetail> list1=new ArrayList<OrderDetail>();
		for(int i=0;i<listoid.size();i++){
			try {
				OrderDetail user = query.query(sql, new BeanHandler<OrderDetail>(OrderDetail.class),listoid.get(i));				
				if(!list1.contains(user)){
					list1.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("批量查询OrderDetail查询失败！");
			}			
		}		
		return list1;
	}

	/**
	 * 房东查看客户
	 */
	@Override
	public List<OrderDetail> litsDetail2(List<Integer> listhid) {
		String sql="select od_id as odid,odo_id as odoid ,odh_id as odhid,od_days as odday,od_price as odprice,od_cost as adcost from tb_order_detail where odh_id=?;";
		List<OrderDetail> list1=new ArrayList<OrderDetail>();
		for(int i=0;i<listhid.size();i++){
			try {
				OrderDetail user = query.query(sql, new BeanHandler<OrderDetail>(OrderDetail.class),listhid.get(i));				
				if(!list1.contains(user)){
					list1.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("批量查询OrderDetail查询失败！");
			}			
		}		
		return list1;
	}

	@Override
	public OrderDetail findByOid(Integer oid) {
		String sql="select  od_id as odid,odo_id as odoid ,odh_id as odhid,od_days as odday,od_price as odprice,od_cost as adcost from tb_order_detail where odo_id=?;";
		try {
			return query.query(sql, new BeanHandler<OrderDetail>(OrderDetail.class), oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("更据Id查询OrderDetail失败");
		}
		
		
	}

	
	

}
