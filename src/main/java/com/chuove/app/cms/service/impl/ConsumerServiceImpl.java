package com.chuove.app.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuove.app.cms.dao.ConsumerDAO;
import com.chuove.app.cms.model.Consumer;
import com.chuove.app.cms.model.ConsumerAddr;
import com.chuove.app.cms.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	private ConsumerDAO consumerDAO;
	

	@Override
	public List<String> findMobileNum() {
		return consumerDAO.findMobileNum();
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

	@Override
	public void savePersonInfo(Consumer consumer) {
		consumerDAO.savePersonInfo(consumer);
	}
	@Override
	public void savePersonInfoSec(Consumer consumer) {
		consumerDAO.savePersonInfoSec(consumer);
	}
	
	@Override
	public void saveUserImg(String userImg) {
		consumerDAO.saveUserImg(userImg);
	}

	@Override
	public void updatePwd(Consumer consumer) {
		consumerDAO.updatePwd(consumer);
	}

	@Override
	public void saveConsumerAddr(ConsumerAddr addr) {
		consumerDAO.saveConsumerAddr(addr);
	}


	
	/*
	dao -> mysql->sql->table 自动（单）事务处理autocommit(insert,update,delete)
	java object <-mybatis-> db sql string
	dao层不需要关心多表之间的业务关联性
	
	service biz/业务
	由相关的dao组成或者其他资源组成
	aservice.java (dao1-insert,dao2-update,dao3-select...) db事务处理，程序化事务处理
	
	mvc
	controller mvc中的c，不处理业务，仅仅处理view-ftl与model-bean的关联
	view -> ftl/freemarker
	
	*/
}
