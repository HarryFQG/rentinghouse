package com.it.entity;

import java.util.List;



public class ResultModel {
	
	/*��װ����*/
	private String queryStr;
	private String price;
	private String street;
	private String houseType;
	private String area;	
	
	
	//�Բ�ѯ����Ĺ���
	//��ѯ����
	private Long recordCount;
	//��ǰҳ
	private int curPage;
	//��ҳ��
	private int pageCount;
	//�����¼
	private List<House> houseList;
	public ResultModel() {
		super();
	}
	public ResultModel(String queryStr, String price, String street,
			String houseType, String area, Long recordCount, int curPage,
			int pageCount, List<House> houseList) {
		super();
		this.queryStr = queryStr;
		this.price = price;
		this.street = street;
		this.houseType = houseType;
		this.area = area;
		this.recordCount = recordCount;
		this.curPage = curPage;
		this.pageCount = pageCount;
		this.houseList = houseList;
	}
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<House> getHouseList() {
		return houseList;
	}
	public void setHouseList(List<House> houseList) {
		this.houseList = houseList;
	}
	@Override
	public String toString() {
		return "ResultModel [queryStr=" + queryStr + ", price=" + price
				+ ", street=" + street + ", houseType=" + houseType + ", area="
				+ area + ", recordCount=" + recordCount + ", curPage="
				+ curPage + ", pageCount=" + pageCount + ", houseList="
				+ houseList + "]";
	}
	
	
		
	
	
	
}
