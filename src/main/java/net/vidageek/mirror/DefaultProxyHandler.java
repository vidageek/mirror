package net.vidageek.mirror;

import static java.lang.reflect.Modifier.isFinal;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.provider.ProxyReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;
import net.vidageek.mirror.proxy.dsl.ProxyHandler;

/**
 * 
 * @author Juliano Alves
 * 
 */
public class DefaultProxyHandler<T> implements ProxyHandler<T> {

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
				if (isFinal(clazz.getModifiers())) {
					throw new IllegalArgumentException("Cannot proxify final class " + clazz.getName());
				}

				baseClassAlreadyFound = true;
				baseClass = clazz;
			} else {
				throw new IllegalArgumentException("Cannot proxify more than one concrete/abstract class");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public T interceptingWith(final MethodInterceptor... interceptors) {
		Preconditions.checkArgument(interceptors != null && interceptors.length > 0, "interceptors cannot be null or empty");

		ProxyReflectionProvider proxyReflectionProvider = provider.getProxyReflectionProvider(	baseClass, interfaces,
																								interceptors);

		return (T) proxyReflectionProvider.createProxy();
	}
}
