package com.it.entity;

import java.io.Serializable;
/**
 * 
 * @author Administrator 
 */
public class User implements Serializable{
	
	
	private Integer uid;
	private String uname;
	private String upwd;
	private String email;
	private String mobile;
	private Integer status;
	private Integer ulogin;
	public User() {
		super();
	}
	public User(Integer uid, String uname, String upwd, String email,
			String mobile, Integer status, Integer ulogin) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
		this.ulogin = ulogin;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUlogin() {
		return ulogin;
	}
	public void setUlogin(Integer ulogin) {
		this.ulogin = ulogin;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((ulogin == null) ? 0 : ulogin.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((upwd == null) ? 0 : upwd.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (ulogin == null) {
			if (other.ulogin != null)
				return false;
		} else if (!ulogin.equals(other.ulogin))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (upwd == null) {
			if (other.upwd != null)
				return false;
		} else if (!upwd.equals(other.upwd))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd
				+ ", email=" + email + ", mobile=" + mobile + ", status="
				+ status + ", ulogin=" + ulogin + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
