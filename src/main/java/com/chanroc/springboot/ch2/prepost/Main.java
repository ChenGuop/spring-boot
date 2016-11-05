package com.chanroc.springboot.ch2.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主方法
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
		BeanWayService beanWayService = context.getBean(BeanWayService.class);
		JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);
		context.close();
		System.out.println("销毁之后");
	}
}
