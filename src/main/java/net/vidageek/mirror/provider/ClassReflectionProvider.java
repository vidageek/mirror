package net.vidageek.mirror.provider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 * Interface that defines reflection operations related to class
 * 
 * @author jonasabreu
 */
public interface ClassReflectionProvider<T> {

	/**
	 * Method used to gain access to the class wrapped.
	 * 
	 * @return The class wrapped by this {@link ClassReflectionProvider}
	 * @throws ReflectionProviderException
	 */
	Class<T> reflectClass();

	/**
	 * This method is used to reflect a single field named fieldName
	 * 
	 * @param fieldName
	 *            Name of the field to be reflected
	 * @return The field represented by fieldName or null if not found.
	 * @throws ReflectionProviderException
	 */
	Field reflectField(String fieldName);

	/**
	 * This method is used to reflect a single method named methodName and with
	 * args argumentTypes
	 * 
	 * @param methodName
	 *            name of the method to be reflected.
	 * @param argumentTypes
	 *            argument types of the method to be reflected.
	 * @return The method that has methodName as its name and matches
	 *         argumentTypes. Will return null if method is not found.
	 * @throws ReflectionProviderException
	 */
	Method reflectMethod(String methodName, Class<?>[] argumentTypes);

	/**
	 * Method used to reflect a single constructor matching argumentTypes.
	 * 
	 * @param argumentTypes
	 *            argument types of the constructor to be reflected.
	 * @return The constructor that matches argumentTypes or null if none is
	 *         found
	 * @throws ReflectionProviderException
	 */
	Constructor<T> reflectConstructor(Class<?>[] argumentTypes);

	/**
	 * Method used to reflect all fields on the wrapped class.
	 * 
	 * @return A list containing all field found on the class and it's
	 *         hierarchy.
	 * @throws ReflectionProviderException
	 */
	List<Field> reflectAllFields();

	/**
	 * Method used to reflect all methods on the wrapped class.
	 * 
	 * @return A list containing all methods found on the class and it's
	 *         hierarchy.
	 * @throws ReflectionProviderException
	 */
	List<Method> reflectAllMethods();

	/**
	 * Method used to reflect all constructor on the wrapped class.
	 * 
	 * @return A list containing all field found on the class.
	 * @throws ReflectionProviderException
	 */
	List<Constructor<T>> reflectAllConstructors();

}
