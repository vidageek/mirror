package net.vidageek.mirror.provider.java;

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
import net.vidageek.mirror.proxy.cglib.CGLibProxyReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

/**
 * Class which supplies all native Java reflection features plus some non
 * supported functions, like constructor bypassing.
 * 
 * @author jonasabreu
 */
public final class DefaultMirrorReflectionProvider implements ReflectionProvider {

	public ClassReflectionProvider<?> getClassReflectionProvider(final String className) {
		return new PureJavaClassReflectionProvider<Object>(className);
	}

	public <T> ClassReflectionProvider<T> getClassReflectionProvider(final Class<T> clazz) {
		return new PureJavaClassReflectionProvider<T>(clazz);
	}

	public FieldReflectionProvider getFieldReflectionProvider(final Object target, final Class<?> clazz,
			final Field field) {
		return new PureJavaFieldReflectionProvider(target, clazz, field);
	}

	public <T> ConstructorReflectionProvider<T> getConstructorReflectionProvider(final Class<T> clazz,
			final Constructor<T> constructor) {
		return new PureJavaConstructorReflectionProvider<T>(clazz, constructor);
	}

	public AnnotatedElementReflectionProvider getAnnotatedElementReflectionProvider(final AnnotatedElement element) {
		return new PureJavaAnnotatedElementReflectionProvider(element);
	}

	public MethodReflectionProvider getMethodReflectionProvider(final Object target, final Class<?> clazz,
			final Method method) {
		return new PureJavaMethodReflectionProvider(target, clazz, method);
	}

	public ParameterizedElementReflectionProvider getParameterizedElementProvider(final GenericTypeAccessor accessor) {
		return new PureJavaParameterizedElementReflectionProvider(accessor);
	}

	public GenericTypeAccessor getClassGenericTypeAccessor(final Class<?> clazz) {
		return new PureJavaClassGenericTypeAccessor(clazz);
	}

	public GenericTypeAccessor getFieldGenericTypeAccessor(final Field field) {
		return new PureJavaFieldGenericTypeAccessor(field);
	}

	public <T> ConstructorBypassingReflectionProvider<T> getConstructorBypassingReflectionProvider(final Class<T> clazz) {
		return new ObjenesisConstructorBypassingReflectionProvider<T>(clazz);
	}

	public ProxyReflectionProvider getProxyReflectionProvider(final Class<?> clazz, final List<Class<?>> interfaces,
			final MethodInterceptor... methodInterceptors) {
		return new CGLibProxyReflectionProvider(clazz, interfaces, methodInterceptors);
	}
}
