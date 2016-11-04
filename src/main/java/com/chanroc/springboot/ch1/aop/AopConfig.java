package com.chanroc.springboot.ch1.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 * AopConfig
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
@Configuration
@ComponentScan("com.chanroc.springboot.ch1.aop")//注入到spring容器中
//注解开启Spring对AspectJ的代理支持
@EnableAspectJAutoProxy
public class AopConfig {
}
