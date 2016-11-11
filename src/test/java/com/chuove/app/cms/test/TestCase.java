package com.chuove.app.cms.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chuove.app.cms.controller.DemoController;
import com.chuove.app.cms.dao.ConsumerDAO;
import com.chuove.app.cms.dao.IDemoDAO;
import com.chuove.app.cms.model.Consumer;
import com.chuove.app.cms.model.Demo;
import com.chuove.app.cms.service.IDemoService;

public class TestCase {
	
	ApplicationContext ac;
	IDemoDAO dao;
	ConsumerDAO condao;
	
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("classpath:spring-orm.xml");
		dao = ac.getBean("demoDAO",IDemoDAO.class);
		condao = ac.getBean("consumerDAO",ConsumerDAO.class);
	}
	
	@Test
	public void testSaveRegistConsumer(){
		Consumer con = new Consumer();
		con.setMobileNum("00000000000");
		con.setUserPwd("rrrrrrrrrrrrr");
		condao.saveRegistConsumer(con);
	}
	
	@Test
	public void testDemoDoAdd(){
		Demo demo = new Demo();
		demo.setId(2);
		demo.setName("Rick");
		demo.setSex("男");
		
		dao.demoAdd(demo);
	}
	
	@Test
	public void testFindDemoById(){
		Demo list = dao.findDemoById(1);
		
		System.out.println(list.toString());
	}
	
	@Test
	public void testdeleteDemoByDemoId(){
		dao.deleteDemoByDemoId(11);
	}
	
	@Test
	public void testupdateDemoInfo(){
		Demo demo = new Demo();
		demo = dao.findDemoById(13);
		demo.setName("mark");
		demo.setSex("f");
		dao.updateDemoInfo(demo);
	}
	
	@Test
	public void testfindbn(){
		/*List<String> list = new ArrayList<String>();
		list = condao.findPhoneNumber();
		for(String num:list){
			System.out.println(num);
		}*/
		
		Consumer con = condao.findConsumerByUserId(1);
		System.out.println(con.toString());
	}
	
	/*@Test
	public void test(){
		//contextInitialized();
	}
	@Test
	public void test(ServletContextEvent servlet){
		ServletContext context = servlet.getServletContext();
		String paramValue = context.getInitParameter("log4jConfigLocation");
		System.out.println(paramValue);
	}*/
}
