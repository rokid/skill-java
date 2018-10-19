package com.rokid.skill.kit4j.core;


import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wuyukai on 2018/5/6.
 */
@Data
@AllArgsConstructor
public class Handler {

	private Method method;
	private Object bean;

}
