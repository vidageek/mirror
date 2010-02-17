package net.vidageek.mirror.provider.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

/**
 * @author jonasabreu
 * 
 */
public final class PureJavaAnnotatedElementReflectionProvider implements AnnotatedElementReflectionProvider {

	private final AnnotatedElement element;

	public PureJavaAnnotatedElementReflectionProvider(final AnnotatedElement element) {
		this.element = element;
	}

	public List<Annotation> getAnnotations() {
		return Arrays.asList(element.getAnnotations());
	}

	public <T extends Annotation> T getAnnotation(final Class<T> annotation) {
		return element.getAnnotation(annotation);
	}

}
