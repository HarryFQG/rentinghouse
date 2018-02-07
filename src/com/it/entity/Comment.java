package com.it.entity;

import java.util.Date;

/**
 * 
 * @author Administrator 2017��8��14�� ����12:15:35
 *�ֶ�����				˵������
 *rc_id				���	������  		int  ����
 *rc_reply			�ظ�				varchar
 *rc_content		����				varchar
 *rc_create_time	����ʱ��			datetime
 *rc_reply_time		�ظ�ʱ��			datetime
 *rc_cid			���ͷ�id			int     
 *rc_rid			���շ�id			int
 *rc_cname			���ͷ�����			varchar
 *rc_rname			���շ�����			varchar
 *rc_status			��Ϣ״̬		 	int         0��δ�鿴����Ϣһ���ͣ�Ĭ��״̬��   1���Ѳ鿴  2 :�ظ�δ�鿴 3 �ظ��Ѳ鿴
 */
public class Comment {

	private Integer cid;
	private String creplay;
	private String ccontent;
	private Date   ccreateTime;
	private Date creplayTmie;
	private Integer ccid;
	private Integer  crid;
	private String ccname;
	private String crname;
	private int cstatus;
	public Comment() {
		super();
	}
	public Comment(Integer cid, String creplay, String ccontent,
			Date ccreateTime, Date creplayTmie, Integer ccid, Integer crid,
			String ccname, String crname, int cstatus) {
		super();
		this.cid = cid;
		this.creplay = creplay;
		this.ccontent = ccontent;
		this.ccreateTime = ccreateTime;
		this.creplayTmie = creplayTmie;
		this.ccid = ccid;
		this.crid = crid;
		this.ccname = ccname;
		this.crname = crname;
		this.cstatus = cstatus;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCreplay() {
		return creplay;
	}
	public void setCreplay(String creplay) {
		this.creplay = creplay;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public Date getCcreateTime() {
		return ccreateTime;
	}
	public void setCcreateTime(Date ccreateTime) {
		this.ccreateTime = ccreateTime;
	}
	public Date getCreplayTmie() {
		return creplayTmie;
	}
	public void setCreplayTmie(Date creplayTmie) {
		this.creplayTmie = creplayTmie;
	}
	public Integer getCcid() {
		return ccid;
	}
	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}
	public Integer getCrid() {
		return crid;
	}
	public void setCrid(Integer crid) {
		this.crid = crid;
	}
	public String getCcname() {
		return ccname;
	}
	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	public String getCrname() {
		return crname;
	}
	public void setCrname(String crname) {
		this.crname = crname;
	}
	public int getCstatus() {
		return cstatus;
	}
	public void setCstatus(int cstatus) {
		this.cstatus = cstatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ccid == null) ? 0 : ccid.hashCode());
		result = prime * result + ((ccname == null) ? 0 : ccname.hashCode());
		result = prime * result
				+ ((ccontent == null) ? 0 : ccontent.hashCode());
		result = prime * result
				+ ((ccreateTime == null) ? 0 : ccreateTime.hashCode());
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((creplay == null) ? 0 : creplay.hashCode());
		result = prime * result
				+ ((creplayTmie == null) ? 0 : creplayTmie.hashCode());
		result = prime * result + ((crid == null) ? 0 : crid.hashCode());
		result = prime * result + ((crname == null) ? 0 : crname.hashCode());
		result = prime * result + cstatus;
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
		Comment other = (Comment) obj;
		if (ccid == null) {
			if (other.ccid != null)
				return false;
		} else if (!ccid.equals(other.ccid))
			return false;
		if (ccname == null) {
			if (other.ccname != null)
				return false;
		} else if (!ccname.equals(other.ccname))
			return false;
		if (ccontent == null) {
			if (other.ccontent != null)
				return false;
		} else if (!ccontent.equals(other.ccontent))
			return false;
		if (ccreateTime == null) {
			if (other.ccreateTime != null)
				return false;
		} else if (!ccreateTime.equals(other.ccreateTime))
			return false;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (creplay == null) {
			if (other.creplay != null)
				return false;
		} else if (!creplay.equals(other.creplay))
			return false;
		if (creplayTmie == null) {
			if (other.creplayTmie != null)
				return false;
		} else if (!creplayTmie.equals(other.creplayTmie))
			return false;
		if (crid == null) {
			if (other.crid != null)
				return false;
		} else if (!crid.equals(other.crid))
			return false;
		if (crname == null) {
			if (other.crname != null)
				return false;
		} else if (!crname.equals(other.crname))
			return false;
		if (cstatus != other.cstatus)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", creplay=" + creplay + ", ccontent="
				+ ccontent + ", ccreateTime=" + ccreateTime + ", creplayTmie="
				+ creplayTmie + ", ccid=" + ccid + ", crid=" + crid
				+ ", ccname=" + ccname + ", crname=" + crname + ", cstatus="
				+ cstatus + "]";
	}
	
}
