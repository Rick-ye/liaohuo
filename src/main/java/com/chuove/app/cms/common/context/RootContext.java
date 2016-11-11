package com.chuove.app.cms.common.context;

public class RootContext extends MapContext<Object> {
	private static final RootContext systemContext = new RootContext();

	public static RootContext instance() {
		return systemContext;
	}
}
