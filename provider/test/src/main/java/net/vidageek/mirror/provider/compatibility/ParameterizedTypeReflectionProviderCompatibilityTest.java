package net.vidageek.mirror.provider.compatibility;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.SubClassOfTypedClassFixture;
import net.vidageek.mirror.provider.GenericTypeAccessor;
import net.vidageek.mirror.provider.ReflectionProvider;

import org.junit.Before;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * 
 * @author dnfeitosa
 * 
 */
@RunWith(Theories.class)
public class ParameterizedTypeReflectionProviderCompatibilityTest {

    private Field typedField;
    private Field notTypedField;
    private GenericTypeAccessor accessor;

    @Before
    public void setup() {
        typedField = new Mirror().on(FieldFixture.class).reflect().field("typedField");
        notTypedField = new Mirror().on(FieldFixture.class).reflect().field("field");
    }

    @Theory
    public void testThatRetrievesTypeFromField(final ReflectionProvider provider) {

        accessor = provider.getFieldGenericTypeAccessor(typedField);
        Class<?> type = provider.getParameterizedElementProvider(accessor).getTypeAtPosition(0);

        assertEquals(String.class, type);
    }

    @Theory
    public void testThatThrowsMirrorExceptionIfFieldIsNotTyped(final ReflectionProvider provider) {

        accessor = provider.getFieldGenericTypeAccessor(notTypedField);
        try {
            provider.getParameterizedElementProvider(accessor).getTypeAtPosition(0);
            fail();
        } catch (Exception e) {
            expectMirrorException(e);
        }
    }

    @Theory
    public void testThatThrowsMirrorExceptionIfDoesNotHaveDeclaredTypeAtGivenPosition(final ReflectionProvider provider) {

        accessor = provider.getFieldGenericTypeAccessor(typedField);
        try {
            provider.getParameterizedElementProvider(accessor).getTypeAtPosition(100);
            fail();
        } catch (Exception e) {
            expectMirrorException(e);
        }
    }

    @Theory
    public void testThatRetrievesTypeFromClass(final ReflectionProvider provider) {

        accessor = provider.getClassGenericTypeAccessor(SubClassOfTypedClassFixture.class);

        Class<?> type = provider.getParameterizedElementProvider(accessor).getTypeAtPosition(0);

        assertEquals(String.class, type);
    }

    @Theory
    public void testThatThrowsMirrorExceptionIfClassIsNotTyped(final ReflectionProvider provider) {

        accessor = provider.getClassGenericTypeAccessor(String.class);

        try {
            provider.getParameterizedElementProvider(accessor).getTypeAtPosition(0);
            fail();
        } catch (Exception e) {
            expectMirrorException(e);
        }
    }

    private void expectMirrorException(final Exception e) {
        assertEquals(MirrorException.class, e.getClass());
    }
}
