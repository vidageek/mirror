/**
 * 
 */
package net.vidageek.mirror.provider.compatibility;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.vidageek.mirror.fixtures.AnnotationFixture;
import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.InterfaceFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProviderDataPointList;
import net.vidageek.mirror.provider.experimental.sun15.Sun15ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import net.vidageek.mirror.reflect.MethodReflectorTest;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
public class MethodReflectionProviderCompatibilityTest implements ReflectionProviderDataPointList {

    private final ReflectionProvider provider = new DefaultMirrorReflectionProvider();

    @Theory
    public void testThatProviderDoesntChangeAccessibilityWhenReflecting(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("privateMethod",
                new Class<?>[] {});

        Assert.assertFalse(method.isAccessible());
    }

    @Theory
    public void testSetAccessible(final ReflectionProvider r) {
        Assume.assumeTrue(!r.getClass().equals(Sun15ReflectionProvider.class));
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("privateMethod",
                new Class<?>[] {});

        Assert.assertFalse(method.isAccessible());

        r.getMethodReflectionProvider(null, MethodFixture.class, method).setAccessible();

        Assert.assertTrue(method.isAccessible());
    }

    @Theory
    public void testGetParametersForMethodWithoutArgs(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithNoArgs",
                new Class<?>[] {});

        Class<?>[] types = r
            .getMethodReflectionProvider(new MethodFixture(), MethodFixture.class, method)
            .getParameters();

        Assert.assertEquals(0, types.length);
    }

    @Theory
    public void testGetParametersForMethodWithArgs(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithFourArgs",
                new Class<?>[] { String.class, Boolean.class, Class.class, Long.class });

        Class<?>[] types = r
            .getMethodReflectionProvider(new MethodFixture(), MethodFixture.class, method)
            .getParameters();

        Assert.assertEquals(4, types.length);
        Assert.assertEquals(String.class, types[0]);
        Assert.assertEquals(Boolean.class, types[1]);
        Assert.assertEquals(Class.class, types[2]);
        Assert.assertEquals(Long.class, types[3]);
    }

    @Theory
    public void testInvokeInstanceMethodWithOneArg(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithOneArg",
                new Class<?>[] { String.class });

        r.getMethodReflectionProvider(new MethodFixture(), MethodFixture.class, method).invoke(new Object[] { "" });

        Assert.assertEquals(new Integer(1), MethodFixture.getArgNum());
    }

    @Theory
    public void testInvokeInstanceMethodWithoutArgs(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithNoArgs",
                new Class<?>[] {});

        r.getMethodReflectionProvider(new MethodFixture(), MethodFixture.class, method).invoke(new Object[] {});

        Assert.assertEquals(new Integer(0), MethodFixture.getArgNum());
    }

    @Theory
    public void testInvokeStaticMethodWithArgs(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod(
                "staticMethodWithOneArg", new Class<?>[] { String.class });

        r.getMethodReflectionProvider(null, MethodFixture.class, method).invoke(new Object[] { "" });

        Assert.assertEquals(new Integer(300), MethodFixture.getArgNum());
    }

    @Theory
    public void testInvokeStaticMethodWithoutArgs(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("staticMethod",
                new Class<?>[] {});

        r.getMethodReflectionProvider(null, MethodFixture.class, method).invoke(new Object[] {});

        Assert.assertEquals(new Integer(200), MethodFixture.getArgNum());
    }

    @Theory
    public void testInvokeSuperClassMethod(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(ChildFixture.class).reflectMethod("superClassMethod",
                new Class<?>[] {});

        Object returnValue = r.getMethodReflectionProvider(new ChildFixture(), ChildFixture.class, method).invoke(
                new Object[] {});

        Assert.assertEquals(new Integer(0), returnValue);
    }

    @Theory
    public void testInvokeMethodWithPrimitivesArgs(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod(
                "methodWithTwoPrimitives", new Class<?>[] { int.class, boolean.class });

        r.getMethodReflectionProvider(new MethodFixture(), MethodFixture.class, method).invoke(
                new Object[] { 1, false });

        Assert.assertEquals(new Integer(2), MethodFixture.getArgNum());
    }

    @Theory
    public void testInvokePrivateMethod(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(MethodFixture.class).reflectMethod("privateMethod",
                new Class<?>[] {});

        r.getMethodReflectionProvider(new MethodFixture(), MethodFixture.class, method).invoke(new Object[] {});

        Assert.assertEquals(new Integer(100), MethodFixture.getArgNum());
    }

    @Theory
    public void testInvokePrivateSuperClassMethod(final ReflectionProvider r) {
        Method method = provider.getClassReflectionProvider(ChildFixture.class).reflectMethod(
                "superClassPrivateMethod", new Class<?>[] {});

        Object returnValue = r.getMethodReflectionProvider(new ChildFixture(), ChildFixture.class, method).invoke(
                new Object[] {});

        Assert.assertEquals(new Integer(1), returnValue);
    }

    @Theory
    public void testThatCanInvokeInterfaceMethodsOnProxys(final ReflectionProvider r) {
        Object proxy = Proxy.newProxyInstance(MethodReflectorTest.class.getClassLoader(),
                new Class<?>[] { InterfaceFixture.class }, new InvocationHandler() {

                    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                        // Won't do anything
                        return null;
                    }
                });

        Method method = provider.getClassReflectionProvider(proxy.getClass()).reflectMethod("interfaceMethod",
                new Class<?>[] { String.class });

        r.getMethodReflectionProvider(proxy, proxy.getClass(), method).invoke(new Object[] { "" });
    }

    @Theory
    public void testThatCanInvokeAnnotationMethods(final ReflectionProvider r) {
        AnnotationFixture annotation = r.getAnnotatedElementReflectionProvider(ClassFixture.class).getAnnotation(
                AnnotationFixture.class);

        Method method = provider.getClassReflectionProvider(AnnotationFixture.class).reflectMethod("value",
                new Class<?>[] {});

        Assert.assertNotNull(method);

        Assert.assertEquals("foo", r.getMethodReflectionProvider(annotation, AnnotationFixture.class, method).invoke(
                new Object[] {}));
    }

}
