package com.adam.k8.circulardependencies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by xsls on 2019/5/29.
 */
@Component
@Scope("prototype")
public class InstanceA implements IApi {

	@Autowired
	private InstanceB instanceB;

	public InstanceB getInstanceB() {
		return instanceB;
	}

	public void setInstanceB(InstanceB instanceB) {
		this.instanceB = instanceB;
	}

	public InstanceA(InstanceB instanceB) {
		this.instanceB = instanceB;
	}


	public InstanceA() {
		System.out.println("InstanceA实例化");
	}

	@Override
	public void say() {
		System.out.println("I'm A");
	}
}
