package com.chuove.app.cms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.search.IntegerComparisonTerm;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chuove.app.cms.code.ActionStatus;
import com.chuove.app.cms.code.ConsumerSex;
import com.chuove.app.cms.code.SecurityCode;
import com.chuove.app.cms.code.SessionKey;
import com.chuove.app.cms.common.utils.FileUploadUtil;
import com.chuove.app.cms.common.utils.ImgCutUtil;
import com.chuove.app.cms.common.utils.Md5;
import com.chuove.app.cms.common.utils.StringUtils;
import com.chuove.app.cms.common.utils.VeriCodeUtil;
import com.chuove.app.cms.model.Consumer;
import com.chuove.app.cms.model.ConsumerAddr;
import com.chuove.app.cms.service.ConsumerService;

@Controller
@RequestMapping(value="consumer")
public class ConsumerInfoController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ConsumerInfoController.class);
	
	@Autowired
	private ConsumerService consumerService;
	
	private static String MSG=VeriCodeUtil.getRandNum(6);
	private static String NUM=MSG.substring(11);
	
	@RequestMapping(value="grxx")
	public String addBank(){
		return "grxx";
	}
	
	@RequestMapping(value="modifypwd")
	public String modifyPwd(){
		return "modifypwd";
	}
	
	@RequestMapping(value="address2")
	public String address2(){
		return "address2";
	}
	
	@RequestMapping(value="address")
	public String address(ModelMap model){
		return "address";
	}
	
	@RequestMapping(value="grsz")
	public String grsz(){
		return "grsz";
	}
	
	@RequestMapping(value="headImg")
	public String headImg(HttpServletRequest request, 
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "x") String x,
            @RequestParam(value = "y") String y,
            @RequestParam(value = "h") String h,
            @RequestParam(value = "w") String w,
            @RequestParam(value = "uploadImg") MultipartFile uploadImg) {
		try {
			//获得真实绝对路径
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String resourcePath = "resources/headImgs/";
			if(uploadImg != null){
				//判断文件的类型是否允许，uploadImg.getContentType()获得文件的类型
				if(FileUploadUtil.allowUpload(uploadImg.getContentType())){
					//对文件重命名，uploadImg.getOriginalFilename()是获得原来文件的名字
					String fileName = FileUploadUtil.rename(uploadImg.getOriginalFilename());
					int end = fileName.lastIndexOf(".");
					String saveName = fileName.substring(0, end);
					File dir = new File(realPath + resourcePath);
					if(!dir.exists()){
						dir.mkdirs();
					}
					//将图片名字保存到dir目录下
					File file = new File(dir,saveName+"_src.jpg");
					//将上传的文件写到指定的文件下
					uploadImg.transferTo(file);
					String srcImgPath = realPath + resourcePath + saveName;
					//System.out.println(srcImgPath);
					int imgX = Integer.parseInt(x);
					int imgY = Integer.parseInt(y);
					int imgW = Integer.parseInt(w);
					int imgH = Integer.parseInt(h);
					ImgCutUtil.imgCut(srcImgPath, imgX, imgY, imgW, imgH);
					String userImg = resourcePath+saveName+"_cut.jpg";
					consumerService.saveUserImg(userImg);
					model.put("cutImg", userImg);
				}
			}
		} catch (Exception e) {
			logger.error("error", e);
		}
		return "grxx";
	}
	
	@RequestMapping(value="personinfo")
	public void savePersonInfo(HttpServletRequest request,HttpServletResponse response){
		//String userImg = request.getParameter("cutImgUrl");
		String cardId = request.getParameter("cardId");
		String userName = request.getParameter("nickName");
		String userSex = request.getParameter("sex");

		Consumer consumer = new Consumer();
		consumer.setUserName(userName);
		try {
			if(ConsumerSex.MALE.getKey().equals(userSex)){
				consumer.setUserSex(ConsumerSex.MALE.getValue());
			}else{
				consumer.setUserSex(ConsumerSex.FEMALE.getValue());
			}
			if(StringUtils.isBlank(cardId)){
				consumerService.savePersonInfo(consumer);
			}else{
				consumer.setCardId(cardId);
				consumerService.savePersonInfoSec(consumer);
			}
		} catch (Exception e) {
			setStatus(ActionStatus.FAIL,"保存失败");
			logger.error("error", e);
		}
		setStatus(ActionStatus.SUCCESS);
		renderData(response);
		
	}
	
	@RequestMapping(value="getvericode")
	public void getVeriCode(String mobileNum,HttpServletResponse response){
		System.out.println("abc");
		boolean exist = false;
		try {
			List<String> list = new ArrayList<String>();
			list = consumerService.findMobileNum();
			
			for(String num:list){
				if(mobileNum.equals(num)){
					exist = true;
					System.out.println(NUM);
					VeriCodeUtil.sendSms(SecurityCode.APIKEY, MSG, mobileNum);
				}
			}
			if(! exist){
				setStatus(ActionStatus.FAIL, "手机号不存在");
			}
			setStatus(ActionStatus.SUCCESS);
			renderData(response);
		} catch (Exception e) {
			logger.error("error", e);
		}
	}
	
	@RequestMapping(value="updatepwd")
	public void updatePwd(HttpServletRequest request,HttpServletResponse response){
		String mobileNum = request.getParameter("mobileNum");
		String veriCode = request.getParameter("veriCode");
		String userPwd = request.getParameter("userPwd");
		String payPwd = request.getParameter("payPwd");
		try {
			if(!NUM.equals(veriCode)){
				setStatus(ActionStatus.FAIL, "验证码错误");
				renderData(response);
				return;
			}
			Consumer consumer = new Consumer();
			consumer.setMobileNum(mobileNum);
			consumer.setUserPwd(Md5.encode(userPwd));
			consumer.setPayPwd(Md5.encode(payPwd));
			consumerService.updatePwd(consumer);
			setStatus(ActionStatus.SUCCESS, "修改成功");
		} catch (Exception e) {
			logger.error("error", e);
			setStatus(ActionStatus.FAIL, "修改失败");
		}
		renderData(response);
	}
	
	@RequestMapping(value="saveAddr")
	public void saveAddr(HttpServletRequest request, HttpServletResponse response){
		try {
			String addUserName = request.getParameter("addUserName");
			String addMobile = request.getParameter("addMobile");
			String addPrvn = request.getParameter("addPrvn");
			String addCity = request.getParameter("addCity");
			String addCnty = request.getParameter("addCnty");
			String addInfo = request.getParameter("addInfo");
			Subject subject = SecurityUtils.getSubject();
			Object userId = subject.getSession().getAttribute(SessionKey.KEY_USERID);
			ConsumerAddr addr = new ConsumerAddr();
			addr.setUserId((Integer)userId);
			addr.setAddUserName(addUserName);
			addr.setAddMobile(addMobile);
			addr.setAddPrvn(addPrvn);
			addr.setAddCity(addCity);
			addr.setAddCnty(addCnty);
			addr.setAddInfo(addInfo);
			addr.setAddStatus(1);
			addr.setCreateTime(new Date());
			consumerService.saveConsumerAddr(addr);
			setStatus(ActionStatus.SUCCESS);
		} catch (Exception e) {
			logger.error("error", e);
			setStatus(ActionStatus.FAIL, "保存失败");
		}
		renderData(response);
	}
	
	/*@RequestMapping(value="demoSubject")
	public void demoSubject(HttpServletRequest req, HttpServletResponse res){
		Subject subject = SecurityUtils.getSubject();
		Object o =	subject.getSession().getAttribute(SessionKey.KEY_USERID);
		logger.info("demoSubject ="+(Integer)o);
	}*/
}






