/**
 * 
 */
package net.vidageek.mirror.matcher;

import java.util.HashMap;
import java.util.Map;

import net.vidageek.mirror.Preconditions;

/**
 * This class is responsible for analyzing if two class arrays match. This is
 * used to chose which constructor or method to be invoked.
 * 
 * @author jonasabreu
 */
public final class ClassArrayMatcher {

	private final Class<?>[] baseClasses;

	private static Map<Class<?>, Class<?>> primitiveToWrapper;

	static {
		primitiveToWrapper = new HashMap<Class<?>, Class<?>>();
		primitiveToWrapper.put(boolean.class, Boolean.class);
		primitiveToWrapper.put(byte.class, Byte.class);
		primitiveToWrapper.put(char.class, Character.class);
		primitiveToWrapper.put(short.class, Short.class);
		primitiveToWrapper.put(int.class, Integer.class);
		primitiveToWrapper.put(long.class, Long.class);
		primitiveToWrapper.put(float.class, Float.class);
		primitiveToWrapper.put(double.class, Double.class);
	}

	public ClassArrayMatcher(final Class<?>... baseClasses) {
		Preconditions.checkArgument(baseClasses != null, "baseClasses cannot be null");
		this.baseClasses = baseClasses;
	}

	public MatchType match(final Class<?>... classes) {
		Preconditions.checkArgument(classes != null, "classes cannot be null");

		if (baseClasses.length != classes.length) {
			return MatchType.DONT_MATCH;
		}

		if (isPerfectMatch(classes)) {
			return MatchType.PERFECT;
		}

		if (isMatch(classes)) {
			return MatchType.MATCH;
		}

		return MatchType.DONT_MATCH;

	}

	private boolean isMatch(final Class<?>[] classes) {
		boolean isMatch = true;
		for (int i = 0; i < baseClasses.length; i++) {
			if (!areClassesAssignable(classes[i], baseClasses[i])) {
				isMatch = false;
				break;
			}
		}
		return isMatch;
	}

	private boolean isPerfectMatch(final Class<?>[] classes) {
		boolean isPerfect = true;
		for (int i = 0; i < baseClasses.length; i++) {
			if (!areClassesEqual(baseClasses[i], classes[i])) {
				isPerfect = false;
				break;
			}
		}
		return isPerfect;
	}

	private boolean areClassesAssignable(final Class<?> c1, final Class<?> c2) {
		if (!(c1.isPrimitive() ^ c2.isPrimitive())) {
			return c1.isAssignableFrom(c2);
		}

		if (c1.isPrimitive()) {
			return primitiveToWrapper.get(c1).isAssignableFrom(c2);
		}
		return primitiveToWrapper.get(c2).isAssignableFrom(c1);
	}

	private boolean areClassesEqual(final Class<?> c1, final Class<?> c2) {
		if (!(c1.isPrimitive() ^ c2.isPrimitive())) {
			return c1.equals(c2);
		}
		if (c1.isPrimitive()) {
			return primitiveToWrapper.get(c1).equals(c2);
		}
		return primitiveToWrapper.get(c2).equals(c1);
	}
}
