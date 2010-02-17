package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

public interface AllMethodAnnotationsHandler {
	/**
	 * This is a convenience method for
	 * {@link AllMethodAnnotationsHandler#withArgs(Class...)}
	 * 
	 * @see AllMethodAnnotationsHandler#withArgs(Class...)
	 */
	public List<Annotation> withoutArgs();

	/**
	 * Use this method to reflect a list of annotations on the method that
	 * matches classes as its argument list.
	 * 
	 * @param classes
	 *            Argument list of the method
	 * @return The list of annotation or a empty list if none was found.
	 * @see AnnotatedElementReflectionProvider#getAnnotations()
	 */
	public List<Annotation> withArgs(final Class<?>... classes);

}
