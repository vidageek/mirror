package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.ClassReflectionProvider;
import net.vidageek.mirror.provider.ConstructorBypassingReflectionProvider;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.GenericTypeAccessor;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.ParameterizedElementReflectionProvider;
import net.vidageek.mirror.provider.ProxyReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.ObjenesisConstructorBypassingReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaAnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaClassGenericTypeAccessor;
import net.vidageek.mirror.provider.java.PureJavaClassReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaFieldGenericTypeAccessor;
import net.vidageek.mirror.provider.java.PureJavaParameterizedElementReflectionProvider;
import net.vidageek.mirror.proxy.cglib.CGLibProxyReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

/**
 * This is a ReflectionProvider implementation that relies on some sun.reflect
 * classes to speedup reflection process.
 * 
 * @author jonasabreu
 * 
 */
final public class Sun15ReflectionProvider implements ReflectionProvider {

	public AnnotatedElementReflectionProvider getAnnotatedElementReflectionProvider(final AnnotatedElement element) {
		return new PureJavaAnnotatedElementReflectionProvider(element);
	}

	public GenericTypeAccessor getClassGenericTypeAccessor(final Class<?> clazz) {
		return new PureJavaClassGenericTypeAccessor(clazz);
	}

	public ClassReflectionProvider<? extends Object> getClassReflectionProvider(final String className) {
		return new PureJavaClassReflectionProvider<Object>(className);
	}

	public <T> ClassReflectionProvider<T> getClassReflectionProvider(final Class<T> clazz) {
		return new PureJavaClassReflectionProvider<T>(clazz);
	}

	public <T> ConstructorBypassingReflectionProvider<T> getConstructorBypassingReflectionProvider(final Class<T> clazz) {
		return new ObjenesisConstructorBypassingReflectionProvider<T>(clazz);
	}

	public <T> ConstructorReflectionProvider<T> getConstructorReflectionProvider(final Class<T> clazz,
			final Constructor<T> constructor) {
		return new Sun15ConstructorReflectionProvider<T>(clazz, constructor);
	}

	public GenericTypeAccessor getFieldGenericTypeAccessor(final Field field) {
		return new PureJavaFieldGenericTypeAccessor(field);
	}

	public FieldReflectionProvider getFieldReflectionProvider(final Object target, final Class<?> clazz,
			final Field field) {
		return new Sun15FieldReflectionProvider(target, clazz, field);
	}

	public MethodReflectionProvider getMethodReflectionProvider(final Object target, final Class<?> clazz,
			final Method method) {
		return new Sun15MethodReflectionProvider(target, clazz, method);
	}

	public ParameterizedElementReflectionProvider getParameterizedElementProvider(final GenericTypeAccessor accessor) {
		return new PureJavaParameterizedElementReflectionProvider(accessor);
	}

	public ProxyReflectionProvider getProxyReflectionProvider(final Class<?> clazz, final List<Class<?>> interfaces,
			final MethodInterceptor... methodInterceptors) {
		return new CGLibProxyReflectionProvider(clazz, interfaces, methodInterceptors);
	}
}
