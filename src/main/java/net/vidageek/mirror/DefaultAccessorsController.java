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
 * 
 * @author jonasabreu
 * 
 */
public final class DefaultAccessorsController implements AccessorsController {

	private final Object target;

	private final ReflectionProvider provider;

	public DefaultAccessorsController(final ReflectionProvider provider, final Object target) {
		Preconditions.checkArgument(target != null, "target cannot be null");
		this.provider = provider;
		this.target = target;
	}

	public InvocationHandler<Object> invoke() {
		return new DefaultInvocationHandler<Object>(provider, target);
	}

	public SetterHandler set() {
		return new DefaultSetterHandler(provider, target);
	}

	public GetterHandler get() {
		return new DefaultGetterHandler(provider, target);
	}
}
