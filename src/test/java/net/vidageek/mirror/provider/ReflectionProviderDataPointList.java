package net.vidageek.mirror.provider;

import net.vidageek.mirror.provider.experimental.sun15.Sun15ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.experimental.theories.DataPoint;

/**
 * Interface to provide datapoints to JUnit's Theory.
 * 
 * @author jonasabreu
 */
public interface ReflectionProviderDataPointList {

    @DataPoint
    public ReflectionProvider defaultProvider = new DefaultMirrorReflectionProvider();

    @DataPoint
    public ReflectionProvider sun15Provider = new Sun15ReflectionProvider();

}
