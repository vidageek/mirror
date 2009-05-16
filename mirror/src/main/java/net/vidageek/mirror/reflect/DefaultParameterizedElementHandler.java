package net.vidageek.mirror.reflect;

import net.vidageek.mirror.provider.GenericTypeAccessor;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.ParameterizedElementHandler;

/**
 * Class which defines access to Generics declarations.
 * 
 * @author dnfeitosa
 */
public class DefaultParameterizedElementHandler implements ParameterizedElementHandler {

	private final ReflectionProvider provider;
	private final GenericTypeAccessor accessor;

	public DefaultParameterizedElementHandler(ReflectionProvider provider,
			GenericTypeAccessor accessor) {
		if (accessor == null) {
			throw new IllegalArgumentException(
					"Argument accessor cannot be null");
		}
		this.provider = provider;
		this.accessor = accessor;
	}

	public Class<?> atPosition(int index) {
		return provider.getParameterizedElementProvider(accessor)
				.getTypeAtPosition(index);
	}
}
