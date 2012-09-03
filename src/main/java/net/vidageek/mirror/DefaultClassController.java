/**
 * 
 */
package net.vidageek.mirror;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

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
public final class DefaultClassController<T> implements ClassController<T> {

	private final Class<T>				clazz;

	private final ReflectionProvider	provider;

	public DefaultClassController(final ReflectionProvider provider, final Class<T> clazz) {
		this.provider = provider;
		if (clazz == null) {
			throw new IllegalArgumentException("clazz cannot be null");
		}
		this.clazz = clazz;
	}

	public InvocationHandler<T> invoke() {
		return new DefaultInvocationHandler<T>(provider, clazz);
	}

	public SetterHandler set() {
		return new DefaultSetterHandler(provider, clazz);
	}

	public GetterHandler get() {
		return new DefaultGetterHandler(provider, clazz);
	}

	public ReflectionHandler<T> reflect() {
		return new DefaultReflectionHandler<T>(provider, clazz);
	}

	public AllReflectionHandler<T> reflectAll() {
		return new DefaultAllReflectionHandler<T>(provider, clazz);
	}

	public boolean isCollection() {
		Type type = clazz;
		if (type instanceof ParameterizedType) {
			ParameterizedType ptype = (ParameterizedType) type;
			return Collection.class.isAssignableFrom((Class<?>) ptype.getRawType())
					|| Map.class.isAssignableFrom((Class<?>) ptype.getRawType());
		}

		return Collection.class.isAssignableFrom((Class<?>) type);
	}

}
