package net.vidageek.mirror.provider.java;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.provider.GenericTypeAccessor;

/**
 * Defines the strategy to access generic types on Field elements.
 * 
 * @author dnfeitosa
 */
public class PureJavaFieldGenericTypeAccessor implements GenericTypeAccessor {

	private final Field field;

	public PureJavaFieldGenericTypeAccessor(Field field) {
		Preconditions.checkArgument(field != null, "field cannot be null");
		this.field = field;
	}

	public Type getGenericTypes() {
		return field.getGenericType();
	}

}
