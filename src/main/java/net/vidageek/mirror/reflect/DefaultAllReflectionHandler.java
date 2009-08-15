/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.BackedMirrorList;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.matcher.GetterMatcher;
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

    public MirrorList<Field> fields() {
        return new BackedMirrorList<Field>(provider.getClassReflectionProvider(clazz).reflectAllFields());
    }

    public MirrorList<Method> methods() {
        return new BackedMirrorList<Method>(provider.getClassReflectionProvider(clazz).reflectAllMethods());
    }

    public MirrorList<Constructor<T>> constructors() {
        return new BackedMirrorList<Constructor<T>>(provider.getClassReflectionProvider(clazz).reflectAllConstructors());
    }

    public AllAnnotationsHandler annotations() {
        return new DefaultAllAnnotationsHandler(provider, clazz);
    }

    public MirrorList<Method> setters() {
        return methods().matching(new SetterMatcher());
    }

    public MirrorList<Method> getters() {
        return methods().matching(new GetterMatcher());
    }

    public List<Field> fieldsMatching(final Matcher<Field> matcher) {
        return fields().matching(matcher);
    }

    public List<Method> methodsMatching(final Matcher<Method> matcher) {
        return methods().matching(matcher);
    }

    public List<Constructor<T>> constructorsMatching(final Matcher<Constructor<T>> matcher) {
        return constructors().matching(matcher);
    }

    public List<Annotation> annotationsMatching(final Matcher<Annotation> matcher) {
        return new BackedMirrorList<Annotation>(annotations().atClass()).matching(matcher);
    }

}
