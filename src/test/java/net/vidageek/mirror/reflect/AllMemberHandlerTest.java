package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

public class AllMemberHandlerTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new DefaultMirrorReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMemberIsNull() {
        new DefaultAllMemberHandler(provider, null);
    }

    @Test
    public void testThatReturnAllAnnotationsFromConstructor() {
        final Constructor<ConstructorFixture> constructor = new Mirror()
            .on(ConstructorFixture.class)
            .reflect()
            .constructor()
            .withoutArgs();
        assertNotNull(constructor);

        final List<Annotation> annontations = new DefaultAllMemberHandler(provider, constructor).annotations();
        assertEquals(2, annontations.size());
    }

    @Test
    public void testThatReturnAllAnnotationsFromMethod() {
        final Method method = new Mirror().on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();
        assertNotNull(method);

        final List<Annotation> annontations = new DefaultAllMemberHandler(provider, method).annotations();
        assertEquals(2, annontations.size());
    }

    @Test
    public void testThatReturnAllAnnotationsFromField() {
        final Field field = new Mirror().on(FieldFixture.class).reflect().field("field");
        assertNotNull(field);

        final List<Annotation> annontations = new DefaultAllMemberHandler(provider, field).annotations();
        assertEquals(2, annontations.size());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testThatPresentsAllAnnotationsToMatcher() {
        List<Annotation> list = new Mirror(provider)
            .on((AnnotatedElement) ClassFixture.class)
            .reflectAll()
            .annotationsMatching(new Matcher<Annotation>() {

                public boolean accepts(final Annotation element) {
                    return true;
                }
            });
        assertEquals(2, list.size());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testThatMatcherIsRespectedForAnnotations() {
        List<Annotation> list = new Mirror(provider)
            .on((AnnotatedElement) ClassFixture.class)
            .reflectAll()
            .annotationsMatching(new Matcher<Annotation>() {

                public boolean accepts(final Annotation element) {
                    return "AnnotationFixture".equals(element.annotationType().getSimpleName());
                }
            });
        assertEquals(1, list.size());
    }

}
