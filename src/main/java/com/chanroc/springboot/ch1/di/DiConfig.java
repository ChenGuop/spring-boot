package com.chanroc.springboot.ch1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * DiConfig
 *
 * @author ChenGuop
 * @date 2016/11/3
 */
//声明当前类是一个配置类
@Configuration
//自动扫描包名下所有的的@Service，@Componment,@Repository,和@Controller的类，并注册为Bean
@ComponentScan("com.chanroc.springboot.ch1.di")
public class DiConfig {

}
