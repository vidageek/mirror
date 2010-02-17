package net.vidageek.mirror.provider.java;

import static java.lang.String.format;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.GenericTypeAccessor;
import net.vidageek.mirror.provider.ParameterizedElementReflectionProvider;

/**
 * @author dnfeitosa
 */
public class PureJavaParameterizedElementReflectionProvider implements ParameterizedElementReflectionProvider {

	private final GenericTypeAccessor accessor;

	public PureJavaParameterizedElementReflectionProvider(GenericTypeAccessor accessor) {
		this.accessor = accessor;
	}

	public Class<?> getTypeAtPosition(int index) {
		ParameterizedType genericType;
		try {
			genericType = (ParameterizedType) accessor.getGenericTypes();
		} catch (ClassCastException e) {
			throw new MirrorException("Element is not parameterized with a generic type.", e);
		}
		Type[] typeArguments = genericType.getActualTypeArguments();
		try {
			return (Class<?>) typeArguments[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new MirrorException(format("No type declared at position %d.", index));
		}
	}
}
