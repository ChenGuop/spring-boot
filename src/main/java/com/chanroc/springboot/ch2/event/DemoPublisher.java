package com.chanroc.springboot.ch2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 事件发布类
 * DemoPublisher
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
@Component
public class DemoPublisher {
	@Autowired
	ApplicationContext applicationContext;//用来发布事件
	public void publisher(String msg){
		applicationContext.publishEvent(new DemoEvent(this,msg));//发布事件
	}
}
