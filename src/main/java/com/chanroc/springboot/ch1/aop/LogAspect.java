package com.chanroc.springboot.ch1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面
 * LogAspect
 *
 * @author ChenGuop
 * @date 2016/11/4
 */
@Aspect//注解声明切面
@Component//注入Bean
public class LogAspect {
	//声明切点（使用注解Action的地方）
	@Pointcut("@annotation(com.chanroc.springboot.ch1.aop.Action)")
	public void annotationPointCut() {
	}
	//注解声明建言，直接使用@PointCut定义的切点
	@After("annotationPointCut()")
	public void after(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Action action = method.getAnnotation(Action.class);
		// 通过反射可获得注解的属性，
		System.out.println("注解式拦截" + action.name());
	}

	//注解声明建言，直接使用拦截规则作为参数
	@Before("execution(* com.chanroc.springboot.ch1.aop.DemoMethodService.*(..))")
	public void before(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("方法规则式拦截" + method.getName());
	}
}
