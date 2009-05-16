package net.vidageek.mirror.provider;

import net.vidageek.mirror.exception.MirrorException;

/**
 * Interface that defines reflection operations related to elements with
 * generics declarations.
 * 
 * @author dnfeitosa
 */
public interface ParameterizedElementReflectionProvider {

	/**
	 * Returns the declared type at a given position. The {@code index} argument
	 * is the position where type has been declared, starting by 0. For example:
	 * 
	 * <code>
	 * HashMap<String, Object> field;
	 * </code>
	 * 
	 * String refers to position 0 and Object to position 1.
	 * 
	 * @see java.lang.Class#getGenericSuperclass()
	 * @see java.lang.reflect.Field#getGenericType()
	 * @see java.lang.reflect.ParameterizedType#getActualTypeArguments()
	 * 
	 * @param index
	 *            Position of declared type.
	 * @return The class of declaration.
	 * @throws MirrorException
	 *             If the element does not have generic type declaration.
	 * @throws MirrorException
	 *             If position is invalid.
	 */
	Class<?> getTypeAtPosition(int index);

}
