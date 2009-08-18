/**
 * 
 */
package net.vidageek.mirror.config;

import junit.framework.Assert;
import net.vidageek.mirror.fake.FakeProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class MirrorProviderBuilderTest {

    @Test
    public void testThatInstantiatesPureJavaReflectionProviderIfPropertiesNotFound() {
        ReflectionProvider provider = new MirrorProviderBuilder(null).createProvider();

        Assert.assertEquals(DefaultMirrorReflectionProvider.class, provider.getClass());
    }

    @Test
    public void testThatInstantiatesProviderAtProperties() {
        ReflectionProvider provider = new MirrorProviderBuilder(MirrorProviderBuilderTest.class
                .getResource("/properties/mirror-fake-provider.properties")).createProvider();

        Assert.assertEquals(FakeProvider.class, provider.getClass());
    }
}
