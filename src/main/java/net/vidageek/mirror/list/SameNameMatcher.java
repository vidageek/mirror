package net.vidageek.mirror.list;

import java.lang.reflect.Method;

import net.vidageek.mirror.list.dsl.Matcher;

/**
 * @author jonasabreu
 * 
 */
final public class SameNameMatcher implements Matcher<Method> {

	private final String methodName;

	public SameNameMatcher(final String methodName) {
		this.methodName = methodName;
	}

	public boolean accepts(final Method method) {
		return method.getName().equals(methodName);
	}

}
