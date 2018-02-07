package com.it.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Administrator
 */
public class House implements Serializable{
	private Integer hid;
	private Integer huid;
	private String huname;
	private Integer hinvoice;
	private Integer hleaseTpe;
	private Integer	htype;
	private double area;
	private int hlive;
	private int hbed;
	private int hbedRoom;
	private int hbedForm;
	private  int htoilet;
	private Date hcheckIn;
	private Date hcheckOut;
	private int	hminDay;
	private int hmaxDay;
	private int hrefundDay;
	private int hrentPrice;
	private String hdescribe;
	private String huseRule;
	private String hservice;
	private String haddress;
	private String hpayRule;
	private String hpicture1;
	private String hpicture2;
	private String hpicture3;
	private  int hstatus;
	public House() {
		super();
	}
	public House(Integer hid, Integer huid, String huname, Integer hinvoice,
			Integer hleaseTpe, Integer htype, double area, int hlive, int hbed,
			int hbedRoom, int hbedForm, int htoilet, Date hcheckIn,
			Date hcheckOut, int hminDay, int hmaxDay, int hrefundDay,
			int hrentPrice, String hdescribe, String huseRule, String hservice,
			String haddress, String hpayRule, String hpicture1,
			String hpicture2, String hpicture3, int hstatus) {
		super();
		this.hid = hid;
		this.huid = huid;
		this.huname = huname;
		this.hinvoice = hinvoice;
		this.hleaseTpe = hleaseTpe;
		this.htype = htype;
		this.area = area;
		this.hlive = hlive;
		this.hbed = hbed;
		this.hbedRoom = hbedRoom;
		this.hbedForm = hbedForm;
		this.htoilet = htoilet;
		this.hcheckIn = hcheckIn;
		this.hcheckOut = hcheckOut;
		this.hminDay = hminDay;
		this.hmaxDay = hmaxDay;
		this.hrefundDay = hrefundDay;
		this.hrentPrice = hrentPrice;
		this.hdescribe = hdescribe;
		this.huseRule = huseRule;
		this.hservice = hservice;
		this.haddress = haddress;
		this.hpayRule = hpayRule;
		this.hpicture1 = hpicture1;
		this.hpicture2 = hpicture2;
		this.hpicture3 = hpicture3;
		this.hstatus = hstatus;
	}
	public Integer getHid() {
		return hid;
	}
	public void setHid(Integer hid) {
		this.hid = hid;
	}
	public Integer getHuid() {
		return huid;
	}
	public void setHuid(Integer huid) {
		this.huid = huid;
	}
	public String getHuname() {
		return huname;
	}
	public void setHuname(String huname) {
		this.huname = huname;
	}
	public Integer getHinvoice() {
		return hinvoice;
	}
	public void setHinvoice(Integer hinvoice) {
		this.hinvoice = hinvoice;
	}
	public Integer getHleaseTpe() {
		return hleaseTpe;
	}
	public void setHleaseTpe(Integer hleaseTpe) {
		this.hleaseTpe = hleaseTpe;
	}
	public Integer getHtype() {
		return htype;
	}
	public void setHtype(Integer htype) {
		this.htype = htype;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public int getHlive() {
		return hlive;
	}
	public void setHlive(int hlive) {
		this.hlive = hlive;
	}
	public int getHbed() {
		return hbed;
	}
	public void setHbed(int hbed) {
		this.hbed = hbed;
	}
	public int getHbedRoom() {
		return hbedRoom;
	}
	public void setHbedRoom(int hbedRoom) {
		this.hbedRoom = hbedRoom;
	}
	public int getHbedForm() {
		return hbedForm;
	}
	public void setHbedForm(int hbedForm) {
		this.hbedForm = hbedForm;
	}
	public int getHtoilet() {
		return htoilet;
	}
	public void setHtoilet(int htoilet) {
		this.htoilet = htoilet;
	}
	public Date getHcheckIn() {
		return hcheckIn;
	}
	public void setHcheckIn(Date hcheckIn) {
		this.hcheckIn = hcheckIn;
	}
	public Date getHcheckOut() {
		return hcheckOut;
	}
	public void setHcheckOut(Date hcheckOut) {
		this.hcheckOut = hcheckOut;
	}
	public int getHminDay() {
		return hminDay;
	}
	public void setHminDay(int hminDay) {
		this.hminDay = hminDay;
	}
	public int getHmaxDay() {
		return hmaxDay;
	}
	public void setHmaxDay(int hmaxDay) {
		this.hmaxDay = hmaxDay;
	}
	public int getHrefundDay() {
		return hrefundDay;
	}
	public void setHrefundDay(int hrefundDay) {
		this.hrefundDay = hrefundDay;
	}
	public int getHrentPrice() {
		return hrentPrice;
	}
	public void setHrentPrice(int hrentPrice) {
		this.hrentPrice = hrentPrice;
	}
	public String getHdescribe() {
		return hdescribe;
	}
	public void setHdescribe(String hdescribe) {
		this.hdescribe = hdescribe;
	}
	public String getHuseRule() {
		return huseRule;
	}
	public void setHuseRule(String huseRule) {
		this.huseRule = huseRule;
	}
	public String getHservice() {
		return hservice;
	}
	public void setHservice(String hservice) {
		this.hservice = hservice;
	}
	public String getHaddress() {
		return haddress;
	}
	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}
	public String getHpayRule() {
		return hpayRule;
	}
	public void setHpayRule(String hpayRule) {
		this.hpayRule = hpayRule;
	}
	public String getHpicture1() {
		return hpicture1;
	}
	public void setHpicture1(String hpicture1) {
		this.hpicture1 = hpicture1;
	}
	public String getHpicture2() {
		return hpicture2;
	}
	public void setHpicture2(String hpicture2) {
		this.hpicture2 = hpicture2;
	}
	public String getHpicture3() {
		return hpicture3;
	}
	public void setHpicture3(String hpicture3) {
		this.hpicture3 = hpicture3;
	}
	public int getHstatus() {
		return hstatus;
	}
	public void setHstatus(int hstatus) {
		this.hstatus = hstatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(area);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((haddress == null) ? 0 : haddress.hashCode());
		result = prime * result + hbed;
		result = prime * result + hbedForm;
		result = prime * result + hbedRoom;
		result = prime * result
				+ ((hcheckIn == null) ? 0 : hcheckIn.hashCode());
		result = prime * result
				+ ((hcheckOut == null) ? 0 : hcheckOut.hashCode());
		result = prime * result
				+ ((hdescribe == null) ? 0 : hdescribe.hashCode());
		result = prime * result + ((hid == null) ? 0 : hid.hashCode());
		result = prime * result
				+ ((hinvoice == null) ? 0 : hinvoice.hashCode());
		result = prime * result
				+ ((hleaseTpe == null) ? 0 : hleaseTpe.hashCode());
		result = prime * result + hlive;
		result = prime * result + hmaxDay;
		result = prime * result + hminDay;
		result = prime * result
				+ ((hpayRule == null) ? 0 : hpayRule.hashCode());
		result = prime * result
				+ ((hpicture1 == null) ? 0 : hpicture1.hashCode());
		result = prime * result
				+ ((hpicture2 == null) ? 0 : hpicture2.hashCode());
		result = prime * result
				+ ((hpicture3 == null) ? 0 : hpicture3.hashCode());
		result = prime * result + hrefundDay;
		result = prime * result + hrentPrice;
		result = prime * result
				+ ((hservice == null) ? 0 : hservice.hashCode());
		result = prime * result + hstatus;
		result = prime * result + htoilet;
		result = prime * result + ((htype == null) ? 0 : htype.hashCode());
		result = prime * result + ((huid == null) ? 0 : huid.hashCode());
		result = prime * result + ((huname == null) ? 0 : huname.hashCode());
		result = prime * result
				+ ((huseRule == null) ? 0 : huseRule.hashCode());
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
		House other = (House) obj;
		if (Double.doubleToLongBits(area) != Double
				.doubleToLongBits(other.area))
			return false;
		if (haddress == null) {
			if (other.haddress != null)
				return false;
		} else if (!haddress.equals(other.haddress))
			return false;
		if (hbed != other.hbed)
			return false;
		if (hbedForm != other.hbedForm)
			return false;
		if (hbedRoom != other.hbedRoom)
			return false;
		if (hcheckIn == null) {
			if (other.hcheckIn != null)
				return false;
		} else if (!hcheckIn.equals(other.hcheckIn))
			return false;
		if (hcheckOut == null) {
			if (other.hcheckOut != null)
				return false;
		} else if (!hcheckOut.equals(other.hcheckOut))
			return false;
		if (hdescribe == null) {
			if (other.hdescribe != null)
				return false;
		} else if (!hdescribe.equals(other.hdescribe))
			return false;
		if (hid == null) {
			if (other.hid != null)
				return false;
		} else if (!hid.equals(other.hid))
			return false;
		if (hinvoice == null) {
			if (other.hinvoice != null)
				return false;
		} else if (!hinvoice.equals(other.hinvoice))
			return false;
		if (hleaseTpe == null) {
			if (other.hleaseTpe != null)
				return false;
		} else if (!hleaseTpe.equals(other.hleaseTpe))
			return false;
		if (hlive != other.hlive)
			return false;
		if (hmaxDay != other.hmaxDay)
			return false;
		if (hminDay != other.hminDay)
			return false;
		if (hpayRule == null) {
			if (other.hpayRule != null)
				return false;
		} else if (!hpayRule.equals(other.hpayRule))
			return false;
		if (hpicture1 == null) {
			if (other.hpicture1 != null)
				return false;
		} else if (!hpicture1.equals(other.hpicture1))
			return false;
		if (hpicture2 == null) {
			if (other.hpicture2 != null)
				return false;
		} else if (!hpicture2.equals(other.hpicture2))
			return false;
		if (hpicture3 == null) {
			if (other.hpicture3 != null)
				return false;
		} else if (!hpicture3.equals(other.hpicture3))
			return false;
		if (hrefundDay != other.hrefundDay)
			return false;
		if (hrentPrice != other.hrentPrice)
			return false;
		if (hservice == null) {
			if (other.hservice != null)
				return false;
		} else if (!hservice.equals(other.hservice))
			return false;
		if (hstatus != other.hstatus)
			return false;
		if (htoilet != other.htoilet)
			return false;
		if (htype == null) {
			if (other.htype != null)
				return false;
		} else if (!htype.equals(other.htype))
			return false;
		if (huid == null) {
			if (other.huid != null)
				return false;
		} else if (!huid.equals(other.huid))
			return false;
		if (huname == null) {
			if (other.huname != null)
				return false;
		} else if (!huname.equals(other.huname))
			return false;
		if (huseRule == null) {
			if (other.huseRule != null)
				return false;
		} else if (!huseRule.equals(other.huseRule))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "House [hid=" + hid + ", huid=" + huid + ", huname=" + huname
				+ ", hinvoice=" + hinvoice + ", hleaseTpe=" + hleaseTpe
				+ ", htype=" + htype + ", area=" + area + ", hlive=" + hlive
				+ ", hbed=" + hbed + ", hbedRoom=" + hbedRoom + ", hbedForm="
				+ hbedForm + ", htoilet=" + htoilet + ", hcheckIn=" + hcheckIn
				+ ", hcheckOut=" + hcheckOut + ", hminDay=" + hminDay
				+ ", hmaxDay=" + hmaxDay + ", hrefundDay=" + hrefundDay
				+ ", hrentPrice=" + hrentPrice + ", hdescribe=" + hdescribe
				+ ", huseRule=" + huseRule + ", hservice=" + hservice
				+ ", haddress=" + haddress + ", hpayRule=" + hpayRule
				+ ", hpicture1=" + hpicture1 + ", hpicture2=" + hpicture2
				+ ", hpicture3=" + hpicture3 + ", hstatus=" + hstatus + "]";
	}
	
	
	
	
	
}
