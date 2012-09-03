package net.vidageek.mirror.dsl;

import net.vidageek.mirror.get.dsl.GetterHandler;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;
import net.vidageek.mirror.set.dsl.SetterHandler;

/**
 * 
 * @author donizetti
 * 
 */
public interface AccessorsController {

	/**
	 * This part of the DSL controls invocation of methods and constructors.
	 * 
	 * @return An object to control invocation.
	 */
	public InvocationHandler<Object> invoke();

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
	 * This part of the DSL verifies if the class is a collection.
	 * 
	 * @return true if reflected class is a collection.
	 */
	public boolean isCollection();

	/**
	 * This part of the DSL verifies if the class is a primitive.
	 * 
	 * @return true if reflected class is a primitive.
	 */
	public boolean isPrimitive();

}