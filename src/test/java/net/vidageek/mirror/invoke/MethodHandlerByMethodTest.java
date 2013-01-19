/**
 * 
 */
package net.vidageek.mirror.invoke;

import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.MethodExceptionFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class MethodHandlerByMethodTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() {
		Method method = new Mirror(provider).on(Object.class).reflect().method("equals").withArgs(Object.class);

		new MethodHandlerByMethod(provider, new Object(), null, method);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodIsNull() {

		new MethodHandlerByMethod(provider, new Object(), Object.class, null);
	}

	@Test
	public void testThatAcceptsMethodAssignableFromClass() {
		Method method = new Mirror(provider).on(Object.class).reflect().method("equals").withArgs(Object.class);

		new MethodHandlerByMethod(provider, new Object(), String.class, method);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodIsNotFromClass() {
		Method method = new Mirror(provider).on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();

		new MethodHandlerByMethod(provider, new Object(), String.class, method);
	}

	@Test
	public void testThatDoesntThrowsExceptionIfObjectIsNull() {
		Method method = new Mirror().on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();

		new MethodHandlerByMethod(provider, null, MethodFixture.class, method);
	}

	@Test(expected = IllegalStateException.class)
	public void testThrowsExceptionIfCallInstanceMethodOnClass() {
		Method method = new Mirror().on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();

		new Mirror().on(MethodFixture.class).invoke().method(method).withoutArgs();
	}

	@Test
	public void testThatThrowsInvocationTargetExceptionCause() {
		Method method = new Mirror().on(MethodExceptionFixture.class).reflect().method("method").withoutArgs();

		try {
			new Mirror().on(MethodExceptionFixture.class).invoke().method(method).withoutArgs();
		} catch (MirrorException e) {
			if (!RuntimeException.class.equals(e.getCause().getClass())) {
				fail("Exception cause should be RuntimeException.class. Was " + e.getCause());
			}
		}
	}
}
