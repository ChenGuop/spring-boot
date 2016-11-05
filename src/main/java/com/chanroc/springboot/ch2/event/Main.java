package com.chanroc.springboot.ch2.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 * 主方法
 * @author ChenGuop
 * @date 2016/11/5
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
		DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
		demoPublisher.publisher("hello application event");
		context.close();
	}
}
