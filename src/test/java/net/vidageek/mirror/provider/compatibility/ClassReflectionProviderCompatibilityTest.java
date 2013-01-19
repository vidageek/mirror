/**
 * 
 */
package net.vidageek.mirror.provider.compatibility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.InterfaceFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
public class ClassReflectionProviderCompatibilityTest implements ReflectionProviderDatapoints {

	private final ReflectionProvider defaultProvider = new DefaultMirrorReflectionProvider();

	@Theory
	public void testReflectClass(final ReflectionProvider r) {
		assertEquals(ClassFixture.class, r.getClassReflectionProvider(ClassFixture.class).reflectClass());

		assertEquals(ClassFixture.class, r.getClassReflectionProvider(ClassFixture.class.getName())
				.reflectClass());
	}

	@Theory
	public void testReflectField(final ReflectionProvider r) {
		Field reference = defaultProvider.getClassReflectionProvider(FieldFixture.class).reflectField("publicField");

		assertNotNull(reference);

		assertEquals(reference, r.getClassReflectionProvider(FieldFixture.class).reflectField("publicField"));

		assertEquals(null, r.getClassReflectionProvider(FieldFixture.class).reflectField("fieldasdf"));
	}

	@Theory
	public void testReflectPrivateField(final ReflectionProvider r) {
		Field reference = defaultProvider.getClassReflectionProvider(FieldFixture.class).reflectField("field");

		assertNotNull(reference);

		assertEquals(reference, r.getClassReflectionProvider(FieldFixture.class).reflectField("field"));
	}

	@Theory
	public void testReflectStaticField(final ReflectionProvider r) {
		Field reference = defaultProvider.getClassReflectionProvider(FieldFixture.class).reflectField("staticField");

		assertNotNull(reference);

		assertEquals(reference, r.getClassReflectionProvider(FieldFixture.class).reflectField("staticField"));
	}

	@Theory
	public void testReflectStaticSuperClassField(final ReflectionProvider r) {
		Field reference = defaultProvider.getClassReflectionProvider(ChildFixture.class)
				.reflectField("staticSuperClassField");

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ChildFixture.class).reflectField("staticSuperClassField"));
	}

	@Theory
	public void testReflectSuperClassField(final ReflectionProvider r) {
		Field reference = defaultProvider.getClassReflectionProvider(ChildFixture.class)
				.reflectField("superClassString");

		assertNotNull(reference);

		assertEquals(reference, r.getClassReflectionProvider(ChildFixture.class)
				.reflectField("superClassString"));
	}

	@Theory
	public void testReflectSuperClassPrivateField(final ReflectionProvider r) {
		Field reference = defaultProvider.getClassReflectionProvider(ChildFixture.class)
				.reflectField("superClassPrivateField");

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ChildFixture.class).reflectField("superClassPrivateField"));

	}

	@Theory
	public void testReflectMethod(final ReflectionProvider r) {
		assertNull(r.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("methodasdf", new Class<?>[] { Object.class }));

	}

	@Theory
	public void testReflectInstanceMethodWithArgs(final ReflectionProvider r) {

		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("methodWithOneArg", new Class<?>[] { String.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class)
									.reflectMethod("methodWithOneArg", new Class<?>[] { String.class }));

	}

	@Theory
	public void testReflectInstanceMethodWithoutArgs(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("methodWithNoArgs", new Class<?>[0]);

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithNoArgs",
																							new Class<?>[0]));

	}

	@Theory
	public void testReflectStaticMethodWithArgs(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("staticMethodWithOneArg", new Class<?>[] { String.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class)
									.reflectMethod("staticMethodWithOneArg", new Class<?>[] { String.class }));
	}

	@Theory
	public void testReflectStaticMethodWithoutArgs(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("staticMethod", new Class<?>[0]);

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class).reflectMethod("staticMethod",
																							new Class<?>[0]));
	}

	@Theory
	public void testReflectSuperClassMethod(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("equals", new Class<?>[] { Object.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class)
									.reflectMethod("equals", new Class<?>[] { Object.class }));
	}

	@Theory
	public void testReflectMethodWithPrimitivesArgs(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("methodWithTwoPrimitives", new Class<?>[] { int.class, boolean.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithTwoPrimitives",
																							new Class<?>[] { int.class,
																									boolean.class }));
	}

	@Theory
	public void testReflectMethodWithPrimitivesArgsUsingWrappers(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("methodWithTwoPrimitives", new Class<?>[] { Integer.class, Boolean.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithTwoPrimitives",
																							new Class<?>[] {
																									Integer.class,
																									Boolean.class }));
	}

	@Theory
	public void testReflectMethodWithWrapperArgsUsingPrimitives(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("methodWithFourArgs",
								new Class<?>[] { String.class, boolean.class, Class.class, long.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class).reflectMethod("methodWithFourArgs",
																							new Class<?>[] {
																									String.class,
																									boolean.class,
																									Class.class,
																									long.class }));
	}

	@Theory
	public void testReflectPrivateMethod(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(MethodFixture.class)
				.reflectMethod("privateMethod", new Class<?>[] {});

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(MethodFixture.class).reflectMethod("privateMethod",
																							new Class<?>[] {}));
	}

	@Theory
	public void testReflectPrivateSuperClassMethod(final ReflectionProvider r) {
		Method reference = defaultProvider.getClassReflectionProvider(ChildFixture.class)
				.reflectMethod("superClassPrivateMethod", new Class<?>[] {});

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ChildFixture.class).reflectMethod("superClassPrivateMethod",
																							new Class<?>[] {}));
	}

	@Theory
	public void testReflectConstructor(final ReflectionProvider r) {
		assertNull(r.getClassReflectionProvider(ConstructorFixture.class)
				.reflectConstructor(new Class<?>[] { Object.class }));

	}

	@Theory
	public void testReflectConstructorWithoutArgs(final ReflectionProvider r) {
		Constructor<ConstructorFixture> reference = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(new Class<?>[] {});

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ConstructorFixture.class)
									.reflectConstructor(new Class<?>[] {}));
	}

	@Theory
	public void testReflectConstructorWithArgs(final ReflectionProvider r) {
		Constructor<ConstructorFixture> reference = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class)
				.reflectConstructor(new Class<?>[] { String.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ConstructorFixture.class)
									.reflectConstructor(new Class<?>[] { String.class }));
	}

	@Theory
	public void testReflectConstructorWithPrimitiveArgs(final ReflectionProvider r) {
		Constructor<ConstructorFixture> reference = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(	new Class<?>[] { int.class,
																									long.class,
																									boolean.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ConstructorFixture.class)
									.reflectConstructor(new Class<?>[] { int.class, long.class, boolean.class }));
	}

	@Theory
	public void testReflectConstructorWithPrimitiveArgsUsingWrappers(final ReflectionProvider r) {
		Constructor<ConstructorFixture> reference = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(	new Class<?>[] {
																									Integer.class,
																									Long.class,
																									Boolean.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ConstructorFixture.class)
									.reflectConstructor(new Class<?>[] { Integer.class, Long.class, Boolean.class }));
	}

	@Theory
	public void testReflectConstructorWithWrapperArgsUsingPrimitive(final ReflectionProvider r) {
		Constructor<ConstructorFixture> reference = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(	new Class<?>[] {
																									String.class,
																									int.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ConstructorFixture.class)
									.reflectConstructor(new Class<?>[] { String.class, int.class }));
	}

	@Theory
	public void testReflectPrivateConstructor(final ReflectionProvider r) {
		Constructor<ConstructorFixture> reference = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(new Class<?>[] { Long.class });

		assertNotNull(reference);

		assertEquals(reference,
							r.getClassReflectionProvider(ConstructorFixture.class)
									.reflectConstructor(new Class<?>[] { Long.class }));
	}

	@Theory
	public void testReflectAllFields(final ReflectionProvider r) {
		List<Field> reference = defaultProvider.getClassReflectionProvider(FieldFixture.class).reflectAllFields();
		List<Field> underTest = r.getClassReflectionProvider(FieldFixture.class).reflectAllFields();

		assertEquals(reference.size(), underTest.size());

		for (Field field : reference) {
			assertTrue(underTest.contains(field));
		}
	}

	@Theory
	public void testReflectAllMethods(final ReflectionProvider r) {
		List<Method> reference = defaultProvider.getClassReflectionProvider(MethodFixture.class).reflectAllMethods();
		List<Method> underTest = r.getClassReflectionProvider(MethodFixture.class).reflectAllMethods();

		assertEquals(reference.size(), underTest.size());

		for (Method method : reference) {
			assertTrue(underTest.contains(method));
		}
	}

	@Theory
	public void testReflectAllConstructors(final ReflectionProvider r) {
		List<Constructor<ConstructorFixture>> reference = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectAllConstructors();
		List<Constructor<ConstructorFixture>> underTest = r.getClassReflectionProvider(ConstructorFixture.class)
				.reflectAllConstructors();

		assertEquals(reference.size(), underTest.size());

		for (Constructor<ConstructorFixture> constructor : reference) {
			assertTrue(underTest.contains(constructor));
		}
	}

	@Theory
	public void testThatCanReflectInterfaceFields(final ReflectionProvider r) {
		assertNotNull(r.getClassReflectionProvider(InterfaceFixture.class).reflectField("interfaceField"));
	}

	@Theory
	public void testThatFieldLookupIsDoneOnInterfaces(final ReflectionProvider r) {
		Field field = defaultProvider.getClassReflectionProvider(ChildFixture.class).reflectField("interfaceField");

		assertEquals("interfaceField",
							r.getFieldReflectionProvider(new ChildFixture(), ChildFixture.class, field).getValue());
	}

	@Theory
	public void testThatFieldLookupIsDoneOnInterfacesUsingInterfaceFieldRepresentation(final ReflectionProvider r) {
		Field field = defaultProvider.getClassReflectionProvider(InterfaceFixture.class).reflectField("interfaceField");

		assertEquals("interfaceField",
							r.getFieldReflectionProvider(new ChildFixture(), ChildFixture.class, field).getValue());
	}

	@Theory
	public void testThatDoesNotRunStaticInitializerToReflectClass(final ReflectionProvider r) {
		defaultProvider.getClassReflectionProvider("net.vidageek.mirror.fixtures.StaticInitializerClass");
	}

}
