package com.chuove.app.cms.controller.support;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chuove.app.cms.code.SessionKey;
import com.chuove.app.cms.common.utils.StringUtils;


/**
 * 账户登录以及权限检测
 * 
 * @author <a href="mailto:lauking1001@163.com">chenjq</a>
 */
public class CommonInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

	private static CopyOnWriteArrayList<String> FILTER_ACTION = new CopyOnWriteArrayList<String>();

	static {
		FILTER_ACTION.add("userLogin");
		FILTER_ACTION.add("findPswd");
		FILTER_ACTION.add("updatePswd");
		FILTER_ACTION.add("sendValidateCode");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.
	 * servlet .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			logger.debug("{} get passed.", handler.getClass().getName());
			return true;
		}
		String action = request.getRequestURI();
		String ctx = request.getContextPath();
		if (!isFilter(ctx, action)) {
			Subject subject = SecurityUtils.getSubject();
			Object userObj = subject.getSession().getAttribute(SessionKey.KEY_USER);
			Object userIdObj = subject.getSession().getAttribute(SessionKey.KEY_USERID);
			if (!subject.isAuthenticated() || userObj == null || userIdObj == null) {
				StringBuffer requestUrl = request.getRequestURL();
				if (StringUtils.isBlank(ctx) || ctx.equals(action)) {
					return true;
				} else {
					String logonUrl = null;
					if (action.indexOf(("admin")) > 0) {
						logonUrl = requestUrl.substring(0, requestUrl.indexOf(action)) + ctx + "/admin/";
					} else {
						logonUrl = requestUrl.substring(0, requestUrl.indexOf(action)) + ctx;
					}
					response.sendRedirect(logonUrl);
					return false;
				}
			} else {
				if (action.indexOf(("admin")) > 0) {
					return true;
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.
	 * servlet .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model)
			throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	private boolean isFilter(String ctx, String action) {
		if (action.startsWith(ctx) && !"/".equals(ctx) && ctx.length() > 1) {
			if(!action.equals(ctx))
			action = action.substring(ctx.length() + 1);
		}
		for (String ac : FILTER_ACTION) {
			if (action.equals(ac)) {
				return true;
			}
		}
		return false;
	}
}