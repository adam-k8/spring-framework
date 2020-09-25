package com.adam.k8.circulardependencies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * Created by xsls on 2019/10/14.
 */
public class JdkDynamicProxy implements InvocationHandler {

	private Object target;

	public JdkDynamicProxy(Object target) {
		this.target = target;
	}


	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("测试");
		return method.invoke(target, args);
	}
}
