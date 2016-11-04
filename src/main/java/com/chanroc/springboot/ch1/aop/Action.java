package com.chanroc.springboot.ch1.aop;

import java.lang.annotation.*;

/**
 * 编写拦截规则的注解
 * Action
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
	/**
	 * 注解本身没有功能和xml一样，一种元数据，即配置
	 * 注解的功能来自使用注解的地方
	 * @return
	 */
	String name();
}
