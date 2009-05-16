/**
 * 
 */
package net.vidageek.mirror.set;

import java.lang.reflect.Field;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.SuperClassFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

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
		provider = new PureJavaReflectionProvider();
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

	@Test(expected = MirrorException.class)
	public void testThatThrowsExceptionIfFieldIsFinalAndStatic() {
		new FieldSetterByField(provider, new FieldFixture(0), FieldFixture.class, new Mirror(provider)
				.on(FieldFixture.class).reflect().field("STATIC_FINAL_FIELD"));
	}

	@Test(expected = MirrorException.class)
	public void testThatThrowsExceptionIfFieldIsFinal() {
		new FieldSetterByField(provider, new FieldFixture(0), FieldFixture.class, new Mirror(provider)
				.on(FieldFixture.class).reflect().field("finalField"));
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

		Assert.assertEquals(null, target.referenceField);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatCantSetNullValueOnPrimitiveField() {

		Field field = new Mirror(provider).on(FieldFixture.class).reflect().field("field");

		new FieldSetterByField(provider, new FieldFixture(0), FieldFixture.class, field).withValue(null);

	}

}
