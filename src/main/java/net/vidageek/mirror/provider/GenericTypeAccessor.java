package net.vidageek.mirror.provider;

import java.lang.reflect.Type;

/**
 * Interface to be used by classes which defines strategies to access elements
 * with generic types.
 * 
 * @author dnfeitosa
 */
public interface GenericTypeAccessor {

	Type getGenericTypes();

}
