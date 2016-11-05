---
title: Spring Boot实战 --（二）Spring 常用配置
date: 2016/11/4 11:26
categories: [Java,Spring]
tags: [Spring Boot,Bean生命周期,Profile,事件监听,Spring El]
keywords: Spring Boot,Bean生命周期,Profile,事件监听,Spring El
description: Spring Boot,Bean生命周期,Profile,事件监听,Spring El
---
# Spring 常用配置
## 1.Bean 的Scope

### 1.1点睛
> Scope描述的是Spring容器如何新建Bean的实例的。
> Spring的scope有一下几种：
1. singleton：一个Spring容器一个Bean实例（默认配置），容器共享一个实例
2. Prototype：每次调用新建一个Bean实例。
3. Request： Web项目中，给每一个http request 新建一个Bean实例。
4. Session： Web项目中，给每一个http session新建一个Bean实例。
5. GlobalSession：这个只在portal应用中使用，global http session 新建一个Bean实例。
6. @StepScope：Spring Batch中Scope使用批处理。

### 1.2示例

- Singleton 的Bean
```java
package com.chanroc.springboot.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Prototype的bean
 * DemoPrototypeService
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
@Service
@Scope("prototype")//声明为scope为ProtoType
public class DemoPrototypeService {

}

```
- Prototype的Bean
```java
package com.chanroc.springboot.ch2.scope;

import org.springframework.stereotype.Service;

/**
 * Singleton的Bean
 * DemoSingletService
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
//默认为Singleton，相当于@Scope("singleton")
@Service
public class DemoSingletService {

}

```
- 配置类
```java
package com.chanroc.springboot.ch2.scope;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * ScopeConfig
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
@Configuration
@ComponentScan("com.chanroc.springboot.ch2.scope")
public class ScopeConfig {
}

```

- 运行类
```java
package com.chanroc.springboot.ch2.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行主方法
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
		DemoSingletService s1 = context.getBean(DemoSingletService.class);
		DemoSingletService s2 = context.getBean(DemoSingletService.class);
		DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
		DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);
		System.out.println("s1与s2是否相等："+s1.equals(s2));
		System.out.println("p1与p2是否相等："+p1.equals(p2));

		context.close();
	}
}

```
- 打印：
```
s1与s2是否相等：true

p1与p2是否相等：false

```

- [代码实例（连接）](https://github.com/ChenGuop/springboot/tree/master/src/main/java/com/chanroc/springboot/ch2/scope)

##2. Spring EL 和资源调用

### 2.1点睛
> - Spring EL ：Spring 表达式语言，支持在xml和注解中使用表达式。类似于JSP的EL表达式
> - 调用资源：普通文件，网址，配置文件，系统环境变量等
> - @Value： 的参数中使用表达式

- 演示实现的几种情况：
	- 注入普通字符；
	- 注入操作系统属性；
	- 注入表达式运算结果；
	- 注入其他的Bean的属性
	- 注入文件内容
	- 注入网址内容
	- 注入属性文件

### 示例

- pom.xml文件添加commons-io文件操作
```xml
	<dependency>
		<groupId>commons-io</groupId>
	    <artifactId>commons-io</artifactId>
	    <version>2.4</version>
	</dependency>
```

-  添加文件：
>  resources包下新建test.propertires
```
book.author=changuop
book.name=spring boot
```
> resources包下新建test.txt文件内容随意

- 需要被注入的Bean
```java
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

```

- 配置类
```java
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
	private Environment environment;

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

```

- 运行类
```java
package com.chanroc.springboot.ch2.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主方法运行
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(ElConfig.class);
		ElConfig elConfig = context.getBean(ElConfig.class);
		elConfig.outputResource();
	}
}

```

- 运行结果
```
----------Sanji.com--------normal值=I Love You!,当前类=ElConfig.outputResource()
----------Sanji.com--------osName值=Windows 7,当前类=ElConfig.outputResource()
----------Sanji.com--------randomNumber值=94.23795546931908,当前类=ElConfig.outputResource()
----------Sanji.com--------fromAnthor值=其他类的属性,当前类=ElConfig.outputResource()
这是一个测试文件txt
<!DOCTYPE html>
<!--STATUS OK--><html> <head><meta http-equiv=content-type content=text/html;charset=utf-8><meta http-equiv=X-UA-Compatible content=IE=Edge><meta content=always name=referrer><link rel=stylesheet type=text/css href=http://s1.bdstatic.com/r/www/cache/bdorz/baidu.min.css><title>百度一下，你就知道</title></head> <body link=#0000cc> <div id=wrapper> <div id=head> <div class=head_wrapper> <div class=s_form> <div class=s_form_wrapper> <div id=lg> <img hidefocus=true src=//www.baidu.com/img/bd_logo1.png width=270 height=129> </div> <form id=form name=f action=//www.baidu.com/s class=fm> <input type=hidden name=bdorz_come value=1> <input type=hidden name=ie value=utf-8> <input type=hidden name=f value=8> <input type=hidden name=rsv_bp value=1> <input type=hidden name=rsv_idx value=1> <input type=hidden name=tn value=baidu><span class="bg s_ipt_wr"><input id=kw name=wd class=s_ipt value maxlength=255 autocomplete=off autofocus></span><span class="bg s_btn_wr"><input type=submit id=su value=百度一下 class="bg s_btn"></span> </form> </div> </div> <div id=u1> <a href=http://news.baidu.com name=tj_trnews class=mnav>新闻</a> <a href=http://www.hao123.com name=tj_trhao123 class=mnav>hao123</a> <a href=http://map.baidu.com name=tj_trmap class=mnav>地图</a> <a href=http://v.baidu.com name=tj_trvideo class=mnav>视频</a> <a href=http://tieba.baidu.com name=tj_trtieba class=mnav>贴吧</a> <noscript> <a href=http://www.baidu.com/bdorz/login.gif?login&amp;tpl=mn&amp;u=http%3A%2F%2Fwww.baidu.com%2f%3fbdorz_come%3d1 name=tj_login class=lb>登录</a> </noscript> <script>document.write('<a href="http://www.baidu.com/bdorz/login.gif?login&tpl=mn&u='+ encodeURIComponent(window.location.href+ (window.location.search === "" ? "?" : "&")+ "bdorz_come=1")+ '" name="tj_login" class="lb">登录</a>');</script> <a href=//www.baidu.com/more/ name=tj_briicon class=bri style="display: block;">更多产品</a> </div> </div> </div> <div id=ftCon> <div id=ftConw> <p id=lh> <a href=http://home.baidu.com>关于百度</a> <a href=http://ir.baidu.com>About Baidu</a> </p> <p id=cp>&copy;2016&nbsp;Baidu&nbsp;<a href=http://www.baidu.com/duty/>使用百度前必读</a>&nbsp; <a href=http://jianyi.baidu.com/ class=cp-feedback>意见反馈</a>&nbsp;京ICP证030173号&nbsp; <img src=//www.baidu.com/img/gs.gif> </p> </div> </div> </div> </body> </html>

----------Sanji.com--------bookName值=spring boot,当前类=ElConfig.outputResource()
changuop

```

- [代码示例（连接）](https://github.com/ChenGuop/springboot/tree/master/src/main/java/com/chanroc/springboot/ch2/el)

## 3.Bean的初始化和销毁

### 3.1 点睛
> 实际开发中会遇到在Bean使用之前或者之后做些必要的操作。Spring对Bean的生命周期的操作提供了支持。提供两种方式：
> 1. Java配置方式：使用@Bean 的initMethod 和destroyMethod （相当于xml配置的init-method和destroy-method）。
> 2. 注解方式：JSR-250 的@PostConstuct@PreDestroy。

### 3.2 演示

1. 增加JSR250支持pom.xml
```xml
<!-- https://mvnrepository.com/artifact/javax.annotation/jsr250-api -->
        <dependency>
            <groupId>javax.annotation</groupId>
            <artifactId>jsr250-api</artifactId>
            <version>1.0</version>
        </dependency>
```
2. 使用@Bean形式的Bean
```java
package com.chanroc.springboot.ch2.prepost;

/**
 * @author ChenGuop
 * @Bean形式的Bean BeanWayService
 * @date 2016/11/5
 */
public class BeanWayService {
	public void init(){
		System.out.println("@Bean-init-method");
	}
	public BeanWayService(){
		super();
		System.out.println("初始化构造函数-BeanWayService");
	}
	public void destroy(){
		System.out.println("@Bean-destroy-method");
	}
}

```
3. JSR250形式的Bena
```java
package com.chanroc.springboot.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * JSR250形式的Bean
 * JSR250WayService
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class JSR250WayService {
	@PostConstruct//在构造函数后执行
	public void init(){
		System.out.println("jsr250-init-method");
	}
	public JSR250WayService(){
		super();
		System.out.println("初始化构造函数-JSR250WayService");
	}
	@PreDestroy//在Bean销毁之前执行
	public void destroy(){
		System.out.println("jsr250-destroy-method");
	}

}

```
4. 配置类
```java
package com.chanroc.springboot.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * PrePostConfig
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
@Configuration
@ComponentScan("com.chanroc.springboot.ch2.prepost")
public class PrePostConfig {
	//指定Bean在构造之后，在销毁前执行
	@Bean(initMethod = "init", destroyMethod = "destroy")
	BeanWayService beanWayService(){
		return new BeanWayService();
	}
	@Bean
	JSR250WayService jsr250WayService(){
		return new JSR250WayService();
	}
}

```

4. 主方法运行：
```java
package com.chanroc.springboot.ch2.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主方法
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
		BeanWayService beanWayService = context.getBean(BeanWayService.class);
		JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);
		context.close();
		System.out.println("销毁之后");
	}
}

```


- 运行结果：
```
初始化构造函数-BeanWayService
@Bean-init-method
初始化构造函数-JSR250WayService
jsr250-init-method

jsr250-destroy-method
@Bean-destroy-method
销毁之后
```

## 4.Profile
### 4.1点睛
> `Profile`为在不同环境下使用不同的配置提供了支持（开发环境的配置和生产环境的配置肯定是不同的）
1. 通过设定@Environment的ActiveProfile来设定当前contex需要的配置环境。在开发中使用@Profile注解或者方法，在不同情况下选择实例化不同的Bean。
2. 通过设定jvm的spring.profile.active参数来设置配置环境。
3. Web项目设置在Servlet的context parameter中。

### 4.2 演示
1. 示例bean
```java
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

```

2. Profile配置
```java
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

```
3. 运行类
```java
package com.chanroc.springboot.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行类
 * Main
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("prod");
		context.getEnvironment().setActiveProfiles("dev");
		context.register(ProfileConfig.class);
		context.refresh();
		DemoBean demoBean = context.getBean(DemoBean.class);
		System.out.println(demoBean.getContent());
	}
}

```
4. 运行结果：
```
from development profile

from development profile
```
5. [代码示例（连接）](https://github.com/ChenGuop/springboot/tree/master/src/main/java/com/chanroc/springboot/ch2/profile)

## 5.事件（ApplicationEvent）

### 5.1 点睛
> spring的事件（ApplicationEvent）为Bean与Bean之间的消息通信提供了支持。当一个Bean处理玩一个任务后，希望另一个Bean知道并能做相应处理，需要另一个Bean监听当前Bean的发生事件。
- Spring的事件需要遵循如下流程：
	- 自定义事件，集成ApplicationEvent。
	- 自定义事件监听器 ，实现ApplicationListener。
	- 使用容器发布事件。
### 5.2 示例
1. 自定义事件。
```java
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

```
2. 事件监听器
```java
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

```
3. 事件发布类
```java
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

```

4. 配置类
```java
package com.chanroc.springboot.ch2.event;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * EventConfig
 *
 * @author ChenGuop
 * @date 2016/11/5
 */
@Configuration
@ComponentScan("com.chanroc.springboot.ch2.event")
public class EventConfig {
}

```
5. 运行类
```java
package com.chanroc.springboot.ch2.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 * 主方法
 * @author ChenGuop
 * @date 2016/11/5
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
		DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
		demoPublisher.publisher("hello application event");
		context.close();
	}
}

```

6. 运行结果
```
我（bean-demoListener）接收到了bean-demoPublisher发布的消息：hello application event
```
### 5.3 [代码演示（连接）](https://github.com/ChenGuop/springboot/tree/master/src/main/java/com/chanroc/springboot/ch2/event)