package com.zizhuling.test.config;

import com.zizhuling.test.component.RequestLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * <p>
 *     把请求限制的Interceptor注册到springboot中
 * </P>
 *
 * @author hebiao Create on 2022/11/9 9:51
 * @version 1.0
 */
@Configuration
public class RequestLimitConfig extends WebMvcConfigurerAdapter {
    @Resource
    private RequestLimitInterceptor requestLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( requestLimitInterceptor );
    }
}
