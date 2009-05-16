package net.vidageek.mirror.invoke.dsl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public interface InvocationHandler<T> {
	/**
	 * Use this method to invoke a method by its name.
	 * 
	 * @param methodName
	 *            name of the method to be invoked.
	 * @return An object responsible for method invocation.
	 * @throws IllegalArgumentException
	 *             if methodName is null.
	 */
	public MethodHandler method(final String methodName);

	/**
	 * Use this method to invoke a constructor using only arguments.
	 * 
	 * @return An object responsible by constructor invocation.
	 * @throws IllegalStateException
	 *             if class was not provided.
	 */
	public ConstructorHandler<T> constructor() ;

	/**
	 * Use this method to invoke a method using its instance.
	 * 
	 * @param method
	 *            Method to be invoked.
	 * @return An object responsible for method invocation.
	 */
	public MethodHandler method(final Method method) ;

	/**
	 *Use this method to invoke a constructor using its instance.
	 * 
	 * @param con
	 *            Constructor to be invoked.
	 * @return An object responsible for constructor invocation.
	 */
	public <C> ConstructorHandler<C> constructor(final Constructor<C> con) ;
	
}
