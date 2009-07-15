package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
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

    public List<Annotation> atClass() {
        return provider.getAnnotatedElementReflectionProvider(clazz).getAnnotations();
    }

    public List<Annotation> atField(final String fieldName) {
        Field field = new Mirror(provider).on(clazz).reflect().field(fieldName);
        if (field == null) {
            throw new IllegalArgumentException("could not find field " + fieldName + " at class " + clazz);
        }
        return provider.getAnnotatedElementReflectionProvider(field).getAnnotations();
    }

    public AllMethodAnnotationsHandler atMethod(final String methodName) {
        return new DefaultAllMethodAnnotationsHandler(provider, clazz, methodName);
    }

}
