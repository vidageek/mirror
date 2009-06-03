package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllMemberHandler;

/**
 * This class is responsible for reflecting all annotations on any
 * AccessibleObject
 * 
 * @author dnfeitosa
 */
public final class DefaultAllMemberHandler implements AllMemberHandler {

    private final AnnotatedElement member;

    private final ReflectionProvider provider;

    public DefaultAllMemberHandler(final ReflectionProvider provider, final AnnotatedElement member) {
        if (member == null) {
            throw new IllegalArgumentException("Argument member cannot be null");
        }
        this.provider = provider;
        this.member = member;
    }

    /**
     * Use this method to reflect a list of annotations on the AccessibleObject
     * 
     * @return The list of annotation or a empty list if none was found.
     * @see AnnotatedElementReflectionProvider#getAnnotations()
     */
    public List<Annotation> annotations() {
        return provider.getAnnotatedElementReflectionProvider(member).getAnnotations();
    }

}
