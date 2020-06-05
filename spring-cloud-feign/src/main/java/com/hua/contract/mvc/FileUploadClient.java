/**
  * @filename FileUploadClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.contract.mvc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.hua.bean.ResultBean;
import com.hua.configuration.FeignConfig4Provider2;
import com.hua.contract.mvc.FileUploadClient.FileUploadClientFallbackFactory;
import com.hua.util.ExceptionUtil;

import feign.hystrix.FallbackFactory;

/**
 * @type FileUploadClient
 * @description 文件上传
 * @author qianye.zheng
 */
@FeignClient(name = "spring-cloud-provider", 
configuration = FeignConfig4Provider2.class, contextId = "file",
// fallback 和 fallbackFactory 使用其中一个
//, fallback = ProviderFallback.class
url = "http://127.0.0.1:7070",
path = "spring-cloud-provider",
fallbackFactory = FileUploadClientFallbackFactory.class, primary = true
 )
public interface FileUploadClient {

	/**
	 * 
	 * @description 
	 * @param file
	 * @return
	 * @author qianye.zheng
	 */
	@PostMapping(value = "/api/file/simpleUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResultBean simpleUpload(final MultipartFile file);
	/*
	 * @RequestPart("file") 可以不指定，
	 * 无论是通过控制层中转，还是直接构造MultipartFile对象
	 * 因为MultipartFile对象已经设置了此值
	 * 
	 */
	//ResultBean simpleUpload(@RequestPart("file") final MultipartFile file);
	
	/**
	 * 
	 * @type FileUploadClientFallbackFactory
	 * @description 
	 * @author qianye.zheng
	 */
	@Component
	class FileUploadClientFallbackFactory implements  FallbackFactory<FileUploadClient>
	{

		/**
		 * @description 
		 * @param cause
		 * @return
		 * @author qianye.zheng
		 */
		@Override
		public FileUploadClient create(Throwable cause) {
			
			return x -> {
				ResultBean resultBean = new ResultBean();
				resultBean.setSuccess(false);
				resultBean.setMessage(ExceptionUtil.getAllCauseInfo(cause));
				resultBean.setData(x.getOriginalFilename());
				
				return resultBean;
			};
		}
	}
	
}
