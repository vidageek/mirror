/**
 * 
 */
package net.vidageek.mirror.provider.compatibility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.AnnotationFixture;
import net.vidageek.mirror.fixtures.AnotherAnnotationFixture;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
public class AnnotatedElementReflectionProviderCompatibilityTest implements ReflectionProviderDatapoints {

	@DataPoint
	public static AnnotatedElement clazz;
	@DataPoint
	public static AnnotatedElement field;
	@DataPoint
	public static AnnotatedElement method;
	@DataPoint
	public static AnnotatedElement constructor;

	@Theory
	public void testGetAnnotations(final AnnotatedElement element, final ReflectionProvider r) {
		AnnotatedElementReflectionProvider provider = r.getAnnotatedElementReflectionProvider(element);

		List<Annotation> list = provider.getAnnotations();

		assertTrue("List should contain AnnotationFixture", contains(list, AnnotationFixture.class));
		assertTrue(	"List should contain AnotherAnnotationFixture",
							contains(list, AnotherAnnotationFixture.class));
	}

	@Theory
	public void testGetAnnotation(final AnnotatedElement element, final ReflectionProvider r) {
		AnnotatedElementReflectionProvider provider = r.getAnnotatedElementReflectionProvider(element);

		assertNotNull(provider.getAnnotation(AnnotationFixture.class));

		assertNotNull(provider.getAnnotation(AnotherAnnotationFixture.class));
	}

	private boolean contains(final List<Annotation> list, final Class<? extends Annotation> ann) {
		for (Annotation annotation : list) {
			if (ann.equals(annotation.getClass().getInterfaces()[0])) {
				return true;
			}
		}
		return false;
	}

	@BeforeClass
	public static void init() {
		Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
		clazz = ClassFixture.class;
		field = mirror.on(FieldFixture.class).reflect().field("field");
		method = mirror.on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();
		constructor = mirror.on(ConstructorFixture.class).reflect().constructor().withoutArgs();
	}
}
