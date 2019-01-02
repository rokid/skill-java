package com.rokid.skill.kit.annotation;

import java.lang.annotation.Documented;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;

/**
 * 使用该注解表名方法对应一个action.
 *
 * @author wuyukai on 2018/5/5.
 */
@Target({ElementType.METHOD, ElementType.TYPE})

@Retention(RetentionPolicy.RUNTIME)

@Documented
public @interface Action {

  /**
   * 意图名称注解
   *
   * @return 意图名称
   */
  String[] intent() default {};

  /**
   * 事件名称注解
   *
   * @return 事件名称
   */
  String[] event() default {};
}
