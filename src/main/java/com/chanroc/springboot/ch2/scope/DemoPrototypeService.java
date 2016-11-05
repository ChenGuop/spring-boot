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
