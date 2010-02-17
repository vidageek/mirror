package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

public interface AnnotationHandler<T extends Annotation> {
	/**
	 * Use this method to reflect a single annotation at field named fieldName
	 * 
	 * @param fieldName
	 *            name of the field from where to search for annotations.
	 * @return The annotation or null if it was not found.
	 * @throws IllegalArgumentException
	 *             if fieldName is null or empty
	 * @see AnnotatedElementReflectionProvider#getAnnotation(Class)
	 */
	public T atField(final String fieldName);

	/**
	 * Use this method to reflect a single annotation at method named methodName
	 * 
	 * @param methodName
	 *            name of the method from where to search for annotations.
	 * @return An object responsible for reflecting annotations on a method.
	 * @throws IllegalArgumentException
	 *             if fieldName is null or empty
	 * @see AnnotatedElementReflectionProvider#getAnnotation(Class)
	 */
	public MethodAnnotationHandler<T> atMethod(final String methodName);

	/**
	 * Use this method to reflect a single annotation at class
	 * 
	 * @return The annotation or null if it was not found.
	 * @see AnnotatedElementReflectionProvider#getAnnotation(Class)
	 */
	public T atClass();
}
