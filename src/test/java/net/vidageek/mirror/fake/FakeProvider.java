/**
 * 
 */
package net.vidageek.mirror.fake;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.ClassReflectionProvider;
import net.vidageek.mirror.provider.ConstructorBypassingReflectionProvider;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.GenericTypeAccessor;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.ParameterizedElementReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * @author jonasabreu
 * 
 */
public class FakeProvider implements ReflectionProvider {

    public AnnotatedElementReflectionProvider getAnnotatedElementReflectionProvider(final AnnotatedElement element) {
        return null;
    }

    public ClassReflectionProvider<? extends Object> getClassReflectionProvider(final String className) {
        return null;
    }

    public <T> ClassReflectionProvider<T> getClassReflectionProvider(final Class<T> clazz) {
        return null;
    }

    public <T> ConstructorReflectionProvider<T> getConstructorReflectionProvider(final Class<T> clazz,
            final Constructor<T> constructor) {
        return null;
    }

    public FieldReflectionProvider getFieldReflectionProvider(final Object target, final Class<?> clazz,
            final Field field) {
        return null;
    }

    public MethodReflectionProvider getMethodReflectionProvider(final Object target, final Class<?> clazz,
            final Method method) {
        return null;
    }

    public ParameterizedElementReflectionProvider getParameterizedElementProvider(final GenericTypeAccessor accessor) {
        return null;
    }

    public GenericTypeAccessor getClassGenericTypeAccessor(final Class<?> clazz) {
        return null;
    }

    public GenericTypeAccessor getFieldGenericTypeAccessor(final Field field) {
        return null;
    }

    public <T> ConstructorBypassingReflectionProvider getConstructorBypassingReflectionProvider(final Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }

}
