/**
 * 
 */
package net.vidageek.mirror.provider;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 * Interface that defines reflection operations related to annotatedElements
 * 
 * @author jonasabreu
 */
public interface AnnotatedElementReflectionProvider {

	/**
	 * This method is used to reflect all annotations from the AnnotatedElement
	 * wrapped.
	 * 
	 * @return A list containing all annotations if any existed. An empty list
	 *         if none is found.
	 * @throws ReflectionProviderException
	 */
	List<Annotation> getAnnotations();

	/**
	 * This method is used to reflect a single annotation from the
	 * AnnotatedElement wrapped.
	 * 
	 * @param annotation
	 *            Annotation to be reflected.
	 * @return The annotation if found, null if not.
	 * @throws ReflectionProviderException
	 */
	<T extends Annotation> T getAnnotation(Class<T> annotation);

}
