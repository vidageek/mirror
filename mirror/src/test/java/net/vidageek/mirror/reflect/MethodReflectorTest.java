/**
 * 
 */
package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.InterfaceFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class MethodReflectorTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new PureJavaReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsNull() {
        new DefaultMethodReflector(provider, null, MethodFixture.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsEmpty() {
        new DefaultMethodReflector(provider, " \n  \t  ", MethodFixture.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionClassIsNull() {
        new DefaultMethodReflector(provider, "method", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionClassesAreNull() {
        new DefaultMethodReflector(provider, "method", MethodFixture.class).withArgs((Class<?>[]) null);
    }

    @Test
    public void testThatCanReflectMethod() throws Exception {
        Method m = new DefaultMethodReflector(provider, "methodWithOneArg", MethodFixture.class).withArgs(String.class);
        assertNotNull(m);
        m.invoke(new MethodFixture(), "Any string");
    }

    @Test
    public void testThatCanReflectMethodWithPrimitiveType() throws Exception {
        Method m = new DefaultMethodReflector(provider, "methodWithTwoPrimitives", MethodFixture.class).withArgs(int.class,
                boolean.class);
        assertNotNull(m);
        m.invoke(new MethodFixture(), 1, true);
    }

    @Test
    public void testThatCanReflectMethodWithWrapperType() throws Exception {
        Method m = new DefaultMethodReflector(provider, "methodWithTwoPrimitives", MethodFixture.class).withArgs(
                Integer.class, Boolean.class);
        assertNotNull(m);
        m.invoke(new MethodFixture(), 1, true);
    }

    @Test
    public void testThatCanReflectSuperClassMethod() throws Exception {
        Method method = new DefaultMethodReflector(provider, "superClassMethod", ChildFixture.class).withoutArgs();
        assertNotNull(method);
        method.invoke(new ChildFixture());
    }

    @Test
    public void testThatCanReflectInterfaceMethods() throws Exception {
        Method method = new DefaultMethodReflector(provider, "interfaceMethod", ChildFixture.class).withArgs(String.class);
        assertNotNull(method);
        method.invoke(new ChildFixture(), "");
    }

    @Test
    public void testThatCanReflectInterfaceMethodsOnInterfaces() {
        Method method = new DefaultMethodReflector(provider, "interfaceMethod", InterfaceFixture.class).withArgs(String.class);

        assertNotNull(method);
    }

    public static void main(final String[] args) {
        System.out.println(Arrays.asList(InterfaceFixture.class.getDeclaredMethods()));
    }

}
