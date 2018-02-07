package com.it.dao;

import java.util.List;

import com.it.entity.House;
import com.it.entity.ResultModel;

public interface HouseDao {
	
	/**
	 * 下午1:36:56
	 * 更新房屋信息
	 * @param house
	 * @return
	 *
	 */
	public int updateHouse(House house);
	/**
	 * 下午1:37:22
	 * 删除房屋信息
	 * @param id
	 * @return
	 *
	 */
	public int deleteHouse(int id);
	
	/**
	 * 下午1:38:06
	 *添加房屋信息
	 * @param house
	 * @return
	 *
	 */
	public int addHouse(House house);
	/**
	 * 下午1:38:40
	 * 查找所有的房屋信息
	 * @return
	 *
	 */
	public int findAllHouse();
	/**
	 * 下午1:39:00
	 * 对房屋信息进行查找分页Solr
	 * @param currentPage
	 * @param pageSize
	 * @return
	 *
	 */
	public ResultModel pageListHouse(String querystr,String price,String area,String houseType,String position,String status ,int currentPage,int pageSize);
	
	/**
	 * 下午2:56:37
	 * 对用户的分页的查询
	 * @param currentPage
	 * @param pageSize
	 * @param id
	 * @return
	 *
	 */
	public List<House> pageListHouse(int currentPage,int pageSize,int id);
	
	
	/**
	 * 下午1:39:33
	 * 获取总页数
	 * @param pageSize
	 * @return
	 *
	 */
	public int totalPage(int pageSize);
	/**
	 * 下午1:39:56
	 * 获取总条数
	 * @return
	 *
	 */
	public int totalCount();
	/**
	 * 下午1:40:09
	 * 批量删除
	 * @param list
	 * @return
	 *
	 */
	public int[] DeleteAll(List<Integer> list);
	
	public House findById(int id);
	
	public int findMaxHouseId();
	
	public List<House> findByListHouse(List<Integer>listhid);
	List<House> findByListHouse1(List<Integer> listhid);
	
	
	
}
