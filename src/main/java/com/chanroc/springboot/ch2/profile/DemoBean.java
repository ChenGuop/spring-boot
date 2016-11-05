package com.chanroc.springboot.ch2.profile;

/**
 * 实例Bean
 * DemoBean
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class DemoBean {
	private String content;

	public DemoBean(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
