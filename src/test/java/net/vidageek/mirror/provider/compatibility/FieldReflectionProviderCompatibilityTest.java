/**
 * 
 */
package net.vidageek.mirror.provider.compatibility;

import java.lang.reflect.Field;

import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.SuperClassFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProviderDataPointList;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Assert;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
public class FieldReflectionProviderCompatibilityTest implements ReflectionProviderDataPointList {

    private final ReflectionProvider provider = new DefaultMirrorReflectionProvider();

    @Theory
    public void testGetField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(FieldFixture.class).reflectField("publicField");
        FieldFixture target = new FieldFixture(0);
        target.publicField = 100;

        Assert.assertEquals(new Integer(100), r.getFieldReflectionProvider(target, FieldFixture.class, field)
                .getValue());

    }

    @Theory
    public void testGetPrivateField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(FieldFixture.class).reflectField("field");
        FieldFixture target = new FieldFixture(100);

        Assert.assertEquals(new Integer(100), r.getFieldReflectionProvider(target, FieldFixture.class, field)
                .getValue());
    }

    @Theory
    public void testGetStaticField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(FieldFixture.class).reflectField("staticField");
        FieldFixture target = new FieldFixture(100);

        Assert.assertEquals(new Integer(100), r.getFieldReflectionProvider(target, FieldFixture.class, field)
                .getValue());
    }

    @Theory
    public void testGetStaticSuperClassField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(ChildFixture.class).reflectField("staticSuperClassField");
        SuperClassFixture.staticSuperClassField = "100";

        Assert.assertEquals("100", r.getFieldReflectionProvider(null, ChildFixture.class, field).getValue());
    }

    @Theory
    public void testGetSuperClassField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(ChildFixture.class).reflectField("superClassString");
        SuperClassFixture target = new SuperClassFixture("");
        target.superClassString = "100";

        Assert.assertEquals("100", r.getFieldReflectionProvider(target, ChildFixture.class, field).getValue());
    }

    @Theory
    public void testGetSuperClassPrivateField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(ChildFixture.class).reflectField("superClassPrivateField");
        SuperClassFixture target = new SuperClassFixture("100");

        Assert.assertEquals("100", r.getFieldReflectionProvider(target, ChildFixture.class, field).getValue());
    }

    @Theory
    public void testSetField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(FieldFixture.class).reflectField("publicField");

        FieldFixture target = new FieldFixture(0);

        r.getFieldReflectionProvider(target, FieldFixture.class, field).setValue(100);

        Assert.assertEquals(new Integer(100), provider.getFieldReflectionProvider(target, FieldFixture.class, field)
                .getValue());
    }

    @Theory
    public void testSetPrivateField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(FieldFixture.class).reflectField("field");

        FieldFixture target = new FieldFixture(0);

        r.getFieldReflectionProvider(target, FieldFixture.class, field).setValue(100);

        Assert.assertEquals(new Integer(100), provider.getFieldReflectionProvider(target, FieldFixture.class, field)
                .getValue());
    }

    @Theory
    public void testSetStaticField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(FieldFixture.class).reflectField("staticField");

        FieldFixture target = new FieldFixture(0);

        r.getFieldReflectionProvider(target, FieldFixture.class, field).setValue(100);

        Assert.assertEquals(new Integer(100), provider.getFieldReflectionProvider(target, FieldFixture.class, field)
                .getValue());
    }

    @Theory
    public void testSetStaticSuperClassField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(ChildFixture.class).reflectField("staticSuperClassField");

        ChildFixture target = new ChildFixture();

        r.getFieldReflectionProvider(target, ChildFixture.class, field).setValue("100");

        Assert.assertEquals("100", provider.getFieldReflectionProvider(target, ChildFixture.class, field).getValue());
    }

    @Theory
    public void testSetSuperClassField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(ChildFixture.class).reflectField("superClassString");

        ChildFixture target = new ChildFixture();

        r.getFieldReflectionProvider(target, ChildFixture.class, field).setValue("100");

        Assert.assertEquals("100", provider.getFieldReflectionProvider(target, ChildFixture.class, field).getValue());
    }

    @Theory
    public void testSetSuperClassPrivateField(final ReflectionProvider r) {
        Field field = provider.getClassReflectionProvider(ChildFixture.class).reflectField("superClassPrivateField");

        ChildFixture target = new ChildFixture();

        r.getFieldReflectionProvider(target, ChildFixture.class, field).setValue("100");

        Assert.assertEquals("100", provider.getFieldReflectionProvider(target, ChildFixture.class, field).getValue());
    }

}
