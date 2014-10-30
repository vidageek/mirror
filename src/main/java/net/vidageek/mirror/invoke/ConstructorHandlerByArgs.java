package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.invoke.dsl.ConstructorHandler;
import net.vidageek.mirror.provider.ConstructorBypassingReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * This class is responsible for invoking a constructor using only the arguments
 * supplied.
 * 
 * @author jonasabreu
 */
public final class ConstructorHandlerByArgs<T> implements ConstructorHandler<T> {

	private final Class<T> clazz;

	private final ReflectionProvider provider;

	public ConstructorHandlerByArgs(final ReflectionProvider provider, final Class<T> clazz) {
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");
		this.provider = provider;
		this.clazz = clazz;
	}

	public T withoutArgs() {
		return this.withArgs(new Object[0]);
	}

	public T withArgs(final Object... args) {
		return new ConstructorHandlerByConstructor<T>(provider, clazz, getConstructor(args)).withArgs(args);
	}

	public T bypasser() {
		ConstructorBypassingReflectionProvider<T> bypassingProvider = provider
				.getConstructorBypassingReflectionProvider(clazz);
		return bypassingProvider.bypassConstructor();
	}

	private Constructor<T> getConstructor(final Object... args) {
		int length = args == null ? 0 : args.length;

		Class<?>[] classes = new Class<?>[length];
		for (int i = 0; i < length; i++) {
			Preconditions.checkArgument(args[i] != null, "Cannot invoke a constructor by args if one of it's arguments is null. First reflect the constructor.");
			classes[i] = args[i].getClass();
		}

		Constructor<T> con = new Mirror(provider).on(clazz).reflect().constructor().withArgs(classes);
		if (con == null) {
			throw new MirrorException("Could not find constructor with args " + Arrays.asList(classes) + " on class "
					+ this.clazz.getName());
		}

		return con;
	}

}
