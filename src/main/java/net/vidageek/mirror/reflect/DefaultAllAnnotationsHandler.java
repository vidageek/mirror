package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllAnnotationsHandler;
import net.vidageek.mirror.reflect.dsl.AllMethodAnnotationsHandler;

/**
 * This class is responsible for choosing where to reflect annotations.
 * 
 * @author dnfeitosa
 */
public final class DefaultAllAnnotationsHandler implements AllAnnotationsHandler {

	private final Class<?> clazz;

	private final ReflectionProvider provider;

	public DefaultAllAnnotationsHandler(final ReflectionProvider provider, final Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Argument clazz cannot be null.");
		}
		this.provider = provider;
		this.clazz = clazz;
	}

	/**
	 * Use this method to reflect all annotations at clazz.
	 * 
	 * @return A list containing all annotations found at clazz.
	 * @see AnnotatedElementReflectionProvider#getAnnotations()
	 */
	public List<Annotation> atClass() {
		return provider.getAnnotatedElementReflectionProvider(clazz).getAnnotations();
	}

	/**
	 * Use this method to reflect all annotations at field fieldName.
	 * 
	 * @param fieldName
	 *            Name of the field from where to search for annotations.
	 * @return A list containing all annotations found at field.
	 * @see AnnotatedElementReflectionProvider#getAnnotations()
	 */
	public List<Annotation> atField(final String fieldName) {
		Field field = new Mirror(provider).on(clazz).reflect().field(fieldName);
		if (field == null) {
			throw new IllegalArgumentException("could not find field " + fieldName + " at class " + clazz);
		}
		return provider.getAnnotatedElementReflectionProvider(field).getAnnotations();
	}

	/**
	 * Use this method to reflect all annotations at method.
	 * 
	 * @param methodName
	 *            Name of the method from where to search for annotations.
	 * @return An object responsible for reflecting annotations at method.
	 */
	public AllMethodAnnotationsHandler atMethod(final String methodName) {
		return new DefaultAllMethodAnnotationsHandler(provider, clazz, methodName);
	}

}
