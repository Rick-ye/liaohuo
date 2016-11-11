package com.chuove.app.cms.controller.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerResponse implements Serializable {
	private static final long serialVersionUID = -5198741158526543988L;
	private int status = 200;
	private String message = "";
	private Object body;

	public ControllerResponse() {
	}

	public ControllerResponse(int status) {
		setStatus(status);
	}

	public ControllerResponse(int status, String message) {
		setStatus(status);
		setMessage(message);
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setStatus(int status, String message) {
		setStatus(status);
		setMessage(message);
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return this.body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public void addData(String key, Object value) {
		if (this.body == null) {
			this.body = new HashMap();
		}
		if ((this.body != null) && ((this.body instanceof Map))) {
			((Map) this.body).put(key, value);
		}
	}

	public void setTotal(int total) {
		if (this.body == null) {
			this.body = new HashMap();
		}
		if ((this.body != null) && ((this.body instanceof Map))) {
			((Map) this.body).put("total", Integer.valueOf(total));
		}
	}

	public void setDataList(List<?> list) {
		if (this.body == null) {
			this.body = new HashMap();
		}
		if ((this.body != null) && ((this.body instanceof Map))) {
			((Map) this.body).put("data", list);
		}
	}
}
