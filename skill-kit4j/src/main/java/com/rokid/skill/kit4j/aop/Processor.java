package com.rokid.skill.kit4j.aop;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 使用注解标明该类标记为一个处理器
 *
 * @author wuyukai on 2018/5/5.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Processor {

}
