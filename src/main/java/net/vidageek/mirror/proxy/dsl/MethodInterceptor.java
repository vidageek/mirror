package net.vidageek.mirror.proxy.dsl;

import java.lang.reflect.Method;

/**
 * @author jonasabreu
 * @author Juliano Alves
 * 
 */
public interface MethodInterceptor {

	boolean accepts(final Method method);
	
	Object intercepts(final Object target, final Method method, final Object... parameters);
}
