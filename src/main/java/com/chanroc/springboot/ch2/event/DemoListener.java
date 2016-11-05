package com.chanroc.springboot.ch2.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * DemoListener
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {
	public void onApplicationEvent(DemoEvent event) {//对消息进行接受处理
		String msg = event.getMsg();
		System.out.println("我（bean-demoListener）接收到了bean-demoPublisher发布的消息："+msg);
	}
}
