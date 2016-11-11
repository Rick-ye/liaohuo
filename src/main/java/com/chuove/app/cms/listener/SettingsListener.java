package com.chuove.app.cms.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chuove.app.cms.common.context.RootContext;
import com.chuove.app.cms.common.utils.StringUtils;
import com.chuove.app.cms.controller.support.ResourceLocator;
import com.chuove.app.cms.controller.support.ServletContextHolder;

public class SettingsListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(SettingsListener.class);
	public static final String SETTINGS_LOCATION_PARAM = "settingsLocation";
	  
	public void contextDestroyed(ServletContextEvent event) {}
	  
	public void contextInitialized(ServletContextEvent event){
	    ServletContextHolder.setServletContext(event.getServletContext());
	    String settingPath = event.getServletContext().getInitParameter("settingsLocation");
	    if (StringUtils.isNotEmpty(settingPath))
	    {
	    	logger.info("loading settings from: " + settingPath);
	    	try{
	    		loadContextSettings(settingPath, event.getServletContext());
	    	}catch (IOException ex){
	    		throw new IllegalArgumentException("Invalid 'settingsLocation' parameter: " + ex.getMessage());
	    	}
	    }
	}
	  
	private void loadContextSettings(String settingPath, ServletContext sc)
			throws IOException{
	    InputStream in = ResourceLocator.getResourceAsStream(settingPath);
	    if (in == null){
	    	logger.warn("No setting file found on path: " + settingPath);
	    	return;
	    }
	    try{
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		      
		    String line = null;
		    RootContext ctx = RootContext.instance();
		    while ((line = reader.readLine()) != null) {
		    	if ((!line.startsWith("#")) && (!line.startsWith("rem ")) && (line.length() != 0))
		        {
		    		int inx = line.indexOf("=");
		    		String name = line.substring(0, inx);
		    		String value = line.substring(inx + 1);
		    		ctx.set(name.trim(), value.trim());
		        }
		    }
	    }finally{
	    	in.close();
	    }
	}

}
