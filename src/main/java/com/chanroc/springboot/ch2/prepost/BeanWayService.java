package com.chanroc.springboot.ch2.prepost;

/**
 * @author ChenGuop
 * @Bean形式的Bean BeanWayService
 * @date 2016/11/5
 */
public class BeanWayService {
	public void init(){
		System.out.println("@Bean-init-method");
	}
	public BeanWayService(){
		super();
		System.out.println("初始化构造函数-BeanWayService");
	}
	public void destroy(){
		System.out.println("@Bean-destroy-method");
	}
}
