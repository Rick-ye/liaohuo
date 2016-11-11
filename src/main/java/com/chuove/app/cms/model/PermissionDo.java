package com.chuove.app.cms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PermissionDo implements Serializable {
	private static final long serialVersionUID = -152460014335597967L;
	private Integer permissionId;
	private Integer parentId;
	private String permissionAlias;
	private String permissionName;
	private String permissionDesc;
	private Date createTime;
	private Date updateTime;
	private Integer tmpUserId;
	private List<PermissionDo> permissionList=new ArrayList<PermissionDo>();

	

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public String getPermissionAlias() {
		return permissionAlias;
	}

	public void setPermissionAlias(String permissionAlias) {
		this.permissionAlias = permissionAlias;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}


	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

	public List<PermissionDo> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionDo> permissionList) {
		this.permissionList = permissionList;
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

	public Integer getTmpUserId() {
		return tmpUserId;
	}

	public void setTmpUserId(Integer tmpUserId) {
		this.tmpUserId = tmpUserId;
	}

	

}
