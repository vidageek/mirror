package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import sun.reflect.MethodAccessor;

@SuppressWarnings( { "restriction" })
public final class Method implements AnnotatedElement {

    public volatile MethodAccessor methodAccessor;

    public void acquireMethodAccessor() {
    }

    public String getName() {
        return null;
    }

    public <T extends Annotation> T getAnnotation(final Class<T> annotationType) {
        return null;
    }

    public Annotation[] getAnnotations() {
        return null;
    }

    public Annotation[] getDeclaredAnnotations() {
        return null;
    }

    public boolean isAnnotationPresent(final Class<? extends Annotation> annotationType) {
        return false;
    }

}
