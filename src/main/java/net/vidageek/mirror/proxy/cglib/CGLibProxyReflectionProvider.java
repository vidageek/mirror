package net.vidageek.mirror.proxy.cglib;

import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.vidageek.mirror.provider.ProxyReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

/**
 * 
 * @author Juliano Alves
 * 
 */
public class CGLibProxyReflectionProvider implements ProxyReflectionProvider {

	private final Class<?> clazz;
	private final Class<?>[] interfaces;
	private final MethodInterceptor[] methodInterceptors;

	public CGLibProxyReflectionProvider(final Class<?> clazz, final List<Class<?>> interfaces,
			final MethodInterceptor... methodInterceptors) {
		this.clazz = clazz;
		this.interfaces = interfaces.toArray(new Class<?>[interfaces.size()]);
		this.methodInterceptors = methodInterceptors;
	}

	public Object createProxy() {
		return Enhancer.create(clazz, interfaces, new CGLibInvocationHandler(methodInterceptors));
	}
}
