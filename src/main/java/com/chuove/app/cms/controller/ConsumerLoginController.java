package com.chuove.app.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuove.app.cms.code.SessionKey;
import com.chuove.app.cms.common.utils.StringUtils;
import com.chuove.app.cms.model.Consumer;
import com.chuove.app.cms.service.ConsumerService;

@Controller
@RequestMapping(value="consumer")
public class ConsumerLoginController {
	private static Logger logger = LoggerFactory.getLogger(ConsumerLoginController.class);
	
	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping(value="login")
	public String login(){
		try {
			Subject subject = SecurityUtils.getSubject();
			if(subject.isAuthenticated()){
				return "redirect:/consumer/index";
			}
		} catch (Exception e) {
			logger.error("error", e);
		}
		return "consumer_login";
	}
	
	@RequestMapping(value="index")
	public String index(){
		try {
			Subject subject = SecurityUtils.getSubject();
			if(! subject.isAuthenticated()){
				return "consumer_login";
			} else {
				Consumer consumer = consumerService.findConsumerByMobileNum(subject.getPrincipal().toString());
				//model.put("cons",consumer);
			}
		} catch (Exception e) {
			logger.error("error",e);
		}
		return "index2";
	}
	
	@RequestMapping(value="doLogin")
	public String doLogin(HttpServletResponse response, HttpServletRequest request, Model model){
		String msg="";
		String mobileNum = request.getParameter("mobileNum");
		if(StringUtils.isBlank(mobileNum)){
			return "consumer_login";
		}
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(mobileNum, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if(subject.isAuthenticated()){
				Consumer consumer = consumerService.findConsumerByMobileNum(mobileNum);
				subject.getSession().setAttribute(SessionKey.KEY_CONSUMER, consumer);
				subject.getSession().setAttribute(SessionKey.KEY_USERID, consumer.getUserId());
				return "redirect:/consumer/index";
			} else {
				return "consumer_login";
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误.";
			model.addAttribute("message", msg);
			System.out.println(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多.";
			model.addAttribute("message", msg);
			System.out.println(msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定.";
			model.addAttribute("message", msg);
			System.out.println(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用.";
			model.addAttribute("message", msg);
			System.out.println(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期.";
			model.addAttribute("message", msg);
			System.out.println(msg);
		} catch (UnknownAccountException e) {
			msg = "帐号不存在.";
			model.addAttribute("message", msg);
			System.out.println(msg);
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！";
			model.addAttribute("message", msg);
			System.out.println(msg);
		}
		
		return "consumer_login";
	}
	
}
