package com.chuove.app.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuove.app.cms.code.ActionStatus;
import com.chuove.app.cms.common.utils.Md5;
import com.chuove.app.cms.common.utils.VeriCodeUtil;
import com.chuove.app.cms.model.Consumer;
import com.chuove.app.cms.service.ConsumerService;

@Controller
@RequestMapping(value="consumer")
public class ConsumerRegistController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ConsumerRegistController.class);

	private static String NUM;
	private static String MSG;
	
	private final static String APIKEY="ff56bbadf674729b8a1301af49b0a230";
	//获得6位验证码
	static{
		MSG = VeriCodeUtil.getRandNum(6);
		NUM = MSG.substring(11);
	}
	
	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping(value="/regist")
	public String regist(){
		
		return "consumer_regist";
	}
	
	@RequestMapping(value="doVeriCode")
	public void getVeriCode(String phoneNumber,HttpServletResponse response){
		try {
			/*if(StringUtils.isBlank(phoneNumber)){
				setStatus(ActionStatus.FAIL, "手机号不能为空");
				renderData(response);
				return;
			}*/
			List<String> list = new ArrayList<String>();
			list = consumerService.findPhoneNumber();
			for(String num:list){
				if(phoneNumber.equals(num)){
					setStatus(ActionStatus.FAIL, "手机号已注册");
					renderData(response);
					return;
				}
			}
			System.out.println(NUM);
			VeriCodeUtil.sendSms(APIKEY, MSG, phoneNumber);
			renderData(response);
		} catch (Exception e) {
			logger.error("error", e);
		}
	}
	
	@RequestMapping(value="doRegist")
	public void doRegist(HttpServletRequest request, HttpServletResponse response){
		String mobileNum = request.getParameter("phoneNumber");
		String veriCode = request.getParameter("veriCode");
		/*if(StringUtils.isBlank(veriCode)){
			setStatus(ActionStatus.FAIL, "验证码不能为空");
			renderData(response);
		}*/
		if(!NUM.equals(veriCode)){
			setStatus(ActionStatus.FAIL, "验证码错误");
			renderData(response);
			return;
		}
		String userPwd = request.getParameter("userPwd");
		/*if(StringUtils.isBlank(userPwd)){
			setStatus(ActionStatus.FAIL, "密码不能为空");
			renderData(response);
		}
		if(!userPwd.equals(request.getParameter("userPwd1"))){
			setStatus(ActionStatus.FAIL, "两次输入的密码不同");
			renderData(response);
			return;
		}*/
		try {
			Consumer consumer = new Consumer();
			consumer.setMobileNum(mobileNum);
			consumer.setUserPwd(Md5.encode(userPwd));
			consumer.setCreateTime(new Date());
			consumerService.saveRegistConsumer(consumer);
		} catch (Exception e) {
			logger.error("error", e);
		}
		setStatus(ActionStatus.SUCCESS, "注册成功");
		renderData(response);
	}
}












