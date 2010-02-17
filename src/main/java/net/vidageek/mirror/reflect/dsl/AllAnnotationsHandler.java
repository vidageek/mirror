package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

public interface AllAnnotationsHandler {
	/**
	 * Use this method to reflect all annotations at clazz.
	 * 
	 * @return A list containing all annotations found at clazz.
	 * @see AnnotatedElementReflectionProvider#getAnnotations()
	 */
	public List<Annotation> atClass();

	/**
	 * Use this method to reflect all annotations at field fieldName.
	 * 
	 * @param fieldName
	 *            Name of the field from where to search for annotations.
	 * @return A list containing all annotations found at field.
	 * @see AnnotatedElementReflectionProvider#getAnnotations()
	 */
	public List<Annotation> atField(final String fieldName);

	/**
	 * Use this method to reflect all annotations at method.
	 * 
	 * @param methodName
	 *            Name of the method from where to search for annotations.
	 * @return An object responsible for reflecting annotations at method.
	 */
	public AllMethodAnnotationsHandler atMethod(final String methodName);
}
