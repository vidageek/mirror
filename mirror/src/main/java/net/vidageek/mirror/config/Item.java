package net.vidageek.mirror.config;

/**
 * @author jonasabreu
 */
public enum Item {
    REFLECTION_PROVIDER("provider.class");

    private final String key;

    Item(final String key) {
        this.key = key;
    }

    public String getPropertyKey() {
        return key;
    }

}
