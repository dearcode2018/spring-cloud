/**
  * @filename SomeFeignClient.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hua.bean.ResultBean;
import com.hua.configuration.DefaultFeignConfig;
import com.hua.entity.User;
import com.hua.feign.SomeFeignClient.SomeFallbackFactory;
import com.hua.util.JacksonUtil;

/**
 * @type SomeFeignClient
 * @description 
 * @author qianye.zheng
 */
/* name设置服务ID，通过eureka调用时的服务名 */
@FeignClient(name = "spring-cloud-feign-provider", path = "/", 
configuration = DefaultFeignConfig.class, fallbackFactory = SomeFallbackFactory.class)
@RequestMapping("/some")
public interface SomeFeignClient {
	
    public static final String MSG_PRFIX = "hystrix 异常消息: ";
    
    /**
     * 
     * @description 
     * 单个参数
     * @param content
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/get")
    ResultBean get(@RequestParam("content") final String content);
	
    /**
     * 
     * @description 
     * 多个参数封装到POJO
     * @param param
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/get-many")
    ResultBean getMany(@SpringQueryMap final User param);
    
    /**
     * 
     * @description 
     * @param param
     * @return
     * @author qianye.zheng
     */
    @PostMapping("/post")
    ResultBean post(@RequestBody final User param);
	
    /**
     * 
     * @description 文件上传
     * @param file
     * @return
     * @author qianye.zheng
     */
    @PostMapping(value = "/file-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultBean fileUpload(final MultipartFile file);
    
    /**
     * 
     * @description 文件下载
     * @param filename
     * @return
     * @author qianye.zheng
     */
    @GetMapping("/file-download")
    byte[] fileDownload(@RequestParam("filename") final String filename);
    
	/**
     * 
     * @type SomeFallbackFactory
     * @description 回退-携带异常消息
     * @author qianye.zheng
     */
    @Component
    static class SomeFallbackFactory implements FallbackFactory<SomeFeignClient>
    {
        /**
         * @description 
         * @param cause
         * @return
         * @author qianye.zheng
         */
        @Override
        public SomeFeignClient create(Throwable cause)
        {
            
            return new SomeFeignClient() {
                /**
                 * @description 
                 * @param content
                 * @return
                 * @author qianye.zheng
                 */
                @Override
                public ResultBean get(String content)
                {
                    ResultBean resultBean = new ResultBean();
                    resultBean.setSuccess(false);
                    resultBean.setMessage(MSG_PRFIX + cause.getMessage());
                    
                    return resultBean;
                }

                @Override
                public ResultBean getMany(User param) {
                    ResultBean resultBean = new ResultBean();
                    resultBean.setSuccess(false);
                    resultBean.setMessage(MSG_PRFIX + cause.getMessage());
                    
                    return resultBean;
                }

                @Override
                public ResultBean post(User param) {
                    ResultBean resultBean = new ResultBean();
                    resultBean.setSuccess(false);
                    resultBean.setMessage(MSG_PRFIX + cause.getMessage());
                    
                    return resultBean;
                }
                
                /**
                 * @description 
                 * @param file
                 * @return
                 * @author qianye.zheng
                 */
                @Override
                public ResultBean fileUpload(MultipartFile file) {
                    return null;
                }
                
                /**
                 * @description 
                 * @param filename
                 * @return
                 * @author qianye.zheng
                 */
                @Override
                public byte[] fileDownload(String filename) {
                    ResultBean resultBean = new ResultBean();
                    resultBean.setSuccess(false);
                    resultBean.setMessage(MSG_PRFIX + cause.getMessage());
                    
                    return JacksonUtil.writeAsBytes(resultBean);
                }
            };
        }
    }
}
