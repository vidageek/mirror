package net.vidageek.mirror.provider.compatibility;

import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.experimental.sun15.Sun15ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.experimental.theories.DataPoint;

/**
 * @author jonasabreu
 * 
 */
public interface ReflectionProviderDatapoints {

	@DataPoint
	public static ReflectionProvider provider = new DefaultMirrorReflectionProvider();

	@DataPoint
	public static ReflectionProvider sun15 = new Sun15ReflectionProvider();

}
