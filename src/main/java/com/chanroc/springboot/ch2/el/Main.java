package com.chanroc.springboot.ch2.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主方法运行
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(ElConfig.class);
		ElConfig elConfig = context.getBean(ElConfig.class);
		elConfig.outputResource();
	}
}
