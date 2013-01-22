package net.vidageek.mirror.provider.proxy;

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
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;
import net.vidageek.mirror.proxy.javassist.JavassistProxyReflectionProvider;

public class JavassistReflectionProvider implements ReflectionProvider {
	
	private ReflectionProvider provider = new DefaultMirrorReflectionProvider();

	public ClassReflectionProvider<? extends Object> getClassReflectionProvider(
			String className) {
		return provider.getClassReflectionProvider(className);
	}

	public <T> ClassReflectionProvider<T> getClassReflectionProvider(
			Class<T> clazz) {
		return provider.getClassReflectionProvider(clazz);
	}

	public <T> ConstructorReflectionProvider<T> getConstructorReflectionProvider(
			Class<T> clazz, Constructor<T> constructor) {
		return provider.getConstructorReflectionProvider(clazz, constructor);
	}

	public <T> ConstructorBypassingReflectionProvider<T> getConstructorBypassingReflectionProvider(
			Class<T> clazz) {
		return provider.getConstructorBypassingReflectionProvider(clazz);
	}

	public MethodReflectionProvider getMethodReflectionProvider(Object target,
			Class<?> clazz, Method method) {
		return provider.getMethodReflectionProvider(target, clazz, method);
	}

	public FieldReflectionProvider getFieldReflectionProvider(Object target,
			Class<?> clazz, Field field) {
		return provider.getFieldReflectionProvider(target, clazz, field);
	}

	public AnnotatedElementReflectionProvider getAnnotatedElementReflectionProvider(
			AnnotatedElement element) {
		return provider.getAnnotatedElementReflectionProvider(element);
	}

	public ParameterizedElementReflectionProvider getParameterizedElementProvider(
			GenericTypeAccessor accessor) {
		return provider.getParameterizedElementProvider(accessor);
	}

	public GenericTypeAccessor getClassGenericTypeAccessor(Class<?> clazz) {
		return provider.getClassGenericTypeAccessor(clazz);
	}

	public GenericTypeAccessor getFieldGenericTypeAccessor(Field field) {
		return provider.getFieldGenericTypeAccessor(field);
	}

	public ProxyReflectionProvider getProxyReflectionProvider(
			Class<?> clazz, List<Class<?>> interfaces, MethodInterceptor... methodInterceptors) {
		return new JavassistProxyReflectionProvider(clazz, interfaces, methodInterceptors);
	}
}
