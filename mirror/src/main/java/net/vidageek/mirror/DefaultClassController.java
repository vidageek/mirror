/**
 * 
 */
package net.vidageek.mirror;

import net.vidageek.mirror.dsl.ClassController;
import net.vidageek.mirror.get.DefaultGetterHandler;
import net.vidageek.mirror.get.dsl.GetterHandler;
import net.vidageek.mirror.invoke.DefaultInvocationHandler;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.DefaultAllReflectionHandler;
import net.vidageek.mirror.reflect.DefaultReflectionHandler;
import net.vidageek.mirror.reflect.dsl.AllReflectionHandler;
import net.vidageek.mirror.reflect.dsl.ReflectionHandler;
import net.vidageek.mirror.set.DefaultSetterHandler;
import net.vidageek.mirror.set.dsl.SetterHandler;

/**
 * Object to provide reflection for a Class object
 * 
 * @author jonasabreu
 */
public final class DefaultClassController<T> implements ClassController<T>{

	private final Class<T> clazz;

	private final ReflectionProvider provider;

	public DefaultClassController(final ReflectionProvider provider, final Class<T> clazz) {
		this.provider = provider;
		if (clazz == null) {
			throw new IllegalArgumentException("clazz cannot be null");
		}
		this.clazz = clazz;
	}

	/**
	 * This part of the DSL controls invocation of methods and constructors.
	 * 
	 * @return An object to control invocation.
	 */
	public InvocationHandler<T> invoke() {
		return new DefaultInvocationHandler<T>(provider, clazz);
	}

	/**
	 * This part of the DSL controls setting of fields.
	 * 
	 * @return An object to control setting.
	 */
	public SetterHandler set() {
		return new DefaultSetterHandler(provider, clazz);
	}

	/**
	 * This part of the DSL controls getting field values.
	 * 
	 * @return An object to control getting of values.
	 */
	public GetterHandler get() {
		return new DefaultGetterHandler(provider, clazz);
	}

	/**
	 * This part of the DSL controls reflection of single reflection elements.
	 * 
	 * @return An object to control reflection of single reflection elements.
	 */
	public ReflectionHandler<T> reflect() {
		return new DefaultReflectionHandler<T>(provider, clazz);
	}

	/**
	 * This part of the DSL controls reflection of lists of reflection elements.
	 * 
	 * @return An object to control reflection of lists of reflection elements.
	 */
	public AllReflectionHandler<T> reflectAll() {
		return new DefaultAllReflectionHandler<T>(provider, clazz);
	}

}
