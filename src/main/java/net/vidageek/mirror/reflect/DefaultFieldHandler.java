package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaFieldGenericTypeAccessor;
import net.vidageek.mirror.reflect.dsl.FieldHandler;
import net.vidageek.mirror.reflect.dsl.MemberHandler;
import net.vidageek.mirror.reflect.dsl.ParameterizedElementHandler;

/**
 * 
 * @author dnfeitosa
 * 
 */
public class DefaultFieldHandler implements FieldHandler {

	private final ReflectionProvider provider;
	private final Field field;
	private MemberHandler memberHandler;

	public DefaultFieldHandler(ReflectionProvider provider, Field field) {
		Preconditions.checkArgument(field != null, "field cannot be null");

		this.provider = provider;
		this.field = field;
		this.memberHandler = new DefaultMemberHandler(provider, field);
	}

	public <T extends Annotation> T annotation(Class<T> annotation) {
		return this.memberHandler.annotation(annotation);
	}

	public ParameterizedElementHandler genericType() {
		PureJavaFieldGenericTypeAccessor accessor = new PureJavaFieldGenericTypeAccessor(field);
		return new DefaultParameterizedElementHandler(provider, accessor);
	}

}
