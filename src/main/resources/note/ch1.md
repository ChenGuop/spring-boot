---
title: Spring项目的快速搭建
date: 2016/11/3 14:57
categories: [Java,Spring]
tags: [spring,maven]
keywords: spring,pom
description: 使用maven的pom快速搭建项目
---

## Maven 的简介
> Apache Maven 是一个软件项目管理工具。基于项目对象模型（Project Object Model，POM）的概念，Maven可以用来管理项目的依赖，编译，文档等信息。

## Maven的安装
略
## Maven 的pom.xml
- dependencies

	> ```<dependencies></dependencies>```

- dependency
	> ``` <dependency></dependency>```
	> 内不通过groupId、artifactId、version确定唯一的依赖
	
- 查询地址：http://mvnrepository.com/
> 可以查询对应jar包的 maven pom
- 具体结构
```xml
	<dependencies>
        <dependency>
            <groupId>org.springframework</groupId> <!-- 组织唯一标识-->
            <artifactId>spring-context</artifactId><!-- 项目唯一标识-->
            <version>4.2.5.RELEASE</version><!--项目的版本-->
        </dependency>
	</dependencies>
```
- 定义变量
```xml
	<properties>
        <java.version>1.8</java.version>
	</properties>
```

- 编译插件
```xml
	 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

## Spring 项目的搭建

> 基于IntelliJ IDEA搭建
> - 新建Maven项目。单击File-new-Project-Maven
>  ![enter image description here](http://source.chanroc.top/image/ch1.di/new-project.png)
> - 选择maven
>  ![enter image description here](http://source.chanroc.top/image/ch1.di/new-peroject-maven.png)
> - 输入坐标值
> - 选择存储路径
> ![enter image description here](http://source.chanroc.top/image/ch1.di/new-project-3.png)
> ![enter image description here](http://source.chanroc.top/image/ch1.di/new-project-4.png)
> - 修改pom.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0"
		         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
		
	<groupId>com.chanroc</groupId>
	<artifactId>springboot</artifactId>
	<version>1.0-SNAPSHOT</version>
		
		    <properties>
		        <java.version>1.8</java.version>
		    </properties>
		    <dependencies>
		        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
		        <dependency>
		            <groupId>org.springframework</groupId>
		            <artifactId>spring-context</artifactId>
		            <version>4.2.5.RELEASE</version>
		        </dependency>
		    </dependencies>
		
		    <build>
		        <plugins>
		            <plugin>
		                <groupId>org.springframework.boot</groupId>
		                <artifactId>spring-boot-maven-plugin</artifactId>
		            </plugin>
		        </plugins>
		    </build>
		</project>
```
## Spring基本配置

Spring框架四大原则：
1. 使用POJO经轻量级和最小入侵式开发。
2. 通过依赖注入和基于接口编程实现松耦合。
3. AOP和默认习惯进行声明式编程
4. 使用AOP合模班减少模式化代码

### 依赖注入
> - 控制反转（IOC）和依赖注入在spring环境下是等同的概念，前者是通过后者实现的。
> - 依赖注入：容器负责创建对象和维护对象间的依赖关系，而非对象本身。
> - `目的`：解耦
> - Spring IOC容器 负责创建Bean，通过容器将功能Bean注入到需要的Bean中。方式：xml，注解，Java配置、groovy配置实现Bean的创建和注入
> - 元数据：描述数据的数据。xml配置，注解，Java配置称为配置元数据。

- 声明Bean的注解：
	- @Component组件，无明确的角色
	- @Service 在业务逻辑层使用
	- @Repository 在数据访问层使用
	- @Controller 在展现层使用（MVC）
- 注入Bean的注解：
	- @Autowired：Spring提供注解
	- @Inject：JSR-330提供注解
	- @Resource ：JSR-250提供注解

#### [代码演示](https://github.com/ChenGuop/springboot)
- [具体代码位置](https://github.com/ChenGuop/springboot/tree/master/src/main/java/com/chanroc/springboot/ch1/di)

### Java 配置
#### 描述
> java配置是Spring4.x推荐的配置方式，可以完全代替xml配置；也是Spring Boot推荐的配置方式。
> Java配置通过@Configuration和@Bean来实现
> - @Configuration 声明当前类是一个配置类
> - @Bean注解子啊方法上，声明当前方法的返回值为一个Bean。
> 配置的原则：全局配置使用Java配置（数据库，MVC配置），业务Bean使用注解配置(@Service，@Component，@Repository，@Controller)

#### [代码演示](https://github.com/ChenGuop/springboot/tree/master/src/main/java/com/chanroc/springboot/ch1/javaconfig)

### AOP

> - AOP: 面向切面编程，相对于OOP面向对象编程
> - 目的：解耦。在oop中智能通过继承和实现接口，阻碍更多的行为添加到一组类上，AOP弥补了OOP的不足。

- Spring支持AspectJ的注解式切面编程：
	1. 使用@Aspect声明是一个切面。
	2. 使用@After、@Before、@Around 定义建言（advice），可直接将拦截规则（切点）作为参数。
	3.  @After、@Before、@Around 参数的拦截规则为切点（PointCut），为了使切点复用使用@PointCut 专门定义。
	4.  其中符合条件的每一个被拦截处为连接点（JoinPoint）。