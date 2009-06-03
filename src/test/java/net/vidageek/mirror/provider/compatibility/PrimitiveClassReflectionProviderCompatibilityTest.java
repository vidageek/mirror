/**
 * 
 */
package net.vidageek.mirror.provider.compatibility;

import static org.junit.Assert.assertEquals;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProviderDataPointList;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PrimitiveClassReflectionProviderCompatibilityTest implements ReflectionProviderDataPointList {

    @DataPoint
    public static Class<?> booleanClass = boolean.class;
    @DataPoint
    public static Class<?> byteClass = byte.class;
    @DataPoint
    public static Class<?> shortClass = short.class;
    @DataPoint
    public static Class<?> charClass = char.class;
    @DataPoint
    public static Class<?> intClass = int.class;
    @DataPoint
    public static Class<?> longClass = long.class;
    @DataPoint
    public static Class<?> floatClass = float.class;
    @DataPoint
    public static Class<?> doubleClass = double.class;
    @DataPoint
    public static Class<?> voidClass = void.class;

    @Theory
    public void testReflectPrimitiveClass(final ReflectionProvider r, final Class<?> type) {
        final Class<?> reflectedClass = r.getClassReflectionProvider(type.getSimpleName()).reflectClass();
        assertEquals(type, reflectedClass);
    }
}