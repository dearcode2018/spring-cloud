/**
  * @filename UploadController.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hua.bean.ResultBean;
import com.hua.constant.Constant;
import com.hua.constant.UriConstant;
import com.hua.entity.User;
import com.hua.util.FileUtil;
import com.hua.util.SpringFileUtil;
import com.hua.util.WebPathUtil;

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
		try {
			final String path = uploadPath  + file.getOriginalFilename();
			file.transferTo(new File(path));
			resultBean.setMessage("文件保存在: " + path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultBean;
	}	
	
	/**
	 * 
	 * @description 文件上传 v1
	 * 可支持多个文件上传，多个的name值相同
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v1"})
	public ResultBean uploadA(final HttpServletRequest request, 
			final HttpServletResponse response)
	{
		// 存储文件
		File destFile = null;
		String path = null;
		List<MultipartFile> multipartFiles = SpringFileUtil.listMultipartFile(request, NAME);
		for (MultipartFile e : multipartFiles)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") 
					+ File.separator + FileUtil.coverTimestamp(e.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(e, destFile);
		}
		
		return null;
	}

	/**
	 * 
	 * @description 文件上传 v1
	 * 可支持多个文件上传，多个的name值各不相同
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v2"})
	public ResultBean uploadB(final HttpServletRequest request, 
			final HttpServletResponse response)
	{
		Map<String, MultipartFile> multipartFileMap = SpringFileUtil.getMultipartFileMap(request);
		MultipartFile multipartFile1 =  multipartFileMap.get(NAME1);
		MultipartFile multipartFile2 =  multipartFileMap.get(NAME2);
		
		// 存储文件
		File destFile = null;
		String path = null;
		if (null != multipartFile1)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile1.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(multipartFile1, destFile);
		}
		if (null != multipartFile2)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile2.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(multipartFile2, destFile);	
		}
		
		
		return null;
	}		
	
	/**
	 * 
	 * @description 文件上传 v1
	 * 可支持多个文件上传，多个的name值各不相同
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	//@RequestMapping(value={"/upload/v1b"})
	//
	public ResultBean uploadBDeprecated(final HttpServletRequest request, 
			final HttpServletResponse response)
	{
		/**
		 * getMultipartFile调用一次之后，后续调用再不再有效
		 */
		MultipartFile multipartFile1 =  SpringFileUtil.getMultipartFile(request, NAME1);
		MultipartFile multipartFile2 =  SpringFileUtil.getMultipartFile(request, NAME2);
		
		// 存储文件
		File destFile = null;
		String path = null;
		if (null != multipartFile1)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile1.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(multipartFile1, destFile);
		}
		if (null != multipartFile2)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile2.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(multipartFile2, destFile);	
		}
		
		
		return null;
	}	
	
	/**
	 * v2/ v3 / v4 方式，需要在IOC中声明 CommonsMultipartResolver,
	 * 然后可以通过参数注入的方式直接使用CommonsMultipartFile对象
	 * 
	 * 多个文件可以通过声明多个CommonsMultipartFile 或 通过数组的方式来实现.
	 * 
	 */
	
	/**
	 * 
	 * @description 文件上传 v2
	 * 支持单个文件上传
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v3"})
	
	public ResultBean upload(final HttpServletRequest request, 
			final HttpServletResponse response, 
			@RequestParam(value = "file" ,required = false) final MultipartFile multipartFile)
	{
		//CommonsMultipartResolver
		// 存储文件
		File destFile = null;
		String path = null;
		if (null != multipartFile)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile.getOriginalFilename());
			destFile = new File(path);
			System.out.println(path);
			SpringFileUtil.store(multipartFile, destFile);
		}
		
		return null;
	}

	/**
	 * v3方式上传多个文件，通过指定多个name的方式而
	 * 不是数组，这种方式比较明确，不会导致处理模糊.
	 */
	/**
	 * 
	 * @description 文件上传 v3
	 * 支持多个文件上传
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v4"})
	
	public ResultBean upload(final HttpServletRequest request, 
			final HttpServletResponse response, 
			@RequestParam(value = "file1" ,required = false) final MultipartFile multipartFile1,
			@RequestParam(value = "file2" ,required = false) final MultipartFile multipartFile2)
	{
		//CommonsMultipartResolver
		File destFile = null;
		String path = null;
		if (null != multipartFile1)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile1.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(multipartFile1, destFile);
		}
		
		if (null != multipartFile2)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile2.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(multipartFile2, destFile);
		}
		
		return null;
	}	
	
	/**
	 * v4的场景对于上传多个同类文件比较适用，而不用
	 * 单独去处理单个文件，因此使用统一的name值
	 */
	/**
	 * 
	 * @description 文件上传 v4
	 * 支持多个文件上传
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v5"})
	
	public ResultBean upload(final HttpServletRequest request, 
			final HttpServletResponse response, 
			@RequestParam(value = "file" ,required = false) final MultipartFile[] multipartFiles)
	{
		// 存储文件
		File destFile = null;
		String path = null;
		for (MultipartFile e : multipartFiles)
		{
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") 
					+ File.separator + FileUtil.coverTimestamp(e.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(e, destFile);
		}
		
		return null;
	}
	

	/**
	 * v3方式上传多个文件，通过指定多个name的方式而
	 * 不是数组，这种方式比较明确，不会导致处理模糊.
	 */
	/**
	 * 
	 * @description 文件上传 v5
	 * 支持多个文件上传
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v6"})
	public ResultBean upload(final HttpServletRequest request, 
			final HttpServletResponse response, 
			@RequestParam(value = "files1" ,required = false) final MultipartFile[] multipartFiles1,
			@RequestParam(value = "files2" ,required = false) final MultipartFile[] multipartFiles2)
	{
		//CommonsMultipartResolver
		File destFile = null;
		String path = null;
		
		if (null != multipartFiles1)
		{
			for (MultipartFile e : multipartFiles1)
			{
				path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") 
						+ File.separator + FileUtil.coverTimestamp(e.getOriginalFilename());
				destFile = new File(path);
				SpringFileUtil.store(e, destFile);
			}	
		}
		
		if (null != multipartFiles2)
		{
			for (MultipartFile e : multipartFiles2)
			{
				path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") 
						+ File.separator + FileUtil.coverTimestamp(e.getOriginalFilename());
				destFile = new File(path);
				SpringFileUtil.store(e, destFile);
			}	
		}
		
		return null;
	}
	
	/**
	 * 
	 * @description 文件上传 v6
	 * 支持多个文件上传
	 * 上传文件，同时携带其他参数
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v7"})
	public ResultBean upload6(final HttpServletRequest request, 
			final HttpServletResponse response,
			@RequestParam(value = "files1" ,required = false) final MultipartFile[] multipartFiles1,
			@RequestParam(value = "files2" ,required = false) final MultipartFile[] multipartFiles2, 
			final User user)
	{
		System.out.println(user.toString());
		//CommonsMultipartResolver
		File destFile = null;
		String path = null;
		
		if (null != multipartFiles1)
		{
			for (MultipartFile e : multipartFiles1)
			{
				path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") 
						+ File.separator + FileUtil.coverTimestamp(e.getOriginalFilename());
				destFile = new File(path);
				SpringFileUtil.store(e, destFile);
			}	
		}
		
		if (null != multipartFiles2)
		{
			for (MultipartFile e : multipartFiles2)
			{
				path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") 
						+ File.separator + FileUtil.coverTimestamp(e.getOriginalFilename());
				destFile = new File(path);
				SpringFileUtil.store(e, destFile);
			}	
		}
		
		return null;
	}	
	
    /** 
     * 
     * 
     * @param request 
     * @return 
     * @throws Exception 
     */  
	/**
	 * 
	 * @description 图片上传 
	 * 兼容旧的提交方式: 
	 * Content-Type:application/x-www-form-urlencoded; 
	 * @param request
	 * @param response
	 * @author qianye.zheng
	 */
    @RequestMapping  
    public void imageSend(HttpServletRequest request,HttpServletResponse response){  
        try {  
            request.setCharacterEncoding(Constant.CHART_SET_UTF_8);  
            // 获取文件数据_base64加密字符串  
            String data = request.getParameter("content");    
            log.info("imageSend =====>解密前 data = " + data);  
              
            final byte[] images = parseImageData(data);  
            log.info("imageSend =====>解密后 data = " + data);  
              
/*            String filePath = FileUpload.saveImage(request, images);  
            log.info("imageSend =====>文件长路径  = " + filePath);  
            // 返回文件路径  
            response.setContentType("text/json;charset=UTF-8");  
            response.getWriter().print(filePath);  
            response.getWriter().flush();  
            response.getWriter().close();  */
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    } 	

    /**
     * 
     * @description 解析base64编码后的图片数据 
     * @param data
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @author qianye.zheng
     */
    public static byte[] parseImageData(String data)  
            throws UnsupportedEncodingException, IOException {  
        /** begin 数据编码与转义 */  
        // url 编码, 编码之后,初步具备解码条件  
        data = URLEncoder.encode(data,  Constant.CHART_SET_UTF_8);  
        // 正则: 斜杠转义  
        final String regex = "\\%2[F|f]";  
        // 替换完成之后,尾部还有剩余后缀,不影响图片主体解析  
        data = data.replaceAll(regex, "/");  
        /** end of 数据编码与转义 */  
          
        // base64解码  
        final byte[] images = Base64Utils.decode(data.getBytes());
        // 解码出的字节长度 (略大于文件实际长度,大约2个字节)  
        //log.info("imageSend =====>文件长度 length = " + images.length);  
        return images;  
    }  
    
    /**
	 * 
	 * @description 文件上传 v2
	 * 支持单个文件上传
	 * @param request
	 * @param response
	 * @return
	 * @author qianye.zheng
	 */
	@RequestMapping(value={"/upload/v10"})
	public ResultBean uploadV10(final HttpServletRequest request, 
			final HttpServletResponse response, 
			@RequestParam(value = "file" ,required = false) final MultipartFile multipartFile)
	{
		//CommonsMultipartResolver
		// 存储文件
		File destFile = null;
		String path = null;
		if (null != multipartFile)
		{
			String originalFilename = null;
			try
			{
				originalFilename = multipartFile.getOriginalFilename();
				//	OriginalFilename = URLDecoder.decode(multipartFile.getOriginalFilename(), Constant.CHART_SET_UTF_8);
				//originalFilename = URLEncoder.encode(multipartFile.getOriginalFilename(), Constant.CHART_SET_UTF_8);
				originalFilename = new String(originalFilename.getBytes(Constant.CHART_SET_GBK), Constant.CHART_SET_UTF_8);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			System.out.println("OriginalFilename = " + originalFilename);
			path = WebPathUtil.getWebSubpath(request.getServletContext(), "/file/upload") + File.separator 
					+ FileUtil.coverTimestamp(multipartFile.getOriginalFilename());
			destFile = new File(path);
			SpringFileUtil.store(multipartFile, destFile);
		}
		
		return null;
	}
    
}
