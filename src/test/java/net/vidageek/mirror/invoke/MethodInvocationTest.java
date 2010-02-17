package net.vidageek.mirror.invoke;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.AnnotationFixture;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.InterfaceFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.reflect.MethodReflectorTest;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jonasabreu
 * 
 */

public class MethodInvocationTest {

	private MethodFixture fixture;

	@Before
	public void setup() {
		fixture = new MethodFixture();
	}

	@Test
	public void testMethodInvocationWithoutArgs() {
		new Mirror().on(fixture).invoke().method("methodWithNoArgs").withoutArgs();
		assertEquals(new Integer(0), MethodFixture.getArgNum());
	}

	@Test
	public void testMethodInvocationWithOneArg() {
		new Mirror().on(fixture).invoke().method("methodWithOneArg").withArgs("any string");
		assertEquals(new Integer(1), MethodFixture.getArgNum());
	}

	@Test
	public void testMethodInvocationWithFourArgs() {
		new Mirror().on(fixture).invoke().method("methodWithFourArgs").withArgs(new String(), true,
																				MethodInvocationTest.class, 1L);
		assertEquals(new Integer(4), MethodFixture.getArgNum());
	}

	@Test
	public void testMethodInvocationWithPrimitiveArgs() {
		new Mirror().on(fixture).invoke().method("methodWithTwoPrimitives").withArgs(1, true);
		assertEquals(new Integer(2), MethodFixture.getArgNum());
	}

	@Test(expected = MirrorException.class)
	public void testThatThrowsExceptionIfMethodIsNotFound() {
		new Mirror().on(fixture).invoke().method("methodThatDoesntExists").withoutArgs();
	}

	@Test(expected = MirrorException.class)
	public void testThatThrowsExceptionIfArgumentsAreWrong() {
		new Mirror().on(fixture).invoke().method("methodWithFourArgs").withArgs(new String());
	}

	@Test
	public void testThatCanFindMethodEvenIfUsingPrimitiveTypes() {
		new Mirror().on(fixture).invoke().method("methodWithTwoPrimitives").withArgs(1, true);
		assertEquals(new Integer(2), MethodFixture.getArgNum());
	}

	@Test
	public void testThatCanCallPrivateMethods() {
		new Mirror().on(fixture).invoke().method("privateMethod").withoutArgs();
		assertEquals(new Integer(100), MethodFixture.getArgNum());
	}

	@Test
	public void testThatCanCallStaticMethods() {
		new Mirror().on(fixture).invoke().method("staticMethod").withoutArgs();
		assertEquals(new Integer(200), MethodFixture.getArgNum());
	}

	@Test
	public void testThatCanCallStaticMethodsWithArgs() {
		new Mirror().on(fixture).invoke().method("staticMethodWithOneArg").withArgs("");
		assertEquals(new Integer(300), MethodFixture.getArgNum());
	}

	@Test
	public void testThatCanCallMethodUsingArray() {
		new Mirror().on(fixture).invoke().method("methodWithArray").withArgs(new int[10]);
		assertEquals(new Integer(400), MethodFixture.getArgNum());
	}

	@Test
	public void testThatCanInvokeInterfaceMethodsOnProxys() throws Exception {
		Object proxy = Proxy.newProxyInstance(MethodReflectorTest.class.getClassLoader(),
												new Class<?>[] { InterfaceFixture.class }, new InvocationHandler() {

													public Object invoke(final Object proxy, final Method method,
															final Object[] args) throws Throwable {
														return null;
													}
												});

		new Mirror().on(proxy).invoke().method("interfaceMethod").withArgs("");
	}

	@Test
	public void testThatCanInvokeAnnotationMethods() {
		AnnotationFixture annotation = new Mirror().on(ClassFixture.class).reflect()
				.annotation(AnnotationFixture.class).atClass();

		Assert.assertEquals("foo", new Mirror().on(annotation).invoke().method("value").withoutArgs());
	}

}
