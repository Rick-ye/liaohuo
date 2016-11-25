package com.chuove.app.cms.controller.support;

public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 4248340414428419572L;
	
	public ControllerException() {
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
