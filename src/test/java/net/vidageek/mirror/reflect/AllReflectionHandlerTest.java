/**
 * 
 */
package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.BeanFixture;
import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.ChildHidingFixture;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.fixtures.NotABeanFixture;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class AllReflectionHandlerTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() {
		new DefaultAllReflectionHandler<Object>(provider, null);
	}

	@Test
	public void testThatReflectAllFields() {
		List<Field> fields = new DefaultAllReflectionHandler<FieldFixture>(provider, FieldFixture.class).fields();

		assertEquals(8, fields.size());
	}

	@Test
	public void testThatReflectHierarchyFields() {
		List<Field> fields = new DefaultAllReflectionHandler<ChildFixture>(provider, ChildFixture.class).fields();

		assertEquals(5, fields.size());
	}

	@Test
	public void testThatFindsHidingFields() {
		List<Field> fields = new DefaultAllReflectionHandler<ChildHidingFixture>(provider, ChildHidingFixture.class)
				.fields();

		assertEquals(2, fields.size());
	}

	@Test
	public void testThatReflectAllMethods() {
		List<Method> methods = new DefaultAllReflectionHandler<MethodFixture>(provider, MethodFixture.class).methods();

		assertEquals(24, methods.size());
	}

	@Test
	public void testThatReflectHierarchyMethods() {
		List<Method> methods = new DefaultAllReflectionHandler<ChildFixture>(provider, ChildFixture.class).methods();

		assertEquals(21, methods.size());
	}

	@Test
	public void testThatFindsOverridingMethods() {
		List<Method> methods = new DefaultAllReflectionHandler<ChildHidingFixture>(provider, ChildHidingFixture.class)
				.methods();

		assertEquals(14, methods.size());
	}

	@Test
	public void testThatReflectsAllConstructors() {
		List<Constructor<ConstructorFixture>> constructors = new DefaultAllReflectionHandler<ConstructorFixture>(
				provider, ConstructorFixture.class).constructors();

		assertEquals(6, constructors.size());
	}

	@Test
	public void testThatReflectsAllSetters() {
		List<Method> setters = new DefaultAllReflectionHandler<BeanFixture>(provider, BeanFixture.class).setters();
		assertEquals(2, setters.size());
		assertTrue("should contain setField", setters.get(0).getName().equals("setField"));
		assertTrue("should contain setBooleanField", setters.get(1).getName().equals("setBooleanField"));
	}

	@Test
	public void testThatDoesntReflectMethodAsSetterIfArgumentNumberIsNotOneOrReturnTypeIsNotVoid() {
		List<Method> setters = new DefaultAllReflectionHandler<NotABeanFixture>(provider, NotABeanFixture.class)
				.setters();
		assertEquals(0, setters.size());
	}

	@Test
	public void testThatReflectsAllGetters() {
		List<Method> getters = new DefaultAllReflectionHandler<BeanFixture>(provider, BeanFixture.class).getters();
		assertEquals(2, getters.size());
		assertEquals("getField", getters.get(0).getName());
		assertEquals("getClass", getters.get(1).getName());
	}

	@Test
	public void testThatDoesntReflectMethodAsGetterIfArgumentNumberIsNotZeroOrReturnTypeIsVoid() {
		List<Method> getters = new DefaultAllReflectionHandler<NotABeanFixture>(provider, NotABeanFixture.class)
				.getters();
		assertEquals(1, getters.size());
		assertEquals("getClass", getters.get(0).getName());
	}

	@SuppressWarnings("unused")
	@Test
	public void testThatAllReflectionHandlersReturnMirrorList() {
		MirrorList<Constructor<ClassFixture>> constructors = new Mirror(provider).on(ClassFixture.class).reflectAll()
				.constructors();
		MirrorList<Field> fields = new Mirror(provider).on(FieldFixture.class).reflectAll().fields();
		MirrorList<Method> methods = new Mirror(provider).on(MethodFixture.class).reflectAll().methods();
		MirrorList<Annotation> annotations = new Mirror(provider).on((AnnotatedElement) ClassFixture.class)
				.reflectAll().annotations();
	}
}
