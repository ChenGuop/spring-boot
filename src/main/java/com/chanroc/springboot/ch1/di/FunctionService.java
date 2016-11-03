package com.chanroc.springboot.ch1.di;

import org.springframework.stereotype.Service;

/**
 * 功能bean
 * FunctionService
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
@Service
public class FunctionService {
	public String sayHello(String word) {
		return "Hello " + word + "!";
	}
}
