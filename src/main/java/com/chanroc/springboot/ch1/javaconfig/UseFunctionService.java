package com.chanroc.springboot.ch1.javaconfig;

/**
 * 使用功能的service
 * UseFunctionService
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
//此处没有使用Service
public class UseFunctionService {

	//此处没有使用Autowired 注解注入Bean
	FunctionService functionService;

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public String SayHello(String word) {
		return functionService.sayHello(word);
	}
}
