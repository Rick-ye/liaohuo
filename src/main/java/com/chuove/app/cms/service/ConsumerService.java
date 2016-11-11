package com.chuove.app.cms.service;

import java.util.List;

import com.chuove.app.cms.model.Consumer;

public interface ConsumerService {
	public List<String> findPhoneNumber();
	
	public Consumer findConsumerByUserId();
	
	public void saveRegistConsumer(Consumer consumer);
	
	public String findPwdByMobileNum(String mobileNum);
	
	public Consumer findConsumerByMobileNum(String mobileNum);
}
