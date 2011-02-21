package net.vidageek.mirror.proxy.cglib;

import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.vidageek.mirror.provider.ProxyReflectionProvider;

/**
 * 
 * @author Juliano Alves
 *
 */
public class CGLibProxyReflectionProvider implements ProxyReflectionProvider {

	private final Class<?> clazz;
	private final Class<?>[] interfaces;
	private final CGLibInvocationHandler invocationHandler;

	public CGLibProxyReflectionProvider(final Class<?> clazz,
			final List<Class<?>> interfaces, final CGLibInvocationHandler invocationHandler) {
		this.clazz = clazz;
		this.interfaces = interfaces.toArray(new Class<?>[interfaces.size()]);
		this.invocationHandler = invocationHandler;
	}

	public Object createProxy() {
		return Enhancer.create(clazz, interfaces, invocationHandler);
	}
}
