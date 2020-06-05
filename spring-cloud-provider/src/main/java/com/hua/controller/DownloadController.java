/**
  * @filename DownloadController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hua.bean.ResultBean;
import com.hua.constant.UriConstant;
import com.hua.util.ClassPathUtil;

 /**
 * @type DownloadController
 * @description 文件下载 控制器
 * @author qye.zheng
 */
//控制器
@Controller
@RequestMapping(value={UriConstant.API + "file"})
public class DownloadController extends FileController {

	/**
	 * 
	 * @description 文件下载 v1
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/download/v1"})
	@ResponseBody
	public OutputStream downloadV1(final HttpServletRequest request, 
			final HttpServletResponse response)
	{
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try
		{
			outputStream = response.getOutputStream();
			String path = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true);
			inputStream = new FileInputStream(path);
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(inputStream);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @description 文件下载 v2
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/download/v2"})
	//@ResponseBody
	public void downloadV2(final HttpServletRequest request, 
			final HttpServletResponse response)
	{
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try
		{
			outputStream = response.getOutputStream();
			String path = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true);
			inputStream = new FileInputStream(path);
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(inputStream);
		}
	}
	
	/**
	 * 
	 * @description 文件下载 v3
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/download/v3"})
	//@ResponseBody
	public void downloadV3(final HttpServletRequest request, 
			final HttpServletResponse response)
	{
		/**
		 * 设置头信息, 把文件名
		 */
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try
		{
			outputStream = response.getOutputStream();
			String path = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true);
			String filename = path.substring(path.lastIndexOf("/") + 1);
			// 内容类型
			response.setContentType("application/x-download;charset=UTF-8");
			// 文件名
			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
			inputStream = new FileInputStream(path);
			// 文件大小
			response.setContentLengthLong(inputStream.available());
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{ // 关闭流
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(inputStream);
		}
	}
	
	/**
	 * 
	 * @description 文件下载 v5
	 * @param request
	 * @param response
	 * @param path 文件路径 (基于当前服务器)
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/download/v5"})
	@ResponseBody
	public ResultBean downloadV5(final HttpServletRequest request, 
			final HttpServletResponse response, final String path)
	{
		/*
		 * 根据文件路径, 响应一个实体数据, 实体数据中含有文件下载的url, 客户端直接用url自动
		 * 下载文件
		 */
		return null;
	}
	
	/**
	 * 
	 * @description 文件下载 v6
	 * @param path 文件路径 (基于当前服务器)
	 * @return 文件下载信息，文件名、url...
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/download/v6"})
	@ResponseBody
	public String downloadV6(final String path)
	{
		//
		return null;
	}
}
