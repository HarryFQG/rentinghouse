package com.it.entity;
/**
 * 
 * @author Administrator         
 */

public class OrderDetail {

	private Integer odid;
	private int odoid;
	private int odhid;
	private int odday;
	private double odprice;
	private double odcost;
	public OrderDetail() {
		super();
	}
	public OrderDetail(Integer odid, int odoid, int odhid, int odday,
			double odprice, double odcost) {
		super();
		this.odid = odid;
		this.odoid = odoid;
		this.odhid = odhid;
		this.odday = odday;
		this.odprice = odprice;
		this.odcost = odcost;
	}
	public Integer getOdid() {
		return odid;
	}
	public void setOdid(Integer odid) {
		this.odid = odid;
	}
	public int getOdoid() {
		return odoid;
	}
	public void setOdoid(int odoid) {
		this.odoid = odoid;
	}
	public int getOdhid() {
		return odhid;
	}
	public void setOdhid(int odhid) {
		this.odhid = odhid;
	}
	public int getOdday() {
		return odday;
	}
	public void setOdday(int odday) {
		this.odday = odday;
	}
	public double getOdprice() {
		return odprice;
	}
	public void setOdprice(double odprice) {
		this.odprice = odprice;
	}
	public double getOdcost() {
		return odcost;
	}
	public void setOdcost(double odcost) {
		this.odcost = odcost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(odcost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + odday;
		result = prime * result + odhid;
		result = prime * result + ((odid == null) ? 0 : odid.hashCode());
		result = prime * result + odoid;
		temp = Double.doubleToLongBits(odprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		OrderDetail other = (OrderDetail) obj;
		if (Double.doubleToLongBits(odcost) != Double
				.doubleToLongBits(other.odcost))
			return false;
		if (odday != other.odday)
			return false;
		if (odhid != other.odhid)
			return false;
		if (odid == null) {
			if (other.odid != null)
				return false;
		} else if (!odid.equals(other.odid))
			return false;
		if (odoid != other.odoid)
			return false;
		if (Double.doubleToLongBits(odprice) != Double
				.doubleToLongBits(other.odprice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDetail [odid=" + odid + ", odoid=" + odoid + ", odhid="
				+ odhid + ", odday=" + odday + ", odprice=" + odprice
				+ ", odcost=" + odcost + "]";
	}
	
}
