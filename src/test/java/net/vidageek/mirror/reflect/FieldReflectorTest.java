/**
 * 
 */
package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.InterfaceFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class FieldReflectorTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldNameIsNull() {
		new DefaultFieldReflector(provider, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldNameBlank() {
		new DefaultFieldReflector(provider, "   \n   \t   ");
	}

	@Test
	public void testThatCanReflectField() {
		Field field = new Mirror().on(ChildFixture.class).reflect().field("integer");
		assertNotNull(field);
		assertEquals(field.getType(), Integer.class);
	}

	@Test
	public void testThatCanReflectSuperClassField() {
		Field field = new Mirror().on(ChildFixture.class).reflect().field("superClassString");
		assertNotNull(field);
		assertEquals(field.getType(), String.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfTargetIsNull() {
		new Mirror().on((Class<?>) null).reflect().field("anyField");
	}

	@Test
	public void testThatCanReflectInterfaceFields() {
		assertNotNull(new DefaultFieldReflector(new DefaultMirrorReflectionProvider(), "interfaceField")
				.onClass(InterfaceFixture.class));
	}

	@Test
	public void testThatFieldLookupIsDoneOnInterfaces() {
		assertEquals("interfaceField", new Mirror(new DefaultMirrorReflectionProvider()).on(new ChildFixture())
				.get().field("interfaceField"));
	}

	@Test
	public void testThatFieldLookupIsDoneOnInterfacesUsingInterfaceFieldRepresentation() {
		Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
		Field field = mirror.on(InterfaceFixture.class).reflect().field("interfaceField");

		assertEquals("interfaceField", mirror.on(new ChildFixture()).get().field(field));
	}
}
