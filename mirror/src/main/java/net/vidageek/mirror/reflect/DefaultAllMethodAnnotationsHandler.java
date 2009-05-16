package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllMethodAnnotationsHandler;

/**
 * This class is responsible for reflecting all annotation on a method
 * 
 * @author dnfeitosa
 */
public final class DefaultAllMethodAnnotationsHandler implements AllMethodAnnotationsHandler {

	private final Class<?> clazz;

	private final String methodName;

	private final ReflectionProvider provider;

	public DefaultAllMethodAnnotationsHandler(final ReflectionProvider provider, final Class<?> clazz, final String methodName) {
		if (clazz == null) {
			throw new IllegalArgumentException("Argument clazz cannot be null.");
		}
		if (methodName == null || methodName.trim().length() == 0) {
			throw new IllegalArgumentException("Argument methodName cannot be null or blank.");
		}

		this.provider = provider;
		this.clazz = clazz;
		this.methodName = methodName.trim();
	}

	/**
	 * This is a convenience method for
	 * {@link DefaultAllMethodAnnotationsHandler#withArgs(Class...)}
	 * 
	 * @see DefaultAllMethodAnnotationsHandler#withArgs(Class...)
	 */
	public List<Annotation> withoutArgs() {
		return withArgs(new Class<?>[0]);
	}

	/**
	 * Use this method to reflect a list of annotations on the method that
	 * matches classes as its argument list.
	 * 
	 * @param classes
	 *            Argument list of the method
	 * @return The list of annotation or a empty list if none was found.
	 * @see AnnotatedElementReflectionProvider#getAnnotations()
	 */
	public List<Annotation> withArgs(final Class<?>... classes) {
		Method method = new Mirror(provider).on(clazz).reflect().method(methodName).withArgs(classes);
		if (method == null) {
			throw new IllegalArgumentException("could not find method that matched " + Arrays.asList(classes));
		}
		return provider.getAnnotatedElementReflectionProvider(method).getAnnotations();
	}
}
