package net.vidageek.mirror.proxy.javassist;

import java.util.List;

import javassist.util.proxy.ProxyFactory;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ProxyReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

public class JavassistProxyReflectionProvider implements ProxyReflectionProvider {

	private final Class<?> clazz;
	private final Class<?>[] interfaces;
	private final MethodInterceptor[] methodInterceptors;

	public JavassistProxyReflectionProvider(final Class<?> clazz, final List<Class<?>> interfaces,
			final MethodInterceptor... methodInterceptors) {
		this.clazz = clazz;
		this.interfaces = interfaces.toArray(new Class<?>[interfaces.size()]);
		this.methodInterceptors = methodInterceptors;
	}

	public Object createProxy() {
		ProxyFactory factory = new ProxyFactory();
		factory.setSuperclass(clazz);
		factory.setInterfaces(interfaces);

		try {
			return factory.create(new Class[0], new Object[0], new JavassistInvocationHandler(methodInterceptors));
		} catch (Exception e) {
			throw new MirrorException("Proxy could not be created", e);
		}
	}
}
