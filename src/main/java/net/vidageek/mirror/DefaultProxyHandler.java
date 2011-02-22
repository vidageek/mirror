package net.vidageek.mirror;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.provider.ProxyReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.proxy.cglib.CGLibInvocationHandler;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;
import net.vidageek.mirror.proxy.dsl.ProxyHandler;

/**
 * 
 * @author Juliano Alves
 * 
 */
public class DefaultProxyHandler implements ProxyHandler<Object> {

	private Class<?> baseClass = Object.class;
	private final List<Class<?>> interfaces = new ArrayList<Class<?>>();
	private final ReflectionProvider provider;

	public DefaultProxyHandler(final ReflectionProvider provider, final Class<?>[] classes) {
		this.provider = provider;
		extractBaseClassAndInterfaces(classes);
	}

	private void extractBaseClassAndInterfaces(final Class<?>[] classes) {
		boolean baseClassAlreadyFound = false;

		for (Class<?> clazz : classes) {
			if (clazz.isInterface()) {
				interfaces.add(clazz);
			} else if (!baseClassAlreadyFound) {
				if (isFinalClass(clazz)) {
					throw new IllegalArgumentException("Cannot proxify final class " + clazz.getName());
				}

				baseClassAlreadyFound = true;
				baseClass = clazz;
			} else {
				throw new IllegalArgumentException("Cannot proxify more than one concrete/abstract class");
			}
		}
	}

	private boolean isFinalClass(final Class<?> clazz) {
		return Modifier.isFinal(clazz.getModifiers());
	}

	public Object interceptingWith(final MethodInterceptor... interceptors) {
		if ((interceptors == null) || (interceptors.length == 0)) {
			throw new IllegalArgumentException("interceptors cannot be null or empty");
		}
		CGLibInvocationHandler invocationHandler = new CGLibInvocationHandler(interceptors);
		ProxyReflectionProvider proxyReflectionProvider = provider.getProxyReflectionProvider(	baseClass, interfaces,
																								invocationHandler);

		return proxyReflectionProvider.createProxy();
	}
}
