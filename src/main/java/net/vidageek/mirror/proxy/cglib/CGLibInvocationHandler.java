package net.vidageek.mirror.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;
import net.vidageek.mirror.exception.MethodNonInterceptedException;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

/**
 * 
 * @author Juliano Alves
 *
 */
public class CGLibInvocationHandler implements net.sf.cglib.proxy.MethodInterceptor {

	private final MethodInterceptor[] interceptors;

	public CGLibInvocationHandler(MethodInterceptor... methodInterceptors) {
		this.interceptors = methodInterceptors;
	}

	public Object intercept(Object target, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {

		for (MethodInterceptor methodInterceptor : interceptors) {
			if (methodInterceptor.accepts(method)) {
				return methodInterceptor.intercepts(target, method, args);
			}
		}

		throw new MethodNonInterceptedException(method.getName() + " was not intercepted");
	}
}
