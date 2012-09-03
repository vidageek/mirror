package net.vidageek.mirror.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import net.vidageek.mirror.reflect.dsl.TypeHandler;

public class DefaultTypeHandler implements TypeHandler {

	private Class<?>	clazz;

	public DefaultTypeHandler(Class<?> clazz) {
		this.clazz = clazz;
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

	public boolean isPrimitive() {
		return clazz.isPrimitive()
				|| clazz.isEnum()
				|| Number.class.isAssignableFrom(clazz)
				|| clazz.equals(String.class)
				|| Date.class.isAssignableFrom(clazz)
				|| Calendar.class.isAssignableFrom(clazz)
				|| Boolean.class.equals(clazz)
				|| Character.class.equals(clazz);
	}
}