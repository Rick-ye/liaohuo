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
import com.chuove.app.cms.code.SecurityCode;
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
			List<String> list = new ArrayList<String>();
			list = consumerService.findMobileNum();
			for(String num:list){
				if(phoneNumber.equals(num)){
					setStatus(ActionStatus.FAIL, "手机号已注册");
					renderData(response);
					return;
				}
			}
			System.out.println(NUM);
			VeriCodeUtil.sendSms(SecurityCode.APIKEY, MSG, phoneNumber);
			setStatus(ActionStatus.SUCCESS);
			renderData(response);
		} catch (Exception e) {
			logger.error("error", e);
		}
	}
	
	@RequestMapping(value="doRegist")
	public void doRegist(HttpServletRequest request, HttpServletResponse response){
		String mobileNum = request.getParameter("phoneNumber");
		String veriCode = request.getParameter("veriCode");
		if(!NUM.equals(veriCode)){
			setStatus(ActionStatus.FAIL, "验证码错误");
			renderData(response);
			return;
		}
		String userPwd = request.getParameter("userPwd");
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












