/**
 * 
 */
package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.InterfaceFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class MethodReflectorTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodNameIsNull() {
		new DefaultMethodReflector(provider, null, MethodFixture.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodNameIsEmpty() {
		new DefaultMethodReflector(provider, " \n  \t  ", MethodFixture.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionClassIsNull() {
		new DefaultMethodReflector(provider, "method", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionClassesAreNull() {
		new DefaultMethodReflector(provider, "method", MethodFixture.class).withArgs((Class<?>[]) null);
	}

	@Test
	public void testThatCanReflectMethod() throws Exception {
		Method m = new DefaultMethodReflector(provider, "methodWithOneArg", MethodFixture.class).withArgs(String.class);
		assertNotNull(m);
		m.invoke(new MethodFixture(), "Any string");
	}

	@Test
	public void testThatCanReflectMethodWithPrimitiveType() throws Exception {
		Method m = new DefaultMethodReflector(provider, "methodWithTwoPrimitives", MethodFixture.class)
				.withArgs(int.class, boolean.class);
		assertNotNull(m);
		m.invoke(new MethodFixture(), 1, true);
	}

	@Test
	public void testThatCanReflectMethodWithWrapperType() throws Exception {
		Method m = new DefaultMethodReflector(provider, "methodWithTwoPrimitives", MethodFixture.class)
				.withArgs(Integer.class, Boolean.class);
		assertNotNull(m);
		m.invoke(new MethodFixture(), 1, true);
	}

	@Test
	public void testThatCanReflectSuperClassMethod() throws Exception {
		Method method = new DefaultMethodReflector(provider, "superClassMethod", ChildFixture.class).withoutArgs();
		assertNotNull(method);
		method.invoke(new ChildFixture());
	}

	@Test
	public void testThatCanReflectInterfaceMethods() throws Exception {
		Method method = new DefaultMethodReflector(provider, "interfaceMethod", ChildFixture.class)
				.withArgs(String.class);
		assertNotNull(method);
		method.invoke(new ChildFixture(), "");
	}

	@Test
	public void testThatCanReflectInterfaceMethodsOnInterfaces() {
		Method method = new DefaultMethodReflector(provider, "interfaceMethod", InterfaceFixture.class)
				.withArgs(String.class);

		assertNotNull(method);
	}

	@Test
	public void testThatFindsUniquelyNamedMethod() {
		Method method = new DefaultMethodReflector(provider, "methodWithOneArg", MethodFixture.class).withAnyArgs();
		assertNotNull(method);
		Method method2 = new DefaultMethodReflector(provider, "methodWithOneArg", MethodFixture.class)
				.withArgs(String.class);
		assertEquals(method2, method);
	}

	@Test
	public void testThatReturnsNullIfNoUniquelyNamedMethodIsFound() {
		Method method = new DefaultMethodReflector(provider, "someMethodThatDoesNotExists", MethodFixture.class)
				.withAnyArgs();
		assertNull(method);
	}

	@Test(expected = MirrorException.class)
	public void testThatThrowsMirrorExceptionIfMoreThanOneMethodIsFound() {
		new DefaultMethodReflector(provider, "overloadedMethod", MethodFixture.class).withAnyArgs();
	}

	@Test
	public void testThatWorksForOverridenMethods() {
		Method method = new DefaultMethodReflector(provider, "overridenMethod", ChildFixture.class).withAnyArgs();
		assertNotNull(method);
	}

	@Test
	public void testThatWorksForOverridenMethodsWithMoreThanOneArgument() {
		Method method = new DefaultMethodReflector(provider, "overridenMethodWithTwoArgs", ChildFixture.class)
				.withAnyArgs();
		assertNotNull(method);
	}

	@Test
	public void testThatWorksForOverridenMethodsOnMoreThanOneLevel() {
		Method method = new DefaultMethodReflector(provider, "equals", ChildFixture.class).withAnyArgs();
		assertNotNull(method);
	}
	
	@Test
	public void testGetParameterClass(){
		DefaultMethodReflector methodReflector = new DefaultMethodReflector(provider, "methodWithParametersfromSomeTypes", ChildFixture.class);
		Class clazzMustBePrimitiveInt = methodReflector.getParameterClass(0);
		Class clazzMustBeString = methodReflector.getParameterClass(1);
		Class clazzMustBeStringArray = methodReflector.getParameterClass(2);
		assertEquals(clazzMustBePrimitiveInt, int.class);
		assertEquals(clazzMustBeString, String.class);
		assertEquals(clazzMustBeStringArray, String[].class);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetParameterClassIndexOutOfBounds(){
		new DefaultMethodReflector(provider, "methodWithParametersfromSomeTypes", ChildFixture.class).getParameterClass(10);
	}	
}
