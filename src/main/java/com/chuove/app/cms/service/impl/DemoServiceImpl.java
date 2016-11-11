package com.chuove.app.cms.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuove.app.cms.dao.IDemoDAO;
import com.chuove.app.cms.model.Demo;
import com.chuove.app.cms.service.IDemoService;

@Service
@Transactional
public class DemoServiceImpl implements IDemoService {
	@Autowired
	private IDemoDAO demoDAO;

	@Override
	public List<Demo> getAllDemo() {
		return demoDAO.getAllDemo();
	}

	@Override
	public void demoAdd(Demo demo) {
		demoDAO.demoAdd(demo);
	}

	@Override
	public Demo findDemoById(Integer id) {
		return demoDAO.findDemoById(id);
	}

	@Override
	public void deleteDemoByDemoId(Integer id) {
		demoDAO.deleteDemoByDemoId(id);
	}

	@Override
	public void updateDemoInfo(Demo demo) {
		demoDAO.updateDemoInfo(demo);
	}
	
}
