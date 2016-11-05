package com.chanroc.springboot.ch2.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * 配置类
 * ElConfig
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
@Configuration
@ComponentScan("com.chanroc.springboot.ch2.el")//注入Bean
@PropertySource("classpath:test.properties")
public class ElConfig {

	@Value("I Love You!")//普通字符串
	private String normal;

	//操作系统属性
	@Value("#{systemProperties['os.name']}")
	private String osName;

	//注入其他Bean属性
	@Value("#{ demoService.another}")
	private String fromAnthor;
	//注入表达式结果
	@Value("#{ T(java.lang.Math).random()*100.0 }")
	private double randomNumber;
	//文件资源
	@Value("classpath:test.txt")
	private Resource testFile;
	//网址资源
	@Value("http://www.baidu.com")
	private Resource testUrl;
	//配置文件
	@Value("${book.name}")
	private String bookName;
	@Autowired
	private Environment environment; // 读取配置文件

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	public void outputResource(){
		try {
			System.out.println("----------Sanji.com--------normal值=" + normal + ",当前类=ElConfig.outputResource()");
			System.out.println("----------Sanji.com--------osName值=" + osName + ",当前类=ElConfig.outputResource()");
			System.out.println("----------Sanji.com--------randomNumber值=" + randomNumber + ",当前类=ElConfig.outputResource()");
			System.out.println("----------Sanji.com--------fromAnthor值=" + fromAnthor + ",当前类=ElConfig.outputResource()");
			System.out.println(IOUtils.toString(testFile.getInputStream()));
			System.out.println(IOUtils.toString(testUrl.getInputStream()));
			System.out.println("----------Sanji.com--------bookName值=" + bookName + ",当前类=ElConfig.outputResource()");
			System.out.println(environment.getProperty("book.author"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
