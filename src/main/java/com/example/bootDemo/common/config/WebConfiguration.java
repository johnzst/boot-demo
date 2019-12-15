package com.example.bootDemo.common.config;

import com.example.bootDemo.common.interceptor.AuthenticationInterceptor;
import com.example.bootDemo.common.interceptor.ResponseResultInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 前置配置
 *
 * @author John
 */
@Slf4j
@Configuration
@EnableCaching
public class WebConfiguration implements WebMvcConfigurer, ApplicationRunner {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUri = "/**";
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns(apiUri);
        //统一响应拦截
        registry.addInterceptor(responseResultInterceptor()).addPathPatterns(apiUri);
        log.error("WebConfiguration,设置拦截器完成");
    }

    /**
     * 响应拦截
     */
    @Bean
    public ResponseResultInterceptor responseResultInterceptor() {
        return new ResponseResultInterceptor();
    }

    /**
     * 认证异常拦截
     */
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("===>>  *^_^* 项目成功启动 *^_^*");
    }
}
