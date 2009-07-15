/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Matcher;
import net.vidageek.mirror.matcher.GetterMatcher;
import net.vidageek.mirror.matcher.ListFilter;
import net.vidageek.mirror.matcher.SetterMatcher;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllAnnotationsHandler;
import net.vidageek.mirror.reflect.dsl.AllReflectionHandler;

/**
 * This class is responsible for reflecting lists of reflection elements
 * 
 * @author jonasabreu
 */
public final class DefaultAllReflectionHandler<T> implements AllReflectionHandler<T> {

    private final Class<T> clazz;

    private final ReflectionProvider provider;

    public DefaultAllReflectionHandler(final ReflectionProvider provider, final Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.provider = provider;
        this.clazz = clazz;
    }

    public List<Field> fields() {
        return provider.getClassReflectionProvider(clazz).reflectAllFields();
    }

    public List<Method> methods() {
        return provider.getClassReflectionProvider(clazz).reflectAllMethods();
    }

    public List<Constructor<T>> constructors() {
        return provider.getClassReflectionProvider(clazz).reflectAllConstructors();
    }

    public AllAnnotationsHandler annotations() {
        return new DefaultAllAnnotationsHandler(provider, clazz);
    }

    public List<Method> setters() {
        return new ListFilter().filter(new SetterMatcher(), methods());
    }

    public List<Method> getters() {
        return new ListFilter().filter(new GetterMatcher(), methods());
    }

    public List<Field> fieldsMatching(final Matcher<Field> matcher) {
        return new ListFilter().filter(matcher, fields());
    }

    public List<Method> methodsMatching(final Matcher<Method> matcher) {
        return new ListFilter().filter(matcher, methods());
    }

    public List<Constructor<T>> constructorsMatching(final Matcher<Constructor<T>> matcher) {
        return new ListFilter().filter(matcher, constructors());
    }

    public List<Annotation> annotationsMatching(final Matcher<Annotation> matcher) {
        return new ListFilter().filter(matcher, annotations().atClass());
    }

}
