package net.vidageek.mirror.fixtures;

import java.lang.reflect.Method;

import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

/**
 * @author jonasabreu
 * @author juliano alves
 * 
 */
final public class MethodInterceptorFixture implements MethodInterceptor {

	public boolean accepts(Method method) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object intercepts(Object target, Method method, Object... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
