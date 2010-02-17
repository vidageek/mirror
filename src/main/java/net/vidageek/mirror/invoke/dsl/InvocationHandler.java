package net.vidageek.mirror.invoke.dsl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
	public ConstructorHandler<T> constructor();

	/**
	 * Use this method to invoke a method using its instance.
	 * 
	 * @param method
	 *            Method to be invoked.
	 * @return An object responsible for method invocation.
	 */
	public MethodHandler method(final Method method);

	/**
	 *Use this method to invoke a constructor using its instance.
	 * 
	 * @param con
	 *            Constructor to be invoked.
	 * @return An object responsible for constructor invocation.
	 */
	public <C> ConstructorHandler<C> constructor(final Constructor<C> con);

	/**
	 * This is a convenience method for invoking getters that follow JavaBean
	 * notation
	 * 
	 * @param fieldName
	 * 
	 * @return Value of the field fieldName
	 */
	public Object getterFor(String fieldName);

	/**
	 * This is a convenience method for invoking getters that follow JavaBean
	 * notation
	 * 
	 * @param field
	 *            field associated with the getter
	 * 
	 * @return Value of the field fieldName
	 */
	public Object getterFor(Field field);

	/**
	 * This is a convenience method for invoking setters that follow JavaBean
	 * notation
	 * 
	 * @param fieldName
	 * 
	 * @return An object responsible for setting values
	 */
	public SetterMethodHandler setterFor(String fieldName);

	/**
	 * This is a convenience method for invoking setters that follow JavaBean
	 * notation
	 * 
	 * @param field
	 * 
	 * @return An object responsible for setting values
	 */
	public SetterMethodHandler setterFor(Field field);

}
