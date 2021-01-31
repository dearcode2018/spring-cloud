/**
  * @filename SomeController.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hua.bean.ResultBean;
import com.hua.entity.User;
import com.hua.feign.SomeFeignClient;
import com.hua.util.FileUtil;
import com.hua.util.IOUtil;

 /**
 * @type SomeController
 * @description 
 * @author qianye.zheng
 */
@RestController
@RequestMapping("/some")
public class SomeController extends BaseController implements  SomeFeignClient
{
    
    /**
     * @description 
     * @param content
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/get")
    @Override
    public ResultBean get(String content) {
        ResultBean resultBean = new ResultBean();
        resultBean.setId("get-20180826");
        resultBean.setMessage("内容: " + content);
        resultBean.setSuccess(true);
        resultBean.setMessageCode("200");
        
        return resultBean;
    }

    /**
     * @description 
     * @param param
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/get-many")
    @Override
    public ResultBean getMany(User param) {
        ResultBean resultBean = new ResultBean();
        resultBean.setId("get-many-20180826");
        resultBean.setMessage("内容: " + param.getUsername());
        resultBean.setSuccess(true);
        resultBean.setMessageCode("200");
        
        return resultBean;
    }

    /**
     * @description 
     * @param param
     * @return
     * @author qianye.zheng
     */
    @PostMapping("/post")
    @Override
    public ResultBean post(User param) {
        ResultBean resultBean = new ResultBean();
        resultBean.setId("post-20180826");
        resultBean.setMessage("内容: " + param.getUsername());
        resultBean.setSuccess(true);
        resultBean.setMessageCode("200");
        
        return resultBean;
    }

    /**
     * @description 
     * @param file
     * @return
     * @author qianye.zheng
     */
    @PostMapping(value = "/file-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Override
    public ResultBean fileUpload(MultipartFile file) {
        ResultBean resultBean = new ResultBean();
        resultBean.setId("fileUpload-20180826");
        resultBean.setMessage("文件名: " + file.getOriginalFilename());
        resultBean.setSuccess(true);
        resultBean.setMessageCode("200");
        try {
            // 存储到本地目录
            FileUtil.writeByteArray(temporaryPath + file.getOriginalFilename(), file.getBytes(), false);
        } catch (Exception e) {
            log.error("文件上传异常: ", e);
        }
        
        return resultBean;
    }

    /**
     * @description 
     * @param filename
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/file-download")
    @Override
    public byte[] fileDownload(String filename) {
        String path = "/file/img/" + filename;
        byte[] arr = null;
        try {
            final InputStream inputStream = getClass().getResourceAsStream(path);
            arr = new byte[inputStream.available()];
            IOUtil.read(inputStream, arr);
        } catch (IOException e) {
            log.error("文件下载异常: ", e);
        }
        return arr;
    }
	
}
