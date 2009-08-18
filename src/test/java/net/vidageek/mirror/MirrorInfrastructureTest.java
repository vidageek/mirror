package net.vidageek.mirror;

import java.lang.reflect.Method;
import java.util.List;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Test;

public class MirrorInfrastructureTest {

    @Test
    public void testThatMirrorContainsAllPublicMethodsOfMirrorInfrastructure() {
        Mirror infrastructure = new Mirror(new DefaultMirrorReflectionProvider());

        List<Method> mirrorMethods = infrastructure.on(Mirror.class).reflectAll().methods();
        List<Method> infraMethods = infrastructure.on(Mirror.class).reflectAll().methods();

        Assert.assertEquals(mirrorMethods.size(), infraMethods.size());

    }
}
