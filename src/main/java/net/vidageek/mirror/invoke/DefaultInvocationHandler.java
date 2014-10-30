package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.bean.Bean;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.invoke.dsl.ConstructorHandler;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;
import net.vidageek.mirror.invoke.dsl.MethodHandler;
import net.vidageek.mirror.invoke.dsl.SetterMethodHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * This class is responsible for choosing between invocation of constructor or
 * methods.
 * 
 * @author jonasabreu
 */
public final class DefaultInvocationHandler<T> implements InvocationHandler<T> {

	private final Object target;

	private final Class<?> clazz;

	private final ReflectionProvider provider;

	public DefaultInvocationHandler(final ReflectionProvider provider, final Object target) {
		Preconditions.checkArgument(target != null, "target cannot be null");
		this.provider = provider;
		this.target = target;
		clazz = target.getClass();
	}

	public DefaultInvocationHandler(final ReflectionProvider provider, final Class<T> target) {
		Preconditions.checkArgument(target != null, "target cannot be null");
		this.provider = provider;
		clazz = target;
		this.target = null;
	}

	public MethodHandler method(final String methodName) {
		Preconditions.checkArgument(methodName != null, "methodName cannot be null");
		return new MethodHandlerByName(provider, target, clazz, methodName);
	}

	@SuppressWarnings("unchecked")
	public ConstructorHandler<T> constructor() {
		if (target != null) {
			throw new MirrorException("You must use constructor InvocationHandler(Class<T>) instead of InvocationHandler(Object).");
		}
		return new ConstructorHandlerByArgs<T>(provider, (Class<T>) clazz);
	}

	public MethodHandler method(final Method method) {
		return new MethodHandlerByMethod(provider, target, clazz, method);
	}

	@SuppressWarnings("unchecked")
	public <C> ConstructorHandler<C> constructor(final Constructor<C> con) {
		return new ConstructorHandlerByConstructor(provider, clazz, con);
	}

	public Object getterFor(final String fieldName) {
		Method method = null;
		for (String string : new Bean().getter(fieldName)) {
			method = new Mirror(provider).on(target.getClass()).reflect().method(string).withoutArgs();
			if (method != null)
				break;
		}
		if (method == null) {
			throw new MirrorException("Could not find getter for field " + fieldName);
		}
		return new Mirror(provider).on(target).invoke().method(method).withoutArgs();
	}

	public Object getterFor(final Field field) {
		return getterFor(field.getName());
	}

	public SetterMethodHandler setterFor(final String fieldName) {
		return new DefaultSetterMethodHandler(provider, target, fieldName);
	}

	public SetterMethodHandler setterFor(final Field field) {
		return setterFor(field.getName());
	}
}
