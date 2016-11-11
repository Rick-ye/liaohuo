package com.chuove.app.cms.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.chuove.app.cms.model.UserDo;
import com.chuove.app.cms.service.IUserService;

@Controller
@RequestMapping(value = "admin")
public class LoginController extends BaseController{
	private static Logger logger=LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value = { "/", "" })
	public String login() {
		try{
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				return "redirect:/admin/index";
			}
		}catch(Exception e){
			logger.error("error", e);
		}
		return "admin_login";
	}

	@RequestMapping(value = "index")
	public String index(ModelMap model) {
		try{
			Subject subject = SecurityUtils.getSubject();
			if (!subject.isAuthenticated()) {
				return "admin_login";
		} else {
			UserDo userDo=userService.findUserByUserName(subject.getPrincipal().toString());
			model.put("ud", userDo);
		}
		}catch(Exception e){
			logger.error("error", e);
		}
		return "admin_index";
	}

	/**
	 * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "doLogin")
	public String doLogin(HttpServletRequest request, Model model) {
		String msg = "";
		String userName = request.getParameter("userName");
		if (StringUtils.isBlank(userName)) {
			return "admin_login";
		}
		String password = request.getParameter("userPwd");
		String remindMe = request.getParameter("remindMe");
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		token.setRememberMe(StringUtils.isBlank(remindMe) ? false : true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				UserDo ud = userService.findUserByUserName(userName);
				subject.getSession().setAttribute(SessionKey.KEY_USER, ud);
				subject.getSession().setAttribute(SessionKey.KEY_USERID, ud.getUserId());
				return "redirect:/admin/index";
			} else {
				return "admin_login";
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
		return "admin_login";
	}

	@RequestMapping(value = "logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		return "admin_login";
	}
}
