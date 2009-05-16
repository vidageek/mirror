/**
 * 
 */
package net.vidageek.mirror.provider.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;

/**
 * @author jonasabreu
 * 
 */
public final class PureJavaConstructorReflectionProvider<T> implements ConstructorReflectionProvider<T> {

	private final Class<T> clazz;

	private final Constructor<T> constructor;

	public PureJavaConstructorReflectionProvider(final Class<T> clazz, final Constructor<T> constructor) {
		this.clazz = clazz;
		this.constructor = constructor;
	}

	public void setAccessible() {
		constructor.setAccessible(true);
	}

	public T instantiate(final Object... args) {
		try {
			setAccessible();
			T newInstance = constructor.newInstance(args);
			return newInstance;
		} catch (IllegalArgumentException e) {
			throw new ReflectionProviderException("could not invoke constructor " + constructor.toGenericString()
					+ " on class " + clazz.getName(), e);
		} catch (InstantiationException e) {
			throw new ReflectionProviderException("could not invoke constructor " + constructor.toGenericString()
					+ " on class " + clazz.getName(), e);
		} catch (IllegalAccessException e) {
			throw new ReflectionProviderException("could not invoke constructor " + constructor.toGenericString()
					+ " on class " + clazz.getName(), e);
		} catch (InvocationTargetException e) {
			throw new ReflectionProviderException("could not invoke constructor " + constructor.toGenericString()
					+ " on class " + clazz.getName(), e.getCause() == null ? e : e.getCause());
		}
	}

	public Class<?>[] getParameters() {
		return constructor.getParameterTypes();
	}
}
