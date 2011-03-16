package net.vidageek.mirror.integration;

import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.proxy.dsl.MethodInterceptor;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test depends on the mirror jar. If possible, use the script test.sh to
 * run this test. If you need to run only this test, run maven -Dmaven.test.skip
 * package on mirror before running this.
 * 
 * @author jonasabreu
 * 
 */
final public class PackagedMirrorTest {

	@Test
	public void testThatProxyfyWorksAfterPackaging() {
		Number number = new Mirror().proxify(Number.class).interceptingWith(new MethodInterceptor() {

			public boolean accepts(final Method method) {
				return true;
			}

			public Object intercepts(final Object target, final Method method, final Object... parameters) {
				return 12;
			}
		});

		Assert.assertEquals(12, number.intValue());
	}
}
