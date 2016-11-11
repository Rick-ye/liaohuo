package com.chuove.app.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuove.app.cms.dao.ConsumerDAO;
import com.chuove.app.cms.model.Consumer;
import com.chuove.app.cms.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	private ConsumerDAO consumerDAO;

	@Override
	public List<String> findPhoneNumber() {
		return consumerDAO.findPhoneNumber();
	}

	@Override
	public Consumer findConsumerByUserId() {
		return null;
	}

	@Override
	public void saveRegistConsumer(Consumer consumer) {
		consumerDAO.saveRegistConsumer(consumer);
	}

	@Override
	public String findPwdByMobileNum(String mobileNum) {
		return consumerDAO.findPwdByMobileNum(mobileNum);
	}

	@Override
	public Consumer findConsumerByMobileNum(String mobileNum) {
		return consumerDAO.findConsumerByMobileNum(mobileNum);
	}
	
}
