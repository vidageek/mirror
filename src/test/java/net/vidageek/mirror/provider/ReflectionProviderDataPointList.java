package net.vidageek.mirror.provider;

import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

/**
 * Interface to provide datapoints to JUnit's Theory.
 * 
 * @author jonasabreu
 */
public interface ReflectionProviderDataPointList {

	public ReflectionProvider pureJava = new PureJavaReflectionProvider();

}
