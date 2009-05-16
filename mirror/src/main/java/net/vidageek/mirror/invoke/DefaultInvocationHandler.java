package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.vidageek.mirror.invoke.dsl.ConstructorHandler;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;
import net.vidageek.mirror.invoke.dsl.MethodHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * This class is responsible for choosing between invocation of constructor or
 * methods.
 * 
 * @author jonasabreu
 */
public final class DefaultInvocationHandler<T> implements InvocationHandler<T> {

	private final Object target;

	private final Class<?> clazz;

	private final ReflectionProvider provider;

	public DefaultInvocationHandler(final ReflectionProvider provider, final Object target) {
		if (target == null) {
			throw new IllegalArgumentException("target can't be null");
		}
		this.provider = provider;
		this.target = target;
		clazz = target.getClass();
	}

	public DefaultInvocationHandler(final ReflectionProvider provider, final Class<T> target) {
		if (target == null) {
			throw new IllegalArgumentException("target can't be null");
		}
		this.provider = provider;
		clazz = target;
		this.target = null;
	}

	/**
	 * Use this method to invoke a method by its name.
	 * 
	 * @param methodName
	 *            name of the method to be invoked.
	 * @return An object responsible for method invocation.
	 * @throws IllegalArgumentException
	 *             if methodName is null.
	 */
	public MethodHandler method(final String methodName) {
		if (methodName == null) {
			throw new IllegalArgumentException("methodName can't be null");
		}
		return new MethodHandlerByName(provider, target, clazz, methodName);
	}

	/**
	 * Use this method to invoke a constructor using only arguments.
	 * 
	 * @return An object responsible by constructor invocation.
	 * @throws IllegalStateException
	 *             if class was not provided.
	 */
	@SuppressWarnings("unchecked")
	public ConstructorHandler<T> constructor() {
		if (this.target != null) {
			throw new IllegalStateException(
					"must use constructor InvocationHandler(Class<T>) instead of InvocationHandler(Object).");
		}
		return new ConstructorHandlerByArgs<T>(provider, (Class<T>) clazz);
	}

	/**
	 * Use this method to invoke a method using its instance.
	 * 
	 * @param method
	 *            Method to be invoked.
	 * @return An object responsible for method invocation.
	 */
	public MethodHandler method(final Method method) {
		return new MethodHandlerByMethod(provider, target, clazz, method);
	}

	/**
	 *Use this method to invoke a constructor using its instance.
	 * 
	 * @param con
	 *            Constructor to be invoked.
	 * @return An object responsible for constructor invocation.
	 */
	@SuppressWarnings("unchecked")
	public <C> ConstructorHandler<C> constructor(final Constructor<C> con) {
		return new ConstructorHandlerByConstructor(provider, clazz, con);
	}
}
