package net.vidageek.mirror.invoke;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ConstructorExceptionFixture;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.ConstructorThatThrowsException;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jonasabreu
 * 
 */
public class ConstructorHandlerByArgsTest {

	private DefaultMirrorReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() {
		new ConstructorHandlerByArgs<ConstructorFixture>(provider, null);
	}

	@Test
	public void testThatThrowsInvocationTargetExceptionCause() {
		try {
			new Mirror().on(ConstructorExceptionFixture.class).invoke().constructor().withoutArgs();
		} catch (MirrorException e) {
			if (!RuntimeException.class.equals(e.getCause().getClass())) {
				fail("Exception cause should be RuntimeException.class. Was " + e.getCause());
			}
		}
	}

	/**
	 * Test for issue #79, described at
	 * http://bugs.vidageek.net/bug.php?op=show&bugid=79&pos=1
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsIEAWhenValueIsNullOnConstructorInvocation() {
		new Mirror().on(MethodFixture.class).invoke().constructor().withArgs((String) null);
	}

	@Test
	public void testThatInstantiatesWithoutUsingConstructor() {
		ConstructorThatThrowsException instance = new Mirror().on(ConstructorThatThrowsException.class).invoke()
				.constructor().bypasser();
		assertNotNull(instance);
	}
}
