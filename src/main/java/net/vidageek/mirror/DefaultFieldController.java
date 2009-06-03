package net.vidageek.mirror;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.FieldController;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.DefaultAllMemberHandler;
import net.vidageek.mirror.reflect.DefaultFieldHandler;
import net.vidageek.mirror.reflect.dsl.AllMemberHandler;
import net.vidageek.mirror.reflect.dsl.FieldHandler;

/**
 * Class which provides reflection features to Fields.
 * 
 * @author dnfeitosa
 */
public class DefaultFieldController implements FieldController {

	private final ReflectionProvider provider;
	private final Field field;

	public DefaultFieldController(ReflectionProvider provider, Field field) {
		if (field == null) {
			throw new IllegalArgumentException("Argument field cannot be null.");
		}
		this.provider = provider;
		this.field = field;
	}

	public FieldHandler reflect() {
		return new DefaultFieldHandler(provider, field);
	}

	public AllMemberHandler reflectAll() {
		return new DefaultAllMemberHandler(provider, field);
	}

}
