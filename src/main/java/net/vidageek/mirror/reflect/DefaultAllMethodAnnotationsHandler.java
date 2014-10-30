package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
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

	public DefaultAllMethodAnnotationsHandler(final ReflectionProvider provider, final Class<?> clazz,
			final String methodName) {
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");
		Preconditions.checkArgument(methodName != null && methodName.trim().length() > 0, "methodName cannot be null or empty");

		this.provider = provider;
		this.clazz = clazz;
		this.methodName = methodName.trim();
	}

	public List<Annotation> withoutArgs() {
		return withArgs(new Class<?>[0]);
	}

	public List<Annotation> withArgs(final Class<?>... classes) {
		Method method = new Mirror(provider).on(clazz).reflect().method(methodName).withArgs(classes);
		if (method == null) {
			throw new MirrorException("could not find method that matched " + Arrays.asList(classes));
		}
		return provider.getAnnotatedElementReflectionProvider(method).getAnnotations();
	}
}
