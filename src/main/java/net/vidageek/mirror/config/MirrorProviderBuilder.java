/**
 * 
 */
package net.vidageek.mirror.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

	private final URL configurationFile;

	public MirrorProviderBuilder(final URL configurationFile) {
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

	private Map<Item, String> processProperties(final URL file) {
		Map<Item, String> map = new HashMap<Item, String>();

		map.put(Item.REFLECTION_PROVIDER, DefaultMirrorReflectionProvider.class.getName());

		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(file.toURI())));
			for (Item item : Item.values()) {
				if (properties.containsKey(item.getPropertyKey())) {
					map.put(item, properties.getProperty(item.getPropertyKey()).trim());
				}
			}
		} catch (IOException e) {
			throw new MirrorException("could not ready file " + file, e);
		} catch (URISyntaxException e) {
			throw new MirrorException("could not ready file " + file, e);
		}
		return map;
	}
}
