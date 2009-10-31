/**
 * 
 */
package net.vidageek.mirror.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

/**
 * Class that provides all logic to read configuration file and instantiate the
 * right provider type.
 * 
 * @author jonasabreu
 */
public final class MirrorProviderBuilder {

    private final InputStream configurationFile;

    public MirrorProviderBuilder(final InputStream configurationFile) {
        this.configurationFile = configurationFile;

    }

    public ReflectionProvider createProvider() {
        if (configurationFile == null) {
            return new DefaultMirrorReflectionProvider();
        }

        Map<Item, String> cfg = processProperties(configurationFile);

        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());

        return (ReflectionProvider) mirror.on(cfg.get(Item.REFLECTION_PROVIDER)).invoke().constructor().withoutArgs();
    }

    private Map<Item, String> processProperties(final InputStream configurationFile) {
        Map<Item, String> map = new HashMap<Item, String>();

        map.put(Item.REFLECTION_PROVIDER, DefaultMirrorReflectionProvider.class.getName());

        try {
            Properties properties = new Properties();
            properties.load(configurationFile);
            for (Item item : Item.values()) {
                if (properties.containsKey(item.getPropertyKey())) {
                    map.put(item, properties.getProperty(item.getPropertyKey()).trim());
                }
            }
        } catch (IOException e) {
            throw new MirrorException("could not ready file " + configurationFile, e);
        }
        return map;
    }
}
