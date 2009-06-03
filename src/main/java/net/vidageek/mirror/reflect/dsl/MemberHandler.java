package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

public interface MemberHandler {
    /**
     * Use this method to reflect a single annotation from the AccessibleObject
     * 
     * @param annotation
     *            Annotation type to be reflected.
     * @return The annotation or null if it was not found.
     * @see AnnotatedElementReflectionProvider#getAnnotation(Class)
     */
    public <T extends Annotation> T annotation(final Class<T> annotation) ;
}
