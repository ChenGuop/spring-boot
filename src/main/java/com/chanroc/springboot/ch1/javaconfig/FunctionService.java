package com.chanroc.springboot.ch1.javaconfig;

import org.springframework.stereotype.Service;

/**
 * 功能bean
 * FunctionService
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
//此处没有使用Service
public class FunctionService {
	public String sayHello(String word) {
		return "Hello " + word + "!";
	}
}
