package com.chuove.app.cms.dao;

import java.util.List;

import com.chuove.app.cms.model.Demo;


public interface IDemoDAO {
	public List<Demo> getAllDemo();
	public void demoAdd(Demo demo);
	public Demo findDemoById(Integer id);
	public void deleteDemoByDemoId(Integer id);
	public void updateDemoInfo(Demo demo);
}
