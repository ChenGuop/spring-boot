package com.chanroc.springboot.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * JSR250形式的Bean
 * JSR250WayService
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class JSR250WayService {
	@PostConstruct//在构造函数后执行
	public void init(){
		System.out.println("jsr250-init-method");
	}
	public JSR250WayService(){
		super();
		System.out.println("初始化构造函数-JSR250WayService");
	}
	@PreDestroy//在Bean销毁之前执行
	public void destroy(){
		System.out.println("jsr250-destroy-method");
	}

}
