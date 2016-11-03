package com.chanroc.springboot.ch1.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用功能的service
 * UseFunctionService
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
@Service
	public class UseFunctionService {

	@Autowired
	FunctionService functionService;

	public String SayHello(String word){
		return functionService.sayHello(word);
	}
}
