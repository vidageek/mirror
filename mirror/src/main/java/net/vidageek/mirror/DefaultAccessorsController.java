/**
 * 
 */
package net.vidageek.mirror;

import net.vidageek.mirror.dsl.AccessorsController;
import net.vidageek.mirror.get.DefaultGetterHandler;
import net.vidageek.mirror.get.dsl.GetterHandler;
import net.vidageek.mirror.invoke.DefaultInvocationHandler;
import net.vidageek.mirror.invoke.dsl.InvocationHandler;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.set.DefaultSetterHandler;
import net.vidageek.mirror.set.dsl.SetterHandler;

/**
 * Class to provide reflection features for objects.
 * 
 * @author jonasabreu
 * 
 */
public final class DefaultAccessorsController implements AccessorsController {

	private final Object target;

	private final ReflectionProvider provider;

	public DefaultAccessorsController(final ReflectionProvider provider, final Object target) {
		if (target == null) {
			throw new IllegalArgumentException("target cannot be null");
		}
		this.provider = provider;
		this.target = target;
	}

	/**
	 * This part of the DSL controls invocation of methods and constructors.
	 * 
	 * @return An object to control invocation.
	 */
	public InvocationHandler<Object> invoke() {
		return new DefaultInvocationHandler<Object>(provider, target);
	}

	/**
	 * This part of the DSL controls setting of fields.
	 * 
	 * @return An object to control setting.
	 */
	public SetterHandler set() {
		return new DefaultSetterHandler(provider, target);
	}

	/**
	 * This part of the DSL controls getting field values.
	 * 
	 * @return An object to control getting of values.
	 */
	public GetterHandler get() {
		return new DefaultGetterHandler(provider, target);
	}
}
