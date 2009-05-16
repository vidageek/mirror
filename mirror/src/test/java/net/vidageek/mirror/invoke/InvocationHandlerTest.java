package net.vidageek.mirror.invoke;

import java.lang.reflect.Method;

import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jonasabreu
 * 
 */
@SuppressWarnings("unchecked")
public class InvocationHandlerTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new PureJavaReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfConstructedWithNullUsingObjectConstructor() {
        new DefaultInvocationHandler(provider, (Object) null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfConstructedwithNullUsingClassConstructor() {
        new DefaultInvocationHandler(provider, (Class<?>) null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfCallNullMethodName() {
        new DefaultInvocationHandler(provider, new Object()).method((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfCallNullMethod() {
        new DefaultInvocationHandler(provider, new Object()).method((Method) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfCallNullConstructor() {
        new DefaultInvocationHandler(provider, new Object()).constructor(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testThatThrowsExceptionIfTryToInvokeConstructorOnObject() {
        new DefaultInvocationHandler(provider, new Object()).constructor();
    }

}
