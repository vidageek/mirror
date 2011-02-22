package net.vidageek.mirror.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

/**
 * 
 * @author Juliano Alves
 * 
 */
public class DynamicInvocationHadler implements InvocationHandler {

	private final MethodInterceptor[] interceptors;

	public DynamicInvocationHadler(MethodInterceptor... methodInterceptors) {
		this.interceptors = methodInterceptors;
	}

	public Object invoke(Object target, Method method, Object[] parameters)
			throws Throwable {
		for (MethodInterceptor methodInterceptor : interceptors) {
			if (methodInterceptor.accepts(method)) {
				return methodInterceptor.intercepts(target, method, parameters);
			}
		}
		throw new Exception(method.getName() + " was not intercepted");
	}
}
