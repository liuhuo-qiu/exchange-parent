/**
 * hpx.com Inc.
 * Copyright (c) 2012-YEARAll Rights Reserved.
 */
package cn.hpx.exchange.configuration;

import cn.hpx.exchange.contant.HPXContant;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrations;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**  
 * @author qlj  
 * @version $Id: FrameworkAutoConfiguration.java, v 0.1 2018-05-17 18:22 legend Exp $$  
 */
@Configuration
@EnableConfigurationProperties(HPXContant.class)
public class FrameworkAutoConfiguration {

    @Autowired
    private HPXContant hpxContant;

    /**
     * 日志对象
     */
    private static final Logger logger=Logger.getLogger(FrameworkAutoConfiguration.class);


    /**
     * 定义feign拦截器，发起请求前处理
     * @return
     */
    @Bean
    @ConditionalOnClass({HPXContant.class})
    public RequestInterceptor authRequestInterceptorRigster(){
        return (template -> {
            template.header("auth_token",hpxContant.getToken());
            template.header("appName",hpxContant.getName());
        });
    }

    /**
     * 重写requestMappingHandler
     * 排除feign引用的requestMapping
     * @return
     */
    @Bean
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrationsAdapter() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new  RequestMappingHandlerMapping(){
                    @Override
                    protected boolean isHandler(Class<?> beanType) {
                        return super.isHandler(beanType) &&
                                !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
                    }
                };
            }
        };
    }



    /**
     * 定义feign调用非熔断异常处理
     *  当响应状态为 400到500之间，则抛出异常，并且不触发熔断机制
     * @return
     */
    @Bean
    public ErrorDecoder errorDecoder(){
        return (String methodKey, Response response)->{
            // 这里只封装4开头的请求异常
            Exception exception = null;
            if (400 <= response.status() || response.status() < 500){
                exception = new HystrixBadRequestException("request exception wrapper", exception);
            }else{
                logger.error(exception.getMessage(), exception);
            }
            return exception;
        };
    }

    /**
     *
     * 注册认证过滤器
     * @return
     */
    @Bean
    @ConditionalOnClass({HPXContant.class})
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

                logger.info("收到请求: 当前项目模式:"+hpxContant.getWeb());
                //web 项目不做请求拦截
                if(hpxContant.getWeb()){
                    filterChain.doFilter(servletRequest,servletResponse);
                    return;
                }

                HttpServletRequest request=(HttpServletRequest)servletRequest;

                String appName = request.getHeader("appName");
                logger.info("获取到header中appName:"+appName);
                if(StringUtils.isBlank(appName)){
                    return;
                }
                /**
                 * 获取请求头中的token
                 */
                String authToken = request.getHeader("auth_token");
                if(StringUtils.isBlank(authToken)){
                    ((HttpServletResponse)servletResponse).setStatus(404);
                    return;
                }

                filterChain.doFilter(servletRequest,servletResponse);
            }

            @Override
            public void destroy() {

            }
        });//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("authFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }
}