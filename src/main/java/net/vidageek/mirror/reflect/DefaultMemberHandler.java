package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.MemberHandler;

/**
 * This class is responsible for reflecting a single annotation from a
 * AccessibleObject
 * 
 * @author dnfeitosa
 */
public final class DefaultMemberHandler implements MemberHandler {

	private final AnnotatedElement member;

	private final ReflectionProvider provider;

	public DefaultMemberHandler(final ReflectionProvider provider, final AnnotatedElement member) {
		Preconditions.checkArgument(member != null, "member cannot be null");
		this.provider = provider;
		this.member = member;
	}

	public <T extends Annotation> T annotation(final Class<T> annotation) {
		return provider.getAnnotatedElementReflectionProvider(member).getAnnotation(annotation);
	}

}
