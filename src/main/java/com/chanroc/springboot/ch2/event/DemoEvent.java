package com.chanroc.springboot.ch2.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * ApplicationEvent
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class DemoEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	private String msg;
	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public DemoEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
