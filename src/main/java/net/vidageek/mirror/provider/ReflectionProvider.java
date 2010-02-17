package net.vidageek.mirror.provider;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 * This is the interface defines methods to get all specific reflection
 * providers.
 * 
 * @author jonasabreu
 */
public interface ReflectionProvider {

	/**
	 * @param className
	 *            Full qualified name of the class that is going to be wrapped
	 *            by a {@link ClassReflectionProvider}.
	 * @see ClassReflectionProvider
	 * @throws ReflectionProviderException
	 */
	ClassReflectionProvider<? extends Object> getClassReflectionProvider(String className);

	/**
	 * @param clazz
	 *            Class that is going to be wrapped by a
	 *            {@link ClassReflectionProvider}.
	 * @see ClassReflectionProvider
	 * @throws ReflectionProviderException
	 */
	<T> ClassReflectionProvider<T> getClassReflectionProvider(Class<T> clazz);

	/**
	 * @param clazz
	 *            Class of the constructor
	 * @param constructor
	 *            constructor that is going to be wrapped by
	 *            {@link ConstructorReflectionProvider}
	 * @see ConstructorReflectionProvider
	 * @throws ReflectionProviderException
	 */
	<T> ConstructorReflectionProvider<T> getConstructorReflectionProvider(Class<T> clazz, Constructor<T> constructor);

	/**
	 * @param clazz
	 *            Class to be instantiated without a constructor.
	 * @throws ReflectionProviderException
	 */
	<T> ConstructorBypassingReflectionProvider<T> getConstructorBypassingReflectionProvider(final Class<T> clazz);

	/**
	 * @param target
	 *            Object where method will be invoked. May be null if method is
	 *            a static method.
	 * @param clazz
	 *            class where this method can be found.
	 * @param method
	 *            method to be wrapped by {@link MethodReflectionProvider}
	 * @see MethodReflectionProvider
	 * @throws ReflectionProviderException
	 */
	MethodReflectionProvider getMethodReflectionProvider(Object target, Class<?> clazz, Method method);

	/**
	 * @param target
	 *            Object where this field can be found. May be null if field is
	 *            a static field.
	 * @param clazz
	 *            Class where this field can be found.
	 * @param field
	 *            Field to be wrapped by {@link FieldReflectionProvider}
	 * @see FieldReflectionProvider
	 * @throws ReflectionProviderException
	 */
	FieldReflectionProvider getFieldReflectionProvider(Object target, Class<?> clazz, Field field);

	/**
	 * @param element
	 *            AnnotatedElement to be wrapped by
	 *            {@link AnnotatedElementReflectionProvider}
	 * @see AnnotatedElementReflectionProvider
	 * @throws ReflectionProviderException
	 */
	AnnotatedElementReflectionProvider getAnnotatedElementReflectionProvider(AnnotatedElement element);

	/**
	 * @param accessor
	 *            Object that has the proper strategy to access class or field
	 *            generic types.
	 * @see ParameterizedElementReflectionProvider
	 * @throws ReflectionProviderException
	 */
	ParameterizedElementReflectionProvider getParameterizedElementProvider(GenericTypeAccessor accessor);

	/**
	 * @param clazz
	 *            Class to be reflected.
	 * @throws ReflectionProviderException
	 */
	GenericTypeAccessor getClassGenericTypeAccessor(Class<?> clazz);

	/**
	 * @param clazz
	 *            Field to be reflected.
	 * @throws ReflectionProviderException
	 */
	GenericTypeAccessor getFieldGenericTypeAccessor(Field field);
}
