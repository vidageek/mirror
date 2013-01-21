package net.vidageek.mirror.provider.experimental.performance;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.experimental.sun15.Sun15ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class TestSunInternalReflectionProviderIsFasterThanTraditionalReflection {

    private static final int BATCH_SIZE = 1000000;
    private Mirror sunMirror;
    private Mirror defaultMirror;

    @Before
    public void setup() {
        sunMirror = new Mirror(new Sun15ReflectionProvider());
        defaultMirror = new Mirror(new DefaultMirrorReflectionProvider());
    }

    @Test
    public void testThatMethodInvokingIsFasterUsingSunInternalClasses() {
        Method method = defaultMirror.on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();
        MethodFixture fixture = new MethodFixture();

        long begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            defaultMirror.on(fixture).invoke().method(method).withoutArgs();
        }

        long defaultGap = System.currentTimeMillis() - begin;

        begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            sunMirror.on(fixture).invoke().method(method).withoutArgs();
        }

        long sunGap = System.currentTimeMillis() - begin;
        System.out.println("Sun method invocation: " + sunGap);
        System.out.println("Default method invocation: " + defaultGap);
        assertTrue("Sun implementation took " + sunGap + " millisseconds and default implemetation took "
                + defaultGap + " millisseconds.", sunGap < defaultGap);
    }

    @Test
    public void testThatFieldGettingIsFasterUsingSunInternalClasses() {
        Field field = defaultMirror.on(FieldFixture.class).reflect().field("field");
        FieldFixture fixture = new FieldFixture(10);
        long begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            defaultMirror.on(fixture).get().field(field);
        }

        long defaultGap = System.currentTimeMillis() - begin;

        begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            sunMirror.on(fixture).get().field(field);
        }

        long sunGap = System.currentTimeMillis() - begin;
        System.out.println("Sun field getting: " + sunGap);
        System.out.println("Default field getting: " + defaultGap);
        assertTrue("Sun implementation took " + sunGap + " millisseconds and default implemetation took "
                + defaultGap + " millisseconds.", sunGap < defaultGap);
    }

    @Test
    public void testThatFieldSettingIsFasterUsingSunInternalClasses() {
        Field field = defaultMirror.on(FieldFixture.class).reflect().field("field");
        FieldFixture fixture = new FieldFixture(10);
        long begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            defaultMirror.on(fixture).set().field(field).withValue(Integer.MAX_VALUE);
        }

        long defaultGap = System.currentTimeMillis() - begin;

        begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            sunMirror.on(fixture).set().field(field).withValue(Integer.MAX_VALUE);
        }

        long sunGap = System.currentTimeMillis() - begin;
        System.out.println("Sun field setting: " + sunGap);
        System.out.println("Default field setting: " + defaultGap);
        assertTrue("Sun implementation took " + sunGap + " millisseconds and default implemetation took "
                + defaultGap + " millisseconds.", sunGap < defaultGap);
    }

    @Test
    public void testThatContructorInvokingIsFasterUsingSunInternalClasses() {
        Constructor<ConstructorFixture> constructor = defaultMirror
            .on(ConstructorFixture.class)
            .reflect()
            .constructor()
            .withoutArgs();
        long begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            defaultMirror.on(ConstructorFixture.class).invoke().constructor(constructor).withoutArgs();
        }

        long defaultGap = System.currentTimeMillis() - begin;

        begin = System.currentTimeMillis();

        for (int i = 0; i < BATCH_SIZE; i++) {
            sunMirror.on(ConstructorFixture.class).invoke().constructor(constructor).withoutArgs();
        }

        long sunGap = System.currentTimeMillis() - begin;
        System.out.println("Sun contructor invoking: " + sunGap);
        System.out.println("Default constructor invoking: " + defaultGap);
        assertTrue("Sun implementation took " + sunGap + " millisseconds and default implemetation took "
                + defaultGap + " millisseconds.", sunGap < defaultGap);
    }
}
