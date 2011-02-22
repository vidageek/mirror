/**
 * 
 */
package net.vidageek.mirror.dsl;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.proxy.OneClass;
import net.vidageek.mirror.proxy.OneInterface;
import net.vidageek.mirror.proxy.OtherClass;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class MirrorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testThatReflectClassThrowsExceptionIfClassNameIsNull() {
		new Mirror().reflectClass(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatReflectClassThrowsExceptionIfClassNameIsEmpty() {
		new Mirror().reflectClass("  \n  \t  ");
	}

	@Test(expected = MirrorException.class)
	public void testThatReflectClassThrowsMirrorExceptionIfClassDoesntExists() {
		new Mirror().reflectClass("some.class.that.doesnt.Exists");
	}

	@Test
	public void testThatReflectedClassIsCorrect() {
		final Class<?> clazz = new Mirror().reflectClass("net.vidageek.mirror.fixtures.ClassFixture");

		assertEquals(ClassFixture.class, clazz);
	}

	@Test(expected = MirrorException.class)
	public void testThatReflectInvalidPrimitiveClassThrowsMirrorExceptionIfClassDoesntExists() {
		new Mirror().reflectClass("Int");
	}

	@Test
	public void testThatReflectedPrimitiveClassIsCorrect() {
		final Class<?> clazz = new Mirror().reflectClass("int");

		assertEquals(int.class, clazz);
	}

	@Test
	public void testThatReflectedVoidClassIsCorrect() {
		final Class<?> clazz = new Mirror().reflectClass("void");

		assertEquals(void.class, clazz);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatProxifyClassThrowsExceptionIfClassNameIsEmpty() {
		new Mirror().proxify("  \n  \t  ");
	}

	@Test(expected = MirrorException.class)
	public void testThatProxifyClassThrowsMirrorExceptionIfClassDoesntExists() {
		new Mirror().proxify("some.class.that.doesnt.Exists");
	}

	@Test
	public void testThatProxifyOneInterfaceIsCorrect() {
		OneInterface proxy = new Mirror().proxify(OneInterface.class).interceptingWith(new MethodInterceptor() {

			public boolean accepts(final Method method) {
				return true;
			}

			public Object intercepts(final Object target, final Method method, final Object... parameters) {
				return "foo";
			}
		});

		assertEquals("foo", proxy.interfaceMethod());
	}

	@Test
	public void testThatProxifyMoreThanOneInterfaceIsCorrect() {
		Object proxy = new Mirror().proxify(OneClass.class).interceptingWith(new MethodInterceptor() {

			public boolean accepts(final Method method) {
				return true;
			}

			public Object intercepts(final Object target, final Method method, final Object... parameters) {
				return "foo";
			}
		});

		// assertEquals("foo", ((OneInterface) proxy).interfaceMethod());
		// assertEquals("foo", ((OtherInterface) proxy).otherInterfaceMethod());
		assertEquals("foo", ((OtherClass) proxy).classMethod());
	}
}
