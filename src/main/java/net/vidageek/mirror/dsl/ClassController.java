package net.vidageek.mirror.dsl;

import net.vidageek.mirror.get.dsl.GetterHandler;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;
import net.vidageek.mirror.reflect.dsl.AllReflectionHandler;
import net.vidageek.mirror.reflect.dsl.ReflectionHandler;
import net.vidageek.mirror.set.dsl.SetterHandler;

/**
 * 
 * 
 * @author donizetti
 */
public interface ClassController<T> {
	/**
	 * This part of the DSL controls invocation of methods and constructors.
	 * 
	 * @return An object to control invocation.
	 */
	public InvocationHandler<T> invoke();

	/**
	 * This part of the DSL controls setting of fields.
	 * 
	 * @return An object to control setting.
	 */
	public SetterHandler set();

	/**
	 * This part of the DSL controls getting field values.
	 * 
	 * @return An object to control getting of values.
	 */
	public GetterHandler get();

	/**
	 * This part of the DSL controls reflection of single reflection elements.
	 * 
	 * @return An object to control reflection of single reflection elements.
	 */
	public ReflectionHandler<T> reflect();

	/**
	 * This part of the DSL controls reflection of lists of reflection elements.
	 * 
	 * @return An object to control reflection of lists of reflection elements.
	 */
	public AllReflectionHandler<T> reflectAll();

}
