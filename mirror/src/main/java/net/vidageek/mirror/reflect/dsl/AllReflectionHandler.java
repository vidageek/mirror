package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.provider.ClassReflectionProvider;

public interface AllReflectionHandler<T> {
	/**
	 * Use this method to reflect all fields on the wrapped class
	 * 
	 * @return The list of fields or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllFields()
	 */
	public List<Field> fields() ;

	/**
	 * Use this method to reflect all methods on the wrapped class
	 * 
	 * @return The list of methods or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllMethods()
	 */
	public List<Method> methods() ;

	/**
	 * Use this method to reflect all constructors on the wrapped class
	 * 
	 * @return The list of constructors or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllConstructors()
	 */
	public List<Constructor<T>> constructors() ;

	/**
	 * Use this method to reflect all annotations on a AccessibleObject
	 * 
	 * @return An object responsible for reflecting annotations.
	 */
	public AllAnnotationsHandler annotations() ;

	
	/**
	 * 
	 * Use this method to reflect all getters that follow JavaBean convention
	 * 
	 * @return A list of getter methods
	 */
	public List<Method> setters();

}
