package com.chuove.app.cms.dao;

import java.util.List;

import com.chuove.app.cms.model.Consumer;

public interface ConsumerDAO {
	public List<String> findPhoneNumber();
	
	public Consumer findConsumerByUserId(int userId);
	//app注册时，只保存手机号和密码
	public void saveRegistConsumer(Consumer consumer);
	//保存所有字段
	public void saveConsumer(Consumer consumer);
	
	public String findPwdByMobileNum(String mobileNum);
	
	public Consumer findConsumerByMobileNum(String mobileNum);
}
