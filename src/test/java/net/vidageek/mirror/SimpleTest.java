package net.vidageek.mirror;

import java.lang.reflect.Method;

import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

public class SimpleTest {

	public class SomaMethodInterceptor implements MethodInterceptor {

		public boolean accepts(Method method) {
			return true;
		}

		public Object intercepts(Object target, Method method,
				Object... parameters) {
			return (Integer) parameters[0] + (Integer) parameters[1];
		}
	}
}
