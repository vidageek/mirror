package net.vidageek.mirror.proxy.dsl;

/**
 * @author jonasabreu
 * 
 */
public interface ProxyHandler<T> {

	T interceptingWith(MethodInterceptor... methodInterceptor);

}
