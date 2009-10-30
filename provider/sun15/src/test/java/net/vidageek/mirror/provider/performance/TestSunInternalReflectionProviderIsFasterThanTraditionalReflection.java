package net.vidageek.mirror.provider.performance;

import java.lang.reflect.Method;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import net.vidageek.mirror.provider.sun15.Sun15ReflectionProvider;

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
    public void testThatMethodInvokingIsFasterForMethodsWithoutArgs() {
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
        System.out.println(sunGap);
        System.out.println(defaultGap);
        Assert.assertTrue("Sun implementation took " + sunGap + " millisseconds and default implemetation took "
                + defaultGap + " millisseconds.", sunGap < defaultGap);
    }
}
