/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.reflect.Field;

import net.vidageek.mirror.Preconditions;
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
public final class DefaultReflectionHandler<T> implements ReflectionHandler<T> {

	private final Class<T> clazz;

	private final ReflectionProvider provider;

	public DefaultReflectionHandler(final ReflectionProvider provider, final Class<T> clazz) {
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");
		this.provider = provider;
		this.clazz = clazz;
	}

	public Field field(final String fieldName) {
		Preconditions.checkArgument(fieldName != null && fieldName.trim().length() > 0, "fieldName cannot be null or empty");
		return new DefaultFieldReflector(provider, fieldName).onClass(clazz);
	}

	public MethodReflector method(final String methodName) {
		Preconditions.checkArgument(methodName != null && methodName.trim().length() > 0, "methodName cannot be null or empty");
		return new DefaultMethodReflector(provider, methodName, clazz);
	}

	public ConstructorReflector<T> constructor() {
		return new DefaultConstructorReflector<T>(provider, clazz);
	}

	@SuppressWarnings("unchecked")
	public <A> AnnotationHandler<? extends A> annotation(final Class<A> annotation) {
		return new DefaultAnnotationHandler(provider, clazz, annotation);
	}

	public ParameterizedElementHandler parentGenericType() {
		PureJavaClassGenericTypeAccessor accessor = new PureJavaClassGenericTypeAccessor(clazz);
		return new DefaultParameterizedElementHandler(provider, accessor);
	}
}
