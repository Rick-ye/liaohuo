package com.chuove.app.cms.dao;

import java.util.List;

import com.chuove.app.cms.model.Consumer;

public interface ConsumerDAO {
	public List<String> findMobileNum();
	
	public Consumer findConsumerByUserId(int userId);
	//app注册时，只保存手机号和密码
	public void saveRegistConsumer(Consumer consumer);
	//保存所有字段
	public void saveConsumer(Consumer consumer);
	
	public String findPwdByMobileNum(String mobileNum);
	
	public Consumer findConsumerByMobileNum(String mobileNum);
	//保存昵称，性别  
	public void savePersonInfo(Consumer consumer);
	//保存昵称，性别，身份证号
	public void savePersonInfoSec(Consumer consumer);
	//修改头像
	public void saveUserImg(String userImg);
	//修改登录密码和支付密码
	public void updatePwd(Consumer consumer);
}
