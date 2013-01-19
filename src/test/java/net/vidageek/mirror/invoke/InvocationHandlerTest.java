package net.vidageek.mirror.invoke;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.BeanFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jonasabreu
 * 
 */
@SuppressWarnings("unchecked")
public class InvocationHandlerTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfConstructedWithNullUsingObjectConstructor() {
		new DefaultInvocationHandler(provider, (Object) null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfConstructedwithNullUsingClassConstructor() {
		new DefaultInvocationHandler(provider, (Class<?>) null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfCallNullMethodName() {
		new DefaultInvocationHandler(provider, new Object()).method((String) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfCallNullMethod() {
		new DefaultInvocationHandler(provider, new Object()).method((Method) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfCallNullConstructor() {
		new DefaultInvocationHandler(provider, new Object()).constructor(null);
	}

	@Test(expected = IllegalStateException.class)
	public void testThatThrowsExceptionIfTryToInvokeConstructorOnObject() {
		new DefaultInvocationHandler(provider, new Object()).constructor();
	}

	@Test
	public void testThatInvokesGetter() {
		BeanFixture target = new BeanFixture();
		target.setField("foo");
		String string = (String) new DefaultInvocationHandler<Object>(provider, target).getterFor("field");
		assertEquals("foo", string);
	}

	@Test
	public void testThatInvokesGetterStartingWithIs() {
		BeanFixture target = new BeanFixture();
		target.setBooleanField(true);
		Boolean b = (Boolean) new DefaultInvocationHandler<Object>(provider, target).getterFor("booleanField");
		assertTrue(b);
	}

	@Test
	public void testThatInvokesGetterUsingField() {
		BeanFixture target = new BeanFixture();
		target.setField("foo");
		String string = (String) new DefaultInvocationHandler<Object>(provider, target).getterFor(new Mirror(provider)
				.on(BeanFixture.class).reflect().field("field"));
		assertEquals("foo", string);
	}
}
