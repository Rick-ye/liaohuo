package com.chuove.app.cms.model;

import java.io.Serializable;
import java.util.Date;

public class Consumer implements Serializable {

	private static final long serialVersionUID = -6387787742654492038L;
	
	private Integer userId;
	private String wechatNum;
	private String userImg;
	private String mobileNum;
	private String userName;
	private String userPwd;
	private String userSex;
	private String cardId;
	private String cardImg1;
	private String cardImg2;
	private String referee;
	private float totalAmout;
	private Integer userStatus;
	private Date createTime;
	private Date updateTime;
	private Integer sortId;
	
	
	public Consumer() {

	}
	
	public Consumer(String mobileNum, String userPwd){
		this.mobileNum = mobileNum;
		this.userPwd = userPwd;
	}
	
	public Consumer(Integer userId, String wechatNum, String userImg, String mobileNum, String userName, String userPwd,
			String userSex, String cardId, String cardImg1, String cardImg2, String referee, float totalAmout,
			Integer userStatus, Date createTime, Date updateTime, Integer sortId) {
		super();
		this.userId = userId;
		this.wechatNum = wechatNum;
		this.userImg = userImg;
		this.mobileNum = mobileNum;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.cardId = cardId;
		this.cardImg1 = cardImg1;
		this.cardImg2 = cardImg2;
		this.referee = referee;
		this.totalAmout = totalAmout;
		this.userStatus = userStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.sortId = sortId;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getWechatNum() {
		return wechatNum;
	}
	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardImg1() {
		return cardImg1;
	}
	public void setCardImg1(String cardImg1) {
		this.cardImg1 = cardImg1;
	}
	public String getCardImg2() {
		return cardImg2;
	}
	public void setCardImg2(String cardImg2) {
		this.cardImg2 = cardImg2;
	}
	public String getReferee() {
		return referee;
	}
	public void setReferee(String referee) {
		this.referee = referee;
	}
	public float getTotalAmout() {
		return totalAmout;
	}
	public void setTotalAmout(float totalAmout) {
		this.totalAmout = totalAmout;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
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
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Consumer other = (Consumer) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Consumer [userId=" + userId + ", wechatNum=" + wechatNum + ", userImg=" + userImg + ", mobileNum="
				+ mobileNum + ", userName=" + userName + ", userPwd=" + userPwd + ", userSex=" + userSex + ", cardId="
				+ cardId + ", cardImg1=" + cardImg1 + ", cardImg2=" + cardImg2 + ", referee=" + referee
				+ ", totalAmout=" + totalAmout + ", userStatus=" + userStatus + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", sortId=" + sortId + "]";
	}
	
	
}








