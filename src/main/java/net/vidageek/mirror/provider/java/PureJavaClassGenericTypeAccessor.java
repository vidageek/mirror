package net.vidageek.mirror.provider.java;

import java.lang.reflect.Type;

import net.vidageek.mirror.provider.GenericTypeAccessor;

/**
 * Defines the strategy to access generic types on Class elements.
 * 
 * @author dnfeitosa
 */
public class PureJavaClassGenericTypeAccessor implements GenericTypeAccessor {

	private final Class<?> clazz;

	public PureJavaClassGenericTypeAccessor(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Type getGenericTypes() {
		return clazz.getGenericSuperclass();
	}

}
