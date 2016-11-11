package com.chuove.app.cms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.chuove.app.cms.code.SessionKey;
import com.chuove.app.cms.code.Settings;
import com.chuove.app.cms.common.context.RootContext;
import com.chuove.app.cms.controller.support.ControllerResponse;
import com.chuove.app.cms.model.UserDo;

public class BaseController {
	private ControllerResponse cRes = new ControllerResponse();

	public void setStatus(int status) {
		cRes.setStatus(status);
	}

	public void setMessage(String message) {
		cRes.setMessage(message);
	}

	public void setStatus(int status, String message) {
		cRes.setStatus(status);
		cRes.setMessage(message);
	}

	public void setBody(Object body) {
		cRes.setBody(body);
	}

	public void addData(String key, Object value) {
		cRes.addData(key, value);
	}

	public void setTotal(int total) {
		cRes.setTotal(total);
	}

	public void setDataList(List<?> list) {
		cRes.setDataList(list);
	}

	protected void renderData(HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(JSON.toJSONString(cRes));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (null != printWriter) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

	public UserDo getUserDo() {
		Subject subject = SecurityUtils.getSubject();
		Object obj = subject.getSession().getAttribute(SessionKey.KEY_USER);
		return obj == null ? null : (UserDo) obj;
	}
	public Integer getUserId(){
		Subject subject = SecurityUtils.getSubject();
		Object obj = subject.getSession().getAttribute(SessionKey.KEY_USERID);
		return obj == null ? null : (Integer) obj;
	}
	public void setImageHost(Model model){
		model.addAttribute("imagehost", RootContext.instance().get(Settings.IMAGE_HOST));
	}
	public void setImageHost(ModelMap model){
		model.addAttribute("imagehost", RootContext.instance().get(Settings.IMAGE_HOST));
	}
}
