package com.it.entity;

import java.util.List;



public class ResultModel {
	
	/*封装条件*/
	private String queryStr;
	private String price;
	private String street;
	private String houseType;
	private String area;	
	
	
	//对查询结果的过滤
	//查询总数
	private Long recordCount;
	//当前页
	private int curPage;
	//总页数
	private int pageCount;
	//结果记录
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
