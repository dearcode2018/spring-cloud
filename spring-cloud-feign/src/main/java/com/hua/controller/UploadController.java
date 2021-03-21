/**
  * @filename UploadController.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hua.bean.ResultBean;
import com.hua.constant.UriConstant;
import com.hua.mvc.FileUploadClient;

 /**
 * @type UploadController
 * @description 文件上传 控制器
 * @author qye.zheng
 */
//控制器
@RestController
@RequestMapping(UriConstant.API + "file")
public class UploadController extends FileController {

	private static final String NAME = "file";
	
	private static final String NAME1 = "file1";
	
	private static final String NAME2 = "file2";
	
	/* 文件要提前创建好，否则无法正常上传 */
	@Value("${file.upload.path:/data/upload/}")
	private String uploadPath;
	
	@Resource
	private FileUploadClient fileUploadClient;
	
	/**
	 * 
	 * @description 简单文件上传
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	@PostMapping("/simpleUpload")
	public ResultBean simpleUpload(@RequestParam(value = "file" ,required = true) final MultipartFile file)
	{
		final ResultBean resultBean = new ResultBean();
		fileUploadClient.simpleUpload(file);
		
		return resultBean;
	}	

    
}
