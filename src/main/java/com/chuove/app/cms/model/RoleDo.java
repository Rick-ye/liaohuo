package com.chuove.app.cms.model;

import java.io.Serializable;
import java.util.Date;

public class RoleDo implements Serializable {
	private static final long serialVersionUID = 5240893631592884470L;
	private Integer roleId;
	private String roleAlias;
	private String roleName;
	private String roleDesc;
	private Date createTime;
	private Date updateTime;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public String getRoleAlias() {
		return roleAlias;
	}

	public void setRoleAlias(String roleAlias) {
		this.roleAlias = roleAlias;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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

}
