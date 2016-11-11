package com.chuove.app.cms.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chuove.app.cms.code.ActionStatus;
import com.chuove.app.cms.code.Settings;
import com.chuove.app.cms.common.context.RootContext;
import com.chuove.app.cms.common.utils.Md5;

@Controller
@RequestMapping("upload")
public class UploadFileController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UploadFileController.class);


	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public void upladImage(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = multipartFile.getOriginalFilename();
		String DEST_DIR = RootContext.instance().getString(Settings.DEST_DIR);
		String destDir = DEST_DIR + RootContext.instance().getString(Settings.UPLOAD_IMAGE_FILE_DIR);
		File destFile = new File(destDir);
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		String fileNameNew = Md5.encode(System.currentTimeMillis() + path) + path.substring(path.lastIndexOf("."));
		File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
		try {
			multipartFile.transferTo(f);
			f.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e.getCause());
		}
		String fileName = RootContext.instance().getString(Settings.UPLOAD_IMAGE_FILE_DIR) + "/" + fileNameNew;
		setStatus(ActionStatus.SUCCESS, "success");
		addData("url", fileName);
		renderData(response);
	}

}
