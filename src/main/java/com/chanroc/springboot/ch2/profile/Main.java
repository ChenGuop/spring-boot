package com.chanroc.springboot.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行类
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class Main {
	public static void main(String[] args) {
		//无参数实例化
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//		context.getEnvironment().setActiveProfiles("prod");
		context.getEnvironment().setActiveProfiles("dev");
		context.register(ProfileConfig.class);
		context.refresh();
		DemoBean demoBean = context.getBean(DemoBean.class);
		System.out.println(demoBean.getContent());
		context.close();
	}
}
