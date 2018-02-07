package com.it.entity;

import java.util.Date;

/**
 * 
 * @author Administrator
 */
public class Order {

	private Integer oid;
	private Integer ouid;
	private String ouname;
	private Date ocreateTime;
	private double ocost;
	private int ostatus;
	private int opayType;
	public Order() {
		super();
	}
	public Order(Integer oid, Integer ouid, String ouname, Date ocreateTime,
			double ocost, int ostatus, int opayType) {
		super();
		this.oid = oid;
		this.ouid = ouid;
		this.ouname = ouname;
		this.ocreateTime = ocreateTime;
		this.ocost = ocost;
		this.ostatus = ostatus;
		this.opayType = opayType;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getOuid() {
		return ouid;
	}
	public void setOuid(Integer ouid) {
		this.ouid = ouid;
	}
	public String getOuname() {
		return ouname;
	}
	public void setOuname(String ouname) {
		this.ouname = ouname;
	}
	public Date getOcreateTime() {
		return ocreateTime;
	}
	public void setOcreateTime(Date ocreateTime) {
		this.ocreateTime = ocreateTime;
	}
	public double getOcost() {
		return ocost;
	}
	public void setOcost(double ocost) {
		this.ocost = ocost;
	}
	public int getOstatus() {
		return ostatus;
	}
	public void setOstatus(int ostatus) {
		this.ostatus = ostatus;
	}
	public int getOpayType() {
		return opayType;
	}
	public void setOpayType(int opayType) {
		this.opayType = opayType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ocost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((ocreateTime == null) ? 0 : ocreateTime.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + opayType;
		result = prime * result + ostatus;
		result = prime * result + ((ouid == null) ? 0 : ouid.hashCode());
		result = prime * result + ((ouname == null) ? 0 : ouname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (Double.doubleToLongBits(ocost) != Double
				.doubleToLongBits(other.ocost))
			return false;
		if (ocreateTime == null) {
			if (other.ocreateTime != null)
				return false;
		} else if (!ocreateTime.equals(other.ocreateTime))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (opayType != other.opayType)
			return false;
		if (ostatus != other.ostatus)
			return false;
		if (ouid == null) {
			if (other.ouid != null)
				return false;
		} else if (!ouid.equals(other.ouid))
			return false;
		if (ouname == null) {
			if (other.ouname != null)
				return false;
		} else if (!ouname.equals(other.ouname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ouid=" + ouid + ", ouname=" + ouname
				+ ", ocreateTime=" + ocreateTime + ", ocost=" + ocost
				+ ", ostatus=" + ostatus + ", opayType=" + opayType + "]";
	}
	
	
	
	
	
	
}
