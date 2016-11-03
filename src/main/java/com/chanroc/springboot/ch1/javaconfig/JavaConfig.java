package com.chanroc.springboot.ch1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * JavaConfig
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
@Configuration//声明当前类是一个配置类：所有的Bean都在此定义
public class JavaConfig {

	@Bean//声明方法返回值是一个Bean *Bean的名称是方法名*
	public FunctionService functionService(){
		return new FunctionService();
	}
	@Bean
	public UseFunctionService useFunctionService(){
		UseFunctionService useFunctionService = new UseFunctionService();
		useFunctionService.setFunctionService(functionService());
		return useFunctionService;
	}
	/**
	 * 另外一种注入方式：直接将FunctionService作为参数给useFunctionService（）.
	 * 在spring容器中，只要容器中存在某个Bean，就可以在另外一个Bean的声明方法的参数写入。
	 */
//	@Bean
//	public UseFunctionService useFunctionService(FunctionService functionService){
//
//		UseFunctionService useFunctionService = new UseFunctionService();
//		useFunctionService.setFunctionService(functionService);
//		return useFunctionService;
//	}
}
