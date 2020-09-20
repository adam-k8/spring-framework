/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.aspectj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.aop.aspectj.AdviceBindingTestAspect.AdviceBindingCollaborator;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.testfixture.beans.ITestBean;
import org.springframework.beans.testfixture.beans.TestBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for various parameter binding scenarios with before advice.
 *
 * @author Adrian Colyer
 * @author Rod Johnson
 * @author Chris Beams
 */
public class AfterAdviceBindingTests {

	private AdviceBindingCollaborator mockCollaborator;

	private ITestBean testBeanProxy;

	private TestBean testBeanTarget;


	@BeforeEach
	public void setup() throws Exception {
		ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext(getClass().getSimpleName() + ".xml", getClass());
		AdviceBindingTestAspect afterAdviceAspect = (AdviceBindingTestAspect) ctx.getBean("testAspect");

		testBeanProxy = (ITestBean) ctx.getBean("testBean");
		assertThat(AopUtils.isAopProxy(testBeanProxy)).isTrue();

		// we need the real target too, not just the proxy...
		testBeanTarget = (TestBean) ((Advised) testBeanProxy).getTargetSource().getTarget();

		mockCollaborator = mock(AdviceBindingCollaborator.class);
		afterAdviceAspect.setCollaborator(mockCollaborator);
	}


	@Test
	public void testOneIntArg() {
		testBeanProxy.setAge(5);
		verify(mockCollaborator).oneIntArg(5);
	}

	@Test
	public void testOneObjectArgBindingProxyWithThis() {
		testBeanProxy.getAge();
		verify(mockCollaborator).oneObjectArg(this.testBeanProxy);
	}

	@Test
	public void testOneObjectArgBindingTarget() {
		testBeanProxy.getDoctor();
		verify(mockCollaborator).oneObjectArg(this.testBeanTarget);
	}

	@Test
	public void testOneIntAndOneObjectArgs() {
		testBeanProxy.setAge(5);
		verify(mockCollaborator).oneIntAndOneObject(5, this.testBeanProxy);
	}

	@Test
	public void testNeedsJoinPoint() {
		testBeanProxy.getAge();
		verify(mockCollaborator).needsJoinPoint("getAge");
	}

	@Test
	public void testNeedsJoinPointStaticPart() {
		testBeanProxy.getAge();
		verify(mockCollaborator).needsJoinPointStaticPart("getAge");
	}

}
