package net.vidageek.mirror.invoke;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.MethodExceptionFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jonasabreu
 * 
 */
public class MethodHandlerByNameTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new PureJavaReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsNull() {
        new MethodHandlerByName(provider, null, MethodHandlerByNameTest.class, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsBlank() {
        new MethodHandlerByName(provider, null, MethodHandlerByNameTest.class, "  \n  \t  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new MethodHandlerByName(provider, null, null, "method");
    }

    @Test
    public void testThatDoesntThrowsExceptionIfObjectIsNull() {
        new MethodHandlerByName(provider, null, MethodHandlerByNameTest.class, "method");
    }

    @Test(expected = IllegalStateException.class)
    public void testThrowsExceptionIfCallInstanceMethodOnClass() {
        new Mirror().on(MethodFixture.class).invoke().method("methodWithNoArgs").withoutArgs();
    }

    @Test
    public void testThatThrowsInvocationTargetExceptionCause() {
        try {
            new Mirror().on(MethodExceptionFixture.class).invoke().method("method").withoutArgs();
        } catch (MirrorException e) {
            if (!RuntimeException.class.equals(e.getCause().getClass())) {
                Assert.fail("Exception cause should be RuntimeException.class. Was " + e.getCause());
            }
        }
    }

    /**
     * Test for issue #79, described at
     * http://bugs.vidageek.net/bug.php?op=show&bugid=79&pos=1
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsIEAWhenValueIsNullOnMethodInvocation() {
        new Mirror().on(new MethodFixture()).invoke().method("methodWithOneArg").withArgs((String) null);
    }
}
