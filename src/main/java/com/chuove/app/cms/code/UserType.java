package com.chuove.app.cms.code;

public enum UserType {
	ADMIN("Administrator", "系统管理员"), 
	MERCHANT("AccAdmin", "地产商管理员"),
	REGION("RegionAdmin", "区域管理员"), 
	REGIONREADER("RegionReader", "地产商数据读取人员"), 
	COMMONREADER("CommonReader", "区域数据读取人员"), 
	EDITOR("Editor", "区域数据录入人员");
	private final String code;
	private final String text;

	UserType(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
}
