package com.chuove.app.cms.code;

public enum UserStatus {
	ACTIVE("1","已开启"),FORBIDDEN("0","已禁用");
	private final String code;
	private final String text;
	UserStatus(String code,String text ){
		this.code=code;
		this.text=text;
	}
	public String getCode() {
		return code;
	}
	public String getText() {
		return text;
	}
	
}
