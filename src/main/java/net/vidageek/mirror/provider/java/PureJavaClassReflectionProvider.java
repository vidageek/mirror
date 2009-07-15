package net.vidageek.mirror.provider.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.matcher.ClassArrayMatcher;
import net.vidageek.mirror.matcher.MatchType;
import net.vidageek.mirror.provider.ClassReflectionProvider;

public final class PureJavaClassReflectionProvider<T> implements ClassReflectionProvider<T> {

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public PureJavaClassReflectionProvider(final String className) {
        try {
            this.clazz = (Class<T>) Class.forName(className, false,
                    PureJavaClassReflectionProvider.class.getClassLoader());
        } catch (final ClassNotFoundException e) {
            this.clazz = (Class<T>) FixedType.fromValue(className);

            if (clazz == null) {
                throw new ReflectionProviderException("class " + className + " could not be found.", e);
            }
        }
    }

    public PureJavaClassReflectionProvider(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public Class<T> reflectClass() {
        return clazz;
    }

    public List<Field> reflectAllFields() {
        Class<?> c = clazz;
        final List<Field> list = new ArrayList<Field>();
        while (c != null) {
            list.addAll(Arrays.asList(c.getDeclaredFields()));
            for (Class<?> interf : c.getInterfaces()) {
                list.addAll(Arrays.asList(interf.getFields()));
            }
            c = c.getSuperclass();
        }
        return list;
    }

    public List<Method> reflectAllMethods() {
        Class<?> c = clazz;
        final List<Method> list = new ArrayList<Method>();
        while (c != null) {
            list.addAll(Arrays.asList(c.getDeclaredMethods()));
            c = c.getSuperclass();
        }
        return list;
    }

    // If somebody can tell me why on earth getDeclaredConstructors return
    // Constructor<?>[], I would be really glad.
    @SuppressWarnings("unchecked")
    public List<Constructor<T>> reflectAllConstructors() {
        return (List) Arrays.asList(clazz.getDeclaredConstructors());
    }

    public Constructor<T> reflectConstructor(final Class<?>[] argumentTypes) {
        final ClassArrayMatcher matcher = new ClassArrayMatcher(argumentTypes);

        Constructor<T> match = null;
        for (final Constructor<T> constructor : reflectAllConstructors()) {
            final MatchType matchType = matcher.match(constructor.getParameterTypes());
            if (MatchType.PERFECT.equals(matchType)) {
                match = constructor;
                break;
            } else if (MatchType.MATCH.equals(matchType)) {
                match = constructor;
            }
        }
        return match;
    }

    public Field reflectField(final String fieldName) {
        for (final Field f : reflectAllFields()) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }

    public Method reflectMethod(final String methodName, final Class<?>[] argumentTypes) {
        final ClassArrayMatcher matcher = new ClassArrayMatcher(argumentTypes);

        Method match = null;
        for (final Method method : reflectAllMethods()) {
            if (method.getName().equals(methodName)) {
                final MatchType matchType = matcher.match(method.getParameterTypes());
                if (MatchType.PERFECT.equals(matchType)) {
                    match = method;
                    break;
                } else if (MatchType.MATCH.equals(matchType)) {
                    match = method;
                }
            }
        }
        return match;
    }

}
