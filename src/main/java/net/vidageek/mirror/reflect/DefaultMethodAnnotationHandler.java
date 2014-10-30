package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.MethodAnnotationHandler;

/**
 * This class is responsible for reflecting annotations on a method.
 * 
 * @author dnfeitosa
 */
public final class DefaultMethodAnnotationHandler<T extends Annotation> implements MethodAnnotationHandler<T> {

	private final Class<?> clazz;

	private final String methodName;

	private final Class<T> annotation;

	private final ReflectionProvider provider;

	public DefaultMethodAnnotationHandler(final ReflectionProvider provider, final Class<?> clazz,
			final String methodName, final Class<T> annotation) {
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");
		Preconditions.checkArgument(methodName != null && methodName.trim().length() > 0, "methodName cannot be null or empty");
		Preconditions.checkArgument(annotation != null, "annotation cannot be null");

		this.provider = provider;
		this.clazz = clazz;
		this.methodName = methodName.trim();
		this.annotation = annotation;
	}

	public T withArgs(final Class<?>... classes) {
		Method method = new DefaultMethodReflector(provider, methodName, clazz).withArgs(classes);
		if (method == null) {
			throw new MirrorException("could not find method matching argument list " + Arrays.asList(classes));
		}
		return provider.getAnnotatedElementReflectionProvider(method).getAnnotation(annotation);
	}

	public T withoutArgs() {
		return withArgs(new Class<?>[0]);
	}
}
