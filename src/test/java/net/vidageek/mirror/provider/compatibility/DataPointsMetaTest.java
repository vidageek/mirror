/**
 * 
 */
package net.vidageek.mirror.provider.compatibility;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import net.vidageek.mirror.provider.ReflectionProviderDataPointList;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
public class DataPointsMetaTest {

    @DataPoint
    public static Class<?> annotationTest = AnnotatedElementReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> classTest = ClassReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> constructorTest = ConstructorReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> fieldTest = FieldReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> methodTest = MethodReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> parameterizedTypeTest = ParameterizedTypeReflectionProviderCompatibilityTest.class;
    @DataPoint
    public static Class<?> primitiveClassReflectionTest = PrimitiveClassReflectionProviderCompatibilityTest.class;

    /*
     * This is a test to ensure that all compatibility tests implements
     * ReflectionProviderDataPointList
     */
    @Theory
    public void testThatImplementsReflectionProviderDataPointList(final Class<?> testClass) {
        assertTrue(Arrays.asList(testClass.getInterfaces()).contains(ReflectionProviderDataPointList.class));
    }

}
