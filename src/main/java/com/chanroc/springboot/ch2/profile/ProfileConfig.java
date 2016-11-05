package com.chanroc.springboot.ch2.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Profile配置
 * ProfileConfig
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
@Configuration
public class ProfileConfig {
	@Bean
	@Profile("dev")//dev时实例化devDemoBean
	public DemoBean devDemoBean(){
		return new DemoBean("from development profile");
	}
	@Bean
	@Profile("prod")//prod时实例化prodDemoBean
	public DemoBean prodDemoBean(){
		return new DemoBean("from production profile");
	}

}
