package com.chuove.app.cms.service;

import java.util.List;

import com.chuove.app.cms.model.Consumer;

public interface ConsumerService {
	public List<String> findMobileNum();
	
	public Consumer findConsumerByUserId();
	
	public void saveRegistConsumer(Consumer consumer);
	
	public String findPwdByMobileNum(String mobileNum);
	
	public Consumer findConsumerByMobileNum(String mobileNum);
	
	public void savePersonInfo(Consumer consumer);
	public void savePersonInfoSec(Consumer consumer);
	
	public void saveUserImg(String userImg);
	//修改登录密码和支付密码
	public void updatePwd(Consumer consumer);
}
