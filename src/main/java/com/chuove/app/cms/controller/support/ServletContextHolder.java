package com.chuove.app.cms.controller.support;

import javax.servlet.ServletContext;

public class ServletContextHolder {
	private static ServletContext servletContext;
	  
	  public static void setServletContext(ServletContext _servletContext)
	  {
	    servletContext = _servletContext;
	  }
	  
	  public static ServletContext get()
	  {
	    return servletContext;
	  }
}
