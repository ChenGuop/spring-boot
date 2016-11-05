package com.chanroc.springboot.ch2.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行主方法
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
		DemoSingletService s1 = context.getBean(DemoSingletService.class);
		DemoSingletService s2 = context.getBean(DemoSingletService.class);
		DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
		DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);
		System.out.println("s1与s2是否相等："+s1.equals(s2));
		System.out.println("p1与p2是否相等："+p1.equals(p2));

		context.close();
	}
}
