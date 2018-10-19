package com.rokid.skill.kit4j.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 引入 kit4j 中的spring配置上下文等
 *
 * @author wuyukai
 */
@Configuration
@EnableWebMvc
@AutoConfigureAfter(value = WebMvcAutoConfiguration.class)
public class WebConfig implements WebMvcConfigurer {


    /**
     * 拦截器添加，拦截器的定义在 skill-kit4j 中
     *
     * @param registry 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerInterceptor())
            .addPathPatterns("/speechlet");
    }

}
