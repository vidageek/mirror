/**
 * 
 */
package net.vidageek.mirror.dsl;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.OneClassFixture;
import net.vidageek.mirror.fixtures.OneInterfaceFixture;
import net.vidageek.mirror.fixtures.OtherInterfaceFixture;
import net.vidageek.mirror.provider.proxy.JavassistReflectionProvider;
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
	public void testThatProxifyOneInterfaceWithCglibIsCorrect() {
		OneInterfaceFixture proxy = new Mirror().proxify(OneInterfaceFixture.class)
				.interceptingWith(new MethodInterceptor() {

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
	public void testThatProxifyMoreThanOneInterfaceWithCglibIsCorrect() {
		Object proxy = new Mirror().proxify(OneClassFixture.class, OneInterfaceFixture.class,
											OtherInterfaceFixture.class).interceptingWith(new MethodInterceptor() {

			public boolean accepts(final Method method) {
				return true;
			}

			public Object intercepts(final Object target, final Method method, final Object... parameters) {
				return "foo";
			}
		});

		assertEquals("foo", ((OneInterfaceFixture) proxy).interfaceMethod());
		assertEquals("foo", ((OtherInterfaceFixture) proxy).otherInterfaceMethod());
		assertEquals("foo", ((OneClassFixture) proxy).classMethod());
	}
	
	@Test
	public void testThatProxifyOneInterfaceWithJavassistIsCorrect() {
		OneInterfaceFixture proxy = new Mirror(new JavassistReflectionProvider()).proxify(OneInterfaceFixture.class)
				.interceptingWith(new MethodInterceptor() {

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
	public void testThatProxifyMoreThanOneInterfaceWithJavassistIsCorrect() {
		Object proxy = new Mirror(new JavassistReflectionProvider()).proxify(OneClassFixture.class, OneInterfaceFixture.class,
											OtherInterfaceFixture.class).interceptingWith(new MethodInterceptor() {

			public boolean accepts(final Method method) {
				return true;
			}

			public Object intercepts(final Object target, final Method method, final Object... parameters) {
				return "foo";
			}
		});

		assertEquals("foo", ((OneInterfaceFixture) proxy).interfaceMethod());
		assertEquals("foo", ((OtherInterfaceFixture) proxy).otherInterfaceMethod());
		assertEquals("foo", ((OneClassFixture) proxy).classMethod());
	}
}
