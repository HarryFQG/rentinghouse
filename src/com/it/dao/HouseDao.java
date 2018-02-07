package com.it.dao;

import java.util.List;

import com.it.entity.House;
import com.it.entity.ResultModel;

public interface HouseDao {
	
	/**
	 * ����1:36:56
	 * ���·�����Ϣ
	 * @param house
	 * @return
	 *
	 */
	public int updateHouse(House house);
	/**
	 * ����1:37:22
	 * ɾ��������Ϣ
	 * @param id
	 * @return
	 *
	 */
	public int deleteHouse(int id);
	
	/**
	 * ����1:38:06
	 *��ӷ�����Ϣ
	 * @param house
	 * @return
	 *
	 */
	public int addHouse(House house);
	/**
	 * ����1:38:40
	 * �������еķ�����Ϣ
	 * @return
	 *
	 */
	public int findAllHouse();
	/**
	 * ����1:39:00
	 * �Է�����Ϣ���в��ҷ�ҳSolr
	 * @param currentPage
	 * @param pageSize
	 * @return
	 *
	 */
	public ResultModel pageListHouse(String querystr,String price,String area,String houseType,String position,String status ,int currentPage,int pageSize);
	
	/**
	 * ����2:56:37
	 * ���û��ķ�ҳ�Ĳ�ѯ
	 * @param currentPage
	 * @param pageSize
	 * @param id
	 * @return
	 *
	 */
	public List<House> pageListHouse(int currentPage,int pageSize,int id);
	
	
	/**
	 * ����1:39:33
	 * ��ȡ��ҳ��
	 * @param pageSize
	 * @return
	 *
	 */
	public int totalPage(int pageSize);
	/**
	 * ����1:39:56
	 * ��ȡ������
	 * @return
	 *
	 */
	public int totalCount();
	/**
	 * ����1:40:09
	 * ����ɾ��
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
