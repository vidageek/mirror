package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Field;

import net.vidageek.mirror.reflect.DefaultConstructorReflector;
import net.vidageek.mirror.reflect.DefaultFieldReflector;
import net.vidageek.mirror.reflect.DefaultMethodReflector;

/**
 * This part of the DSL is used to choose reflection element is going to be
 * reflected.
 * 
 * @author donizetti
 */
public interface ReflectionHandler<T> {
	/**
	 * Use this method to reflect a field by its name.
	 * 
	 * @param fieldName
	 *            Name of the field to be reflected.
	 * @return A field identified by fieldName or null if none is found.
	 * @throws IllegalArgumentException
	 *             if fieldName is null or empty
	 * @see DefaultFieldReflector#onClass(Class)
	 */
	public Field field(final String fieldName);

	/**
	 * Use this method to choose the name of the method to reflect.
	 * 
	 * @param methodName
	 *            name of the method to be reflected
	 * @return An object responsible for reflecting the method.
	 * @throws IllegalArgumentException
	 *             if methodName is null or empty
	 * @see DefaultMethodReflector
	 */
	public MethodReflector method(final String methodName) ;

	/**
	 * Use this method to reflect a constructor
	 * 
	 * @return An object responsible for reflecting constructors.
	 * @see DefaultConstructorReflector
	 */
	public ConstructorReflector<T> constructor(); 

	/**
	 * Use this method to reflect annotations.
	 * 
	 * @param annotation
	 *            Type of the annotation to be reflected.
	 * @return An object responsible for reflecting annotations.
	 * @see AnnotationHandler
	 */
	public <A> AnnotationHandler<? extends A> annotation(final Class<A> annotation) ;


	public ParameterizedElementHandler parentGenericType() ;
	

}
