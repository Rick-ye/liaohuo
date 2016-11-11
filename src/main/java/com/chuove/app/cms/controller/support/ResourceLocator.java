package com.chuove.app.cms.controller.support;

import java.io.InputStream;
import java.net.MalformedURLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceLocator {
	private static final Logger logger = LoggerFactory.getLogger(ResourceLocator.class);

	public static String getResource(String path) {
		if (ServletContextHolder.get() == null) {
			return null;
		}
		if ((path.startsWith("/")) || (path.startsWith("\\"))) {
			return ResourceLocator.class.getResource(path).getPath();
		}
		return ServletContextHolder.get().getRealPath(path);
	}

	public static InputStream getResourceAsStream(String path) {
		if (ServletContextHolder.get() == null) {
			return null;
		}
		if ((path.startsWith("/")) || (path.startsWith("\\"))) {
			return ResourceLocator.class.getResourceAsStream(path);
		}
		return ServletContextHolder.get().getResourceAsStream(path);
	}

	public static boolean exists(String path) {
		if (ServletContextHolder.get() == null) {
			return false;
		}
		if ((path.startsWith("/")) || (path.startsWith("\\"))) {
			return ResourceLocator.class.getResource(path) != null;
		}
		try {
			return ServletContextHolder.get().getResource(path) != null;
		} catch (MalformedURLException e) {
			logger.error("Failed to locate resource: {}", path, e);
		}
		return false;
	}
}
