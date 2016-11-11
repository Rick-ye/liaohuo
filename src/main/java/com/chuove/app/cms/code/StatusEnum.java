package com.chuove.app.cms.code;

public enum StatusEnum {
	ACTIVITE(1, "开放"), UNACTIVITE(0, "不开放");
	private final int code;
	private final String text;

	StatusEnum(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public String getText() {
		return text;
	}
}
