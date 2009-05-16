/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.provider.ClassReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.AllAnnotationsHandler;
import net.vidageek.mirror.reflect.dsl.AllReflectionHandler;

/**
 * This class is responsible for reflecting lists of reflection elements
 * 
 * @author jonasabreu
 */
public final class DefaultAllReflectionHandler<T> implements AllReflectionHandler<T>{

	private final Class<T> clazz;

	private final ReflectionProvider provider;

	public DefaultAllReflectionHandler(final ReflectionProvider provider, final Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("clazz cannot be null");
		}
		this.provider = provider;
		this.clazz = clazz;
	}

	/**
	 * Use this method to reflect all fields on the wrapped class
	 * 
	 * @return The list of fields or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllFields()
	 */
	public List<Field> fields() {
		return provider.getClassReflectionProvider(clazz).reflectAllFields();
	}

	/**
	 * Use this method to reflect all methods on the wrapped class
	 * 
	 * @return The list of methods or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllMethods()
	 */
	public List<Method> methods() {
		return provider.getClassReflectionProvider(clazz).reflectAllMethods();
	}

	/**
	 * Use this method to reflect all constructors on the wrapped class
	 * 
	 * @return The list of constructors or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllConstructors()
	 */
	public List<Constructor<T>> constructors() {
		return provider.getClassReflectionProvider(clazz).reflectAllConstructors();
	}

	/**
	 * Use this method to reflect all annotations on a AccessibleObject
	 * 
	 * @return An object responsible for reflecting annotations.
	 */
	public AllAnnotationsHandler annotations() {
		return new DefaultAllAnnotationsHandler(provider, clazz);
	}

}
