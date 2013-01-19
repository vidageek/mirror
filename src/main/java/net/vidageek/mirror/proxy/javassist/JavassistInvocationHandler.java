package net.vidageek.mirror.proxy.javassist;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import net.vidageek.mirror.exception.MethodNonInterceptedException;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

public class JavassistInvocationHandler implements MethodHandler {

	private final MethodInterceptor[] interceptors;

	public JavassistInvocationHandler(MethodInterceptor... methodInterceptors) {
		this.interceptors = methodInterceptors;
	}

	public Object invoke(Object target, Method method, Method proceed, Object[] parameters)
			throws Throwable {

		for (MethodInterceptor methodInterceptor : interceptors) {
			if (methodInterceptor.accepts(method)) {
				return methodInterceptor.intercepts(target, method, parameters);
			}
		}

		throw new MethodNonInterceptedException(method.getName() + " was not intercepted");
	}
}
