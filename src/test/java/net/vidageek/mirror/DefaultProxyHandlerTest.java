package net.vidageek.mirror;

import net.vidageek.mirror.fixtures.InterfaceFixture;
import net.vidageek.mirror.fixtures.OtherInterfaceFixture;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class DefaultProxyHandlerTest {

	@Test
	public void testThatAcceptsASingleClass() {
		new DefaultProxyHandler(null, new Class[] { Object.class });
	}

	@Test
	public void testThatAcceptsASingleInterface() {
		new DefaultProxyHandler(null, new Class[] { InterfaceFixture.class });
	}

	@Test
	public void testThatAcceptsASingleClassWithMultipleInterfaces() {
		new DefaultProxyHandler(null, new Class[] { Object.class, InterfaceFixture.class, OtherInterfaceFixture.class });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfReceiveTwoClasses() {
		new DefaultProxyHandler(null, new Class[] { Object.class, Number.class });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfReceiveAFinalClass() {
		new DefaultProxyHandler(null, new Class[] { String.class });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodInterceptorsAreNull() {
		new DefaultProxyHandler(null, new Class[] { Number.class }).interceptingWith((MethodInterceptor[]) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodInterceptorsAreEmpty() {
		new DefaultProxyHandler(null, new Class[] { Number.class }).interceptingWith(new MethodInterceptor[] {});
	}

}
