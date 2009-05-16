package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;

public interface MethodAnnotationHandler<T extends Annotation>{
	
	/**
	 * Use this method to reflect a single annotation at the method that matches
	 * classes as its arguments.
	 * 
	 * @param classes
	 *            arguments of the method from where to search for annotations.
	 * @return The annotation or null if it was not found.
	 * @see AnnotatedElementReflectionProvider#getAnnotation(Class)
	 */
	public T withArgs(final Class<?>... classes) ;

	/**
	 * This is a convenience method for
	 * {@link MethodAnnotationHandler#withArgs(Class...)}
	 * 
	 * @see MethodAnnotationHandler#withArgs(Class...)
	 */
	public T withoutArgs() ;

}
