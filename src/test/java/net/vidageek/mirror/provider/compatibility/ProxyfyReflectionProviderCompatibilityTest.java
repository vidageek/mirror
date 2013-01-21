package net.vidageek.mirror.provider.compatibility;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.fixtures.OneInterfaceFixture;
import net.vidageek.mirror.fixtures.OtherInterfaceFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
final public class ProxyfyReflectionProviderCompatibilityTest implements ReflectionProviderDatapoints {

	@Theory
	public void testThatCanCreateAProxyOfANonFinalClass(final ReflectionProvider r) {
		Object proxy = r.getProxyReflectionProvider(Number.class, new ArrayList<Class<?>>(),
													new MethodInterceptor[] { new MethodInterceptor() {

														public Object intercepts(final Object target,
																final Method method, final Object... parameters) {
															return null;
														}

														public boolean accepts(final Method method) {
															return true;
														}
													} }).createProxy();

		assertTrue(Number.class.isAssignableFrom(proxy.getClass()));
	}

	@Theory
	public void testThatCanCreateAProxyOfAnInterfaceAndAObject(final ReflectionProvider r) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Object proxy = r.getProxyReflectionProvider(Object.class, (List) Arrays.asList(OtherInterfaceFixture.class),
													new MethodInterceptor[] { new MethodInterceptor() {

														public Object intercepts(final Object target,
																final Method method, final Object... parameters) {
															return null;
														}

														public boolean accepts(final Method method) {
															return true;
														}
													} }).createProxy();

		assertTrue(Object.class.isAssignableFrom(proxy.getClass()));
		assertTrue(OtherInterfaceFixture.class.isAssignableFrom(proxy.getClass()));
	}

	@Theory
	public void testThatCanCreateAProxyOfAClassAndMultipleInterfaces(final ReflectionProvider r) {
		@SuppressWarnings({ "unchecked" })
		Object proxy = r.getProxyReflectionProvider(Object.class,
													Arrays.asList(	OtherInterfaceFixture.class,
																	OneInterfaceFixture.class),
													new MethodInterceptor[] { new MethodInterceptor() {

														public Object intercepts(final Object target,
																final Method method, final Object... parameters) {
															return null;
														}

														public boolean accepts(final Method method) {
															return true;
														}
													} }).createProxy();

		assertTrue(Object.class.isAssignableFrom(proxy.getClass()));
		assertTrue(OtherInterfaceFixture.class.isAssignableFrom(proxy.getClass()));
		assertTrue(OneInterfaceFixture.class.isAssignableFrom(proxy.getClass()));
	}
}
