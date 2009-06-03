/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.reflect.Field;

import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaClassGenericTypeAccessor;
import net.vidageek.mirror.reflect.dsl.AnnotationHandler;
import net.vidageek.mirror.reflect.dsl.ConstructorReflector;
import net.vidageek.mirror.reflect.dsl.MethodReflector;
import net.vidageek.mirror.reflect.dsl.ParameterizedElementHandler;
import net.vidageek.mirror.reflect.dsl.ReflectionHandler;

/**
 * This part of the DSL is used to choose reflection element is going to be
 * reflected.
 * 
 * @author jonasabreu
 */
public final class DefaultReflectionHandler<T> implements ReflectionHandler<T>{

	private final Class<T> clazz;

	private final ReflectionProvider provider;

	public DefaultReflectionHandler(final ReflectionProvider provider,
			final Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("clazz cannot be null");
		}
		this.provider = provider;
		this.clazz = clazz;
	}

	/**
	 * Use this method to reflect a field by its name.
	 * 
	 * @param fieldName
	 *            Name of the field to be reflected.
	 * @return A field identified by fieldName or null if none is found.
	 * @throws IllegalArgumentException
	 *             if fieldName is null or empty
	 * @see DefaultFieldReflector#onClass(Class)
	 */
	public Field field(final String fieldName) {
		if (fieldName == null || fieldName.trim().length() == 0) {
			throw new IllegalArgumentException(
					"fieldName cannot be null or empty.");
		}
		return new DefaultFieldReflector(provider, fieldName).onClass(clazz);
	}

	/**
	 * Use this method to choose the name of the method to reflect.
	 * 
	 * @param methodName
	 *            name of the method to be reflected
	 * @return An object responsible for reflecting the method.
	 * @throws IllegalArgumentException
	 *             if methodName is null or empty
	 * @see DefaultMethodReflector
	 */
	public MethodReflector method(final String methodName) {
		if (methodName == null || methodName.trim().length() == 0) {
			throw new IllegalArgumentException(
					"methodName cannot be null or empty.");
		}
		return new DefaultMethodReflector(provider, methodName, clazz);
	}

	/**
	 * Use this method to reflect a constructor
	 * 
	 * @return An object responsible for reflecting constructors.
	 * @see DefaultConstructorReflector
	 */
	public ConstructorReflector<T> constructor() {
		return new DefaultConstructorReflector<T>(provider, clazz);
	}

	/**
	 * Use this method to reflect annotations.
	 * 
	 * @param annotation
	 *            Type of the annotation to be reflected.
	 * @return An object responsible for reflecting annotations.
	 * @see DefaultAnnotationHandler
	 */
	@SuppressWarnings("unchecked")
	public <A> AnnotationHandler<? extends A> annotation(
			final Class<A> annotation) {
		return new DefaultAnnotationHandler(provider, clazz, annotation);
	}

	public ParameterizedElementHandler parentGenericType() {
		PureJavaClassGenericTypeAccessor accessor = new PureJavaClassGenericTypeAccessor(
				clazz);
		return new DefaultParameterizedElementHandler(provider, accessor);
	}
}
