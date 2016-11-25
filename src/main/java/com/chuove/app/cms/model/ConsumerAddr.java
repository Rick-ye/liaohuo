package com.chuove.app.cms.model;

import java.io.Serializable;
import java.util.Date;

public class ConsumerAddr implements Serializable {

	private static final long serialVersionUID = -7155705663059424774L;
	
	private Integer addrId;
	private Integer userId;
	private String addUserName;
	private String addMobile;
	private String addPrvn;
	private String addCity;
	private String addCnty;
	private String addInfo;
	private Integer addStatus;
	private Date createTime;
	private Date updateTime;
	private Integer sortId;
	
	public ConsumerAddr() {
		super();
	}
	public Integer getAddrId() {
		return addrId;
	}
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public String getAddMobile() {
		return addMobile;
	}
	public void setAddMobile(String addMobile) {
		this.addMobile = addMobile;
	}
	public String getAddPrvn() {
		return addPrvn;
	}
	public void setAddPrvn(String addPrvn) {
		this.addPrvn = addPrvn;
	}
	public String getAddCity() {
		return addCity;
	}
	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}
	public String getAddCnty() {
		return addCnty;
	}
	public void setAddCnty(String addCnty) {
		this.addCnty = addCnty;
	}
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public Integer getAddStatus() {
		return addStatus;
	}
	public void setAddStatus(Integer addStatus) {
		this.addStatus = addStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addrId == null) ? 0 : addrId.hashCode());
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
		ConsumerAddr other = (ConsumerAddr) obj;
		if (addrId == null) {
			if (other.addrId != null)
				return false;
		} else if (!addrId.equals(other.addrId))
			return false;
		return true;
	}
	
}
