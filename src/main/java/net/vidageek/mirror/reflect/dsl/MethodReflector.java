package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Method;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ClassReflectionProvider;

public interface MethodReflector {
	/**
	 * This is a convenience method for
	 * {@link MethodReflector#withArgs(Class...)}
	 */
	public Method withoutArgs();

	/**
	 * Use this method to reflect a method with classes as its argument types.
	 * 
	 * @param classes
	 *            argument types of the method to be reflected.
	 * @return A method matching classes as argument types.
	 * @see ClassReflectionProvider#reflectMethod(String, Class[])
	 * @throws IllegalArgumentException
	 *             if classes is null
	 */
	public Method withArgs(final Class<?>... classes);

	/**
	 * Use this method to reflect a uniquely named method.
	 * 
	 * @return A method uniquely named or null if no method was found.
	 * @throws MirrorException
	 *             if more than one method with the same name was found.
	 */
	public Method withAnyArgs();
	
	/**
	 * Use this method to get a Class of current index parameter
	 * @param index 
	 * 		a index in array of parameters
	 * @return Class Type from param at index
	 * @throws MirrorException
	 * 				if index is out range
	 */
	public Class<?> getParameterClass(int index);}
