package net.vidageek.mirror.provider.test;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.compatibility.AnnotatedElementReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.compatibility.ClassReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.compatibility.ConstructorReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.compatibility.FieldReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.compatibility.MethodReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.compatibility.ObjenesisConstructorBypassingReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.compatibility.ParameterizedTypeReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.compatibility.PrimitiveClassReflectionProviderCompatibilityTest;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */

@RunWith(Theories.class)
final public class InvariantTest {

    @DataPoint
    public static Class<?> annotation = AnnotatedElementReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> clazz = ClassReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> contructor = ConstructorReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> field = FieldReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> method = MethodReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> contructorBypasser = ObjenesisConstructorBypassingReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> generics = ParameterizedTypeReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> primitive = PrimitiveClassReflectionProviderCompatibilityTest.class;

    @Theory
    public void testThatProviderTestsContainStaticFieldProvider(final Class<?> clazz) {
        ReflectionProvider provider = new DefaultMirrorReflectionProvider();
        new Mirror(provider).on(clazz).set().field("provider").withValue(provider);

    }

}
