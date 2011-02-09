package net.vidageek.mirror.proxy.dsl;

import net.vidageek.mirror.fixtures.MethodInterceptorFixture;

/**
 * @author jonasabreu
 * 
 */
public interface ProxyController<T> {

	T interceptingWith(MethodInterceptorFixture... methodInterceptorFixture);

}
