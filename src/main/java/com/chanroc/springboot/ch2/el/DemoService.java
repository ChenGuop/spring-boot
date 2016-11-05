package com.chanroc.springboot.ch2.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 注入Bean
 * DemoService
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
@Service
public class DemoService {
	@Value("其他类的属性")//注入普通的字符串
	private String another;

	public String getAnother() {
		return another;
	}

	public void setAnother(String another) {
		this.another = another;
	}
}
