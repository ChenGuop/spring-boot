package com.chanroc.springboot.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * PrePostConfig
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
@Configuration
@ComponentScan("com.chanroc.springboot.ch2.prepost")
public class PrePostConfig {
	//指定Bean在构造之后，在销毁前执行
	@Bean(initMethod = "init", destroyMethod = "destroy")
	BeanWayService beanWayService(){
		return new BeanWayService();
	}
	@Bean
	JSR250WayService jsr250WayService(){
		return new JSR250WayService();
	}
}
