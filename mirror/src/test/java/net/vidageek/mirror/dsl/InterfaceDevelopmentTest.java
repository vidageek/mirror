package net.vidageek.mirror.dsl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.vidageek.mirror.fixtures.AnnotationFixture;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.fixtures.SubClassOfTypedClassFixture;

import org.junit.Before;
import org.junit.Test;

/**
 * These are tests to ensure the readability level wanted.
 * 
 * @author jonasabreu
 */

public class InterfaceDevelopmentTest {

    private MethodFixture methodFixture;

    private FieldFixture fieldFixture;

    private Method staticMethod;

    private Method instanceMethod;

    private Constructor<ConstructorFixture> constructor;

    private Field field;

    private Field typedField;

    @Before
    public void setup() {
        methodFixture = new MethodFixture();
        fieldFixture = new FieldFixture(0);

        staticMethod = new Mirror().on(MethodFixture.class).reflect().method("staticMethodWithOneArg").withArgs(
                String.class);
        instanceMethod = new Mirror().on(MethodFixture.class).reflect().method("methodWithOneArg").withArgs(
                String.class);
        constructor = new Mirror().on(ConstructorFixture.class).reflect().constructor().withArgs(String.class);
        field = new Mirror().on(FieldFixture.class).reflect().field("field");
        typedField = new Mirror().on(FieldFixture.class).reflect().field("typedField");
    }

    @Test
    public void testMethodInvocationInterface() {
        new Mirror().on(methodFixture).invoke().method("methodWithNoArgs").withoutArgs();

        new Mirror().on(methodFixture).invoke().method("methodWithOneArg").withArgs("arg");

        new Mirror().on(MethodFixture.class).invoke().method("staticMethod").withoutArgs();
        new Mirror().on(MethodFixture.class).invoke().method("staticMethodWithOneArg").withArgs("arg");

        new Mirror().on(methodFixture).invoke().method(instanceMethod).withArgs("arg");

        new Mirror().on(MethodFixture.class).invoke().method(staticMethod).withArgs("arg");
    }

    @Test
    public void testClassInstantiationInterface() {
        new Mirror().on(ConstructorFixture.class).invoke().constructor().withoutArgs();
        new Mirror().on(ConstructorFixture.class).invoke().constructor().withArgs("arg");

        new Mirror().on("net.vidageek.mirror.fixtures.ConstructorFixture").invoke().constructor().withoutArgs();
        new Mirror().on("net.vidageek.mirror.fixtures.ConstructorFixture").invoke().constructor().withArgs("arg");

        new Mirror().on(ConstructorFixture.class).invoke().constructor(constructor).withArgs("arg");
        new Mirror().on("net.vidageek.mirror.fixtures.ConstructorFixture").invoke().constructor(constructor).withArgs(
                "arg");
    }

    @Test
    public void testFieldSettingInterface() {
        new Mirror().on(fieldFixture).set().field("field").withValue(10);
        new Mirror().on(FieldFixture.class).set().field("staticField").withValue(10);

        new Mirror().on(fieldFixture).set().field(field).withValue(10);

    }

    @Test
    public void testFieldGettingInterface() {
        new Mirror().on(fieldFixture).get().field("field");
        new Mirror().on(FieldFixture.class).get().field("staticField");

        new Mirror().on(fieldFixture).get().field(field);
    }

    @Test
    public void testFieldReflectInterface() {
        new Mirror().on(FieldFixture.class).reflect().field("field");
        new Mirror().on("net.vidageek.mirror.fixtures.FieldFixture").reflect().field("field");
        new Mirror().on("net.vidageek.mirror.fixtures.FieldFixture").reflectAll().fields();
        new Mirror().on(FieldFixture.class).reflectAll().fields();
    }

    @Test
    public void testMethodReflectInterface() {
        new Mirror().on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();
        new Mirror().on(MethodFixture.class).reflect().method("methodWithOneArg").withArgs(String.class);
        new Mirror()
                    .on("net.vidageek.mirror.fixtures.MethodFixture")
                    .reflect()
                    .method("methodWithNoArgs")
                    .withoutArgs();
        new Mirror().on("net.vidageek.mirror.fixtures.MethodFixture").reflect().method("methodWithOneArg").withArgs(
                String.class);
        new Mirror().on(MethodFixture.class).reflectAll().methods();
        new Mirror().on("net.vidageek.mirror.fixtures.MethodFixture").reflectAll().methods();
    }

    @Test
    public void testConstructorReflectInterface() {
        new Mirror().on(ConstructorFixture.class).reflect().constructor().withoutArgs();
        new Mirror().on(ConstructorFixture.class).reflect().constructor().withArgs(int.class);
        new Mirror().on("net.vidageek.mirror.fixtures.ConstructorFixture").reflect().constructor().withoutArgs();
        new Mirror().on("net.vidageek.mirror.fixtures.ConstructorFixture").reflect().constructor().withArgs(int.class);
        new Mirror().on(FieldFixture.class).reflectAll().constructors();
        new Mirror().on("net.vidageek.mirror.fixtures.ConstructorFixture").reflectAll().constructors();

    }

    @Test
    public void testClassReflectInterface() {
        new Mirror().reflectClass("net.vidageek.mirror.fixtures.ClassFixture");
    }

    @Test
    public void testAnnotationReflection() {
        new Mirror().on(ClassFixture.class).reflect().annotation(AnnotationFixture.class).atClass();
        new Mirror().on(FieldFixture.class).reflect().annotation(AnnotationFixture.class).atField("field");
        new Mirror().on(FieldFixture.class).reflect().annotation(AnnotationFixture.class).atField("staticField");
        new Mirror()
                    .on(MethodFixture.class)
                    .reflect()
                    .annotation(AnnotationFixture.class)
                    .atMethod("methodWithNoArgs")
                    .withoutArgs();
        new Mirror()
                    .on(MethodFixture.class)
                    .reflect()
                    .annotation(AnnotationFixture.class)
                    .atMethod("methodWithOneArg")
                    .withArgs(String.class);

        new Mirror().on(ClassFixture.class).reflectAll().annotations().atClass();
        new Mirror().on(FieldFixture.class).reflectAll().annotations().atField("field");
        new Mirror().on(FieldFixture.class).reflectAll().annotations().atField("staticField");
        new Mirror().on(MethodFixture.class).reflectAll().annotations().atMethod("methodWithNoArgs").withoutArgs();
        new Mirror().on(MethodFixture.class).reflectAll().annotations().atMethod("methodWithOneArg").withArgs(
                String.class);
    }

    @Test
    public void testMemberReflection() {
        new Mirror().on(constructor).reflect().annotation(AnnotationFixture.class);
        new Mirror().on(constructor).reflectAll().annotations();
        new Mirror().on(field).reflect().annotation(AnnotationFixture.class);
        new Mirror().on(field).reflectAll().annotations();
        new Mirror().on(instanceMethod).reflect().annotation(AnnotationFixture.class);
        new Mirror().on(instanceMethod).reflectAll().annotations();
    }

    @Test
    public void testGenericTypeReflection() {
        new Mirror().on(typedField).reflect().genericType().atPosition(0);
        new Mirror().on(SubClassOfTypedClassFixture.class).reflect().parentGenericType().atPosition(0);
    }

    public void name() {

    }

}