package com.chanroc.springboot.ch1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行类
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
public class Main {
	public static void main(String[] args) {
		//使用AnnotationConfigApplicationContext 作为spring容器，接受配置类作为参数
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);

		//获得声明配置的UseFunctionService 的Bean
		UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
		System.out.println(useFunctionService.SayHello("Word"));
		context.close();
	}
}
