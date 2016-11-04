package com.chanroc.springboot.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * 使用注解被拦截类
 * DemoAnnotationService
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
@Service
public class DemoAnnotationService {
	@Action(name = "注解式拦截的add操作")
	public void add(){}
}
