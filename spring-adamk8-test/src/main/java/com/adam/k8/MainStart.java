package com.adam.k8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxinxin
 * @title: com.adam.k8.MainStart
 * @projectName spring
 * @description: TODO
 * @date 2020/9/13 17:00
 */
@Configuration
@ComponentScan("com.adam.k8")
public class MainStart {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(MainStart.class);
		UserServiceImpl bean = context.getBean(UserServiceImpl.class);
		bean.helloWorld();
	}
}
