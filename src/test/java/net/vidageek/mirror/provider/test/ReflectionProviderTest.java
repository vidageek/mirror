package net.vidageek.mirror.provider.test;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;
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

/**
 * @author jonasabreu
 * 
 */
public class ReflectionProviderTest implements Test {

    private final Class<?>[] testClasses = { AnnotatedElementReflectionProviderCompatibilityTest.class,
            ClassReflectionProviderCompatibilityTest.class, ConstructorReflectionProviderCompatibilityTest.class,
            FieldReflectionProviderCompatibilityTest.class, MethodReflectionProviderCompatibilityTest.class,
            ObjenesisConstructorBypassingReflectionProviderCompatibilityTest.class,
            ParameterizedTypeReflectionProviderCompatibilityTest.class,
            PrimitiveClassReflectionProviderCompatibilityTest.class };

    private final Test test;

    public ReflectionProviderTest(final ReflectionProvider provider) {
        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
        TestSuite testSuite = new TestSuite();
        for (Class<?> clazz : testClasses) {
            mirror.on(clazz).set().field("provider").withValue(provider);
            testSuite.addTest(new JUnit4TestAdapter(clazz));
        }
        test = testSuite;
    }

    public int countTestCases() {
        return test.countTestCases();
    }

    public void run(final TestResult result) {
        test.run(result);
    }

}
