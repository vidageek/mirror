/**
 * 
 */
package net.vidageek.mirror.set;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.SuperClassFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class FieldSetterByFieldTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() {
		Field field = new Mirror(provider).on(FieldFixture.class).reflect().field("field");

		new FieldSetterByField(provider, new Object(), null, field);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldIsNull() {
		new FieldSetterByField(provider, new Object(), FieldFixture.class, null);
	}

	/*
	 * Test to avoid bug #57, descripted at
	 * http://bugs.vidageek.net/bug.php?op=show&bugid=57
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldDeclaringClassIsNotAssignableFromClass() {
		Field field = new Mirror(provider).on(ChildFixture.class).reflect().field("integer");

		new FieldSetterByField(provider, new Object(), SuperClassFixture.class, field);
	}

	@Test
	public void testThatCanSetFieldIfFieldIsFinal() {
		final FieldFixture fixture = new FieldFixture(0);
		new FieldSetterByField(provider, fixture, FieldFixture.class, new Mirror(provider).on(FieldFixture.class)
				.reflect().field("finalField")).withValue(2);
		assertEquals(2, new Mirror(new DefaultMirrorReflectionProvider()).on(fixture).get().field("finalField"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsIllegalArgumentExceptionIfValueTypeDoesntMatchFieldType() {

		Field field = new Mirror(provider).on(FieldFixture.class).reflect().field("field");

		new FieldSetterByField(provider, new FieldFixture(0), FieldFixture.class, field).withValue("string");

	}

	@Test
	public void testThatDoesntThrowsExceptionIfTypeIsAssignableFromValue() {

		Field field = new Mirror(provider).on(FieldFixture.class).reflect().field("superType");

		new FieldSetterByField(provider, new FieldFixture(0), FieldFixture.class, field).withValue("string");

	}

	@Test
	public void testThatCanSetNullValueOnReferenceField() {
		Mirror mirror = new Mirror(provider);
		Field field = mirror.on(FieldFixture.class).reflect().field("referenceField");

		FieldFixture target = new FieldFixture(0);
		target.referenceField = new Object();

		new FieldSetterByField(provider, target, FieldFixture.class, field).withValue(null);

		assertEquals(null, target.referenceField);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatCantSetNullValueOnPrimitiveField() {

		Field field = new Mirror(provider).on(FieldFixture.class).reflect().field("field");

		new FieldSetterByField(provider, new FieldFixture(0), FieldFixture.class, field).withValue(null);

	}

}
