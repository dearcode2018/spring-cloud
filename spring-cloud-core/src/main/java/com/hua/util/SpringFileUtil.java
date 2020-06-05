/**
 * SpringFileUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * SpringFileUtil
 * 描述: Spring 文件工具
 * @author  qye.zheng
 */
public final class SpringFileUtil
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private SpringFileUtil()
	{
	}

	/**
	 * 注意: getMultipartFile调用一次之后，后续调用再不再有效
	 */
	/**
	 * 
	 * @description 根据name获取单个MultipartFile对象
	 * 适用于单个文件上传的场景
	 * @param request
	 * @param name multipart中name值
	 * @return
	 * @author qianye.zheng
	 */
	public static final MultipartFile getMultipartFile(final HttpServletRequest request, 
			final String name)
	{
		MultipartFile multipartFile = null;
		/*
		 * multipart 解析器 - 解析文件
		 * 可以通过 IOC 注入，配置相关属性
		 */
		final MultipartResolver multipartResolver = new CommonsMultipartResolver();
		if (multipartResolver.isMultipart(request))
		{ // multipart 类型
			/**
			 * 注意: 方式1通过强转的方式抛异常: 
			 * cannot be cast to MultipartHttpServletRequest
			 * 可以通过方式2，使用Multipart解析器来解析
			 */
			// 方式1: 强转为 MultipartHttpServletRequest
			//final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 方式2: 
			final MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
			multipartFile = multipartRequest.getFile(name);
		} else
		{
			System.out.println("不是 multipart 类型， 不作处理.");
		}
		
		return multipartFile;
	}	
	
	/**
	 * 
	 * @description 根据name获取多个MultipartFile对象
	 * 适用于文件上传中name值相同的场景
	 * @param request
	 * @param name multipart中name值
	 * @return
	 * @author qianye.zheng
	 */
	public static final List<MultipartFile> listMultipartFile(final HttpServletRequest request, 
			final String name)
	{
		List<MultipartFile> multipartFiles = null;
		/*
		 * multipart 解析器 - 解析文件
		 * 可以通过 IOC 注入，配置相关属性
		 */
		final MultipartResolver multipartResolver = new CommonsMultipartResolver();
		if (multipartResolver.isMultipart(request))
		{ // multipart 类型
			/**
			 * 注意: 方式1通过强转的方式抛异常: 
			 * cannot be cast to MultipartHttpServletRequest
			 * 可以通过方式2，使用Multipart解析器来解析
			 */
			// 方式1: 强转为 MultipartHttpServletRequest
			//final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 方式2: 
			final MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
			multipartFiles = multipartRequest.getFiles(name);
		} else
		{
			System.out.println("不是 multipart 类型， 不作处理.");
		}
		
		return multipartFiles;
	}

	/**
	 * 
	 * @description 根据name获取多个MultipartFile对象
	 * 适用于文件上传中name值各不相同的场景
	 * @param request
	 * @return
	 * @author qianye.zheng
	 */
	public static final Map<String, MultipartFile> getMultipartFileMap(final HttpServletRequest request)
	{
		Map<String, MultipartFile> multipartFileMap = null;
		/*
		 * multipart 解析器 - 解析文件
		 * 可以通过 IOC 注入，配置相关属性
		 */
		final MultipartResolver multipartResolver = new CommonsMultipartResolver();
		if (multipartResolver.isMultipart(request))
		{ // multipart 类型
			/**
			 * 注意: 方式1通过强转的方式抛异常: 
			 * cannot be cast to MultipartHttpServletRequest
			 * 可以通过方式2，使用Multipart解析器来解析
			 */
			// 方式1: 强转为 MultipartHttpServletRequest
			//final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 方式2: 
			final MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
			multipartFileMap = multipartRequest.getFileMap();
		} else
		{
			System.out.println("不是 multipart 类型， 不作处理.");
		}
		
		return multipartFileMap;
	}	
	
	/**
	 * 
	 * @description 
	 * @param multipartFile
	 * @param destFile
	 * @return
	 * @author qianye.zheng
	 */
	public static final boolean store(final MultipartFile multipartFile, final File destFile)
	{
		boolean flag = false;
		try {
			multipartFile.transferTo(destFile);
			flag = true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return flag;
	}
}
