package com.chuove.app.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chuove.app.cms.code.ActionStatus;
import com.chuove.app.cms.code.SessionKey;
import com.chuove.app.cms.common.utils.StringUtils;
import com.chuove.app.cms.model.Demo;
import com.chuove.app.cms.service.IDemoService;
import com.chuove.app.cms.service.impl.DemoServiceImpl;
import com.sun.tools.hat.internal.parser.Reader;

@Controller
@RequestMapping("admin")
 public class DemoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	@Autowired
	private IDemoService demoService;
	
	
	@RequestMapping(value = "demolist")
	public String list(HttpServletRequest request, ModelMap model) {
		try{
			List<Demo> list=demoService.getAllDemo();
			model.put("demoList", list);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return "demo_list";
	}
	
	@RequestMapping(value="demoAdd")
	public String demoAdd(ModelMap model){
		try {
			model.put("demoList", getAllDemo());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "demo_add";
	}
	
	public List<Demo> getAllDemo(){
		List<Demo> list =null ;
		try {
			list = demoService.getAllDemo();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	@RequestMapping(value="demoDoAdd")
	public void demoDoAdd(HttpServletRequest req, HttpServletResponse res){
		try {
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");
			Demo demo = new Demo();
			demo.setName(name);
			demo.setSex(sex);
			demoService.demoAdd(demo);
			setStatus(ActionStatus.SUCCESS, "添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			setStatus(ActionStatus.FAIL,"添加失败");
		}
		renderData(res);
	}
	
	@RequestMapping(value="demoDel", method=RequestMethod.POST)
	@ResponseBody
	public void demoDel(HttpServletRequest req, HttpServletResponse res){
		try {
			Integer id = Integer.valueOf(req.getParameter("id")); 
			Demo demo = demoService.findDemoById(id);
			if(demo != null){
				demoService.deleteDemoByDemoId(demo.getId());
				setStatus(ActionStatus.SUCCESS,"已删除");
			}
		} catch (Exception e) {
			setStatus(ActionStatus.FAIL,"删除失败");
			logger.error("demoDel error",e.getMessage());
		}
		renderData(res);
	}
	
	@RequestMapping(value="demoEdit",method=RequestMethod.GET)
	public String demoEdit(HttpServletRequest req, ModelMap model){
		try {
			Integer id = Integer.valueOf(req.getParameter("id"));
			Demo demo = demoService.findDemoById(id);
			model.put("demo", demo);
		} catch (Exception e) {
			logger.error("demoEdit error", e);
		}
		return "demo_edit";
	}
	
	@RequestMapping(value="demoDoEdit")
	public void demoDoEdit(HttpServletRequest req, HttpServletResponse res){
		try {
			String id = req.getParameter("id");
			if(StringUtils.isBlank(id)){
				setStatus(ActionStatus.FAIL,"demo不存在");
			}
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");
			Demo demo = demoService.findDemoById(Integer.valueOf(id));
			
			demo.setName(name);
			demo.setSex(sex);
			demoService.updateDemoInfo(demo);
			setStatus(ActionStatus.SUCCESS,"修改成功");
		} catch (Exception e) {
			logger.error("demoDoEdit error", e);
			setStatus(ActionStatus.FAIL, "修改失败");
		}
		renderData(res);
	}
	
	@RequestMapping(value="demoSubject")
	public void demoSubject(HttpServletRequest req, HttpServletResponse res){
		Subject subject = SecurityUtils.getSubject();
		//if(subject.isAuthenticated()){
		Object o =	subject.getSession().getAttribute(SessionKey.KEY_USERID);
		logger.info("demoSubject ="+(Integer)o);
		//}
	}
}












