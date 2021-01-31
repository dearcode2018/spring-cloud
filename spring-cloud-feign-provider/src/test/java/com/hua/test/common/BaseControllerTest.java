/**
 * 描述:
 * BaseControllerTest.java
 *
 * @author qye.zheng
 * version 1.0
 */
package com.hua.test.common;

import javax.annotation.Resource;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hua.ApplicationStarter;
import com.hua.test.BaseTest;
import com.hua.util.StringUtil;


/**
 * 描述:
 *
 * @author qye.zheng
 * BaseControllerTest
 */
/*
 *
 * @SpringJUnit4ClassRunner 运行器负责拉起 spring 环境
 * @ContextConfiguration 指定 spring配置文件，若不指定，则使用默认配置.
 */
//@ContextConfiguration(locations = {"classpath:conf/xml/applicationContext.xml"})
/*
 * dispatcher-servlet.xml 放在目标项目的src/main/webapp/WEB-INF目录下
 * 无法直接放到当前项目的classpath目录下，因此需要在dispatcher-servlet.xml
 * 发生变化时，将此文件拷贝到conf/xml 目录下，所有的spring配置文件包括mvc部分，
 * 都在此处标示出来，才能正常启动mvc环境
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class BaseControllerTest extends BaseTest {

    /**
     * 引当前项目用其他项目之后，然后可以使用
     * SpringJunitTest模板测试的其他项目
     * <p>
     * 可以使用所引用目标项目的所有资源
     * 若引用的项目的配置与本地的冲突或无法生效，需要
     * 将目标项目的配置复制到当前项目同一路径下
     */

    @Resource
    protected WebApplicationContext webApplicationContext;

    /**
     * @param url 服务相对url
     * @return
     * @description POST 请求
     * @author qianye.zheng
     */
    protected MockHttpServletRequestBuilder post(final String url) {
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);
        requestBuilder.header("Content-Type", "application/json;charset=UTF-8");
        requestBuilder.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        // 构建公共参数
        buildCommonParam(requestBuilder);

        return requestBuilder;
    }

    /**
     * @param url 服务相对url
     * @return
     * @description GET 请求
     * @author qianye.zheng
     */
    protected MockHttpServletRequestBuilder get(final String url) {
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
        requestBuilder.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        // 构建公共参数
        buildCommonParam(requestBuilder);

        return requestBuilder;
    }

    /**
     * @param url 服务相对url
     * @return
     * @description POST 请求
     * @author qianye.zheng
     */
    protected MockHttpServletRequestBuilder options(final String url) {
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.options(url);
        // 构建公共参数
        buildCommonParam(requestBuilder);

        return requestBuilder;
    }

    /**
     * @param url 服务相对url
     * @return
     * @description 文件上传请求
     * @author qianye.zheng
     */
    protected MockMultipartHttpServletRequestBuilder fileUpload(final String url) {
        final MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.multipart(url);
        // 构建公共参数
        buildCommonParam(requestBuilder);

        return requestBuilder;
    }

    /**
     * @param requestBuilder
     * @return 返回模拟http请求对象
     * @description 执行(模拟)请求
     * @author qianye.zheng
     */
    protected MockHttpServletResponse perform(final RequestBuilder requestBuilder) {
        // 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
        final MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // 响应对象
        MockHttpServletResponse response = null;
        try {
            // mvc 结果
            MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
            // 异常对象
            final Exception exception = mvcResult.getResolvedException();
            if (null != exception && StringUtil.isNotEmpty(exception.getMessage())) {
                log.warn("异常信息: " + exception.getMessage());
            }
            response = mvcResult.getResponse();
        } catch (final Exception e) {
            log.error("perform =====> ", e);
        }

        return response;
    }

    /**
     * @param requestBuilder
     * @description 构建公共参数
     * @author qianye.zheng
     */
    private void buildCommonParam(final MockHttpServletRequestBuilder requestBuilder) {
       
    }

}
