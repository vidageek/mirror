package net.vidageek.mirror.invoke;

import net.vidageek.mirror.bean.Bean;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.invoke.dsl.SetterMethodHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

final public class DefaultSetterMethodHandler implements SetterMethodHandler {

    private final ReflectionProvider provider;
    private final Object target;
    private final String fieldName;

    public DefaultSetterMethodHandler(final ReflectionProvider provider, final Object target, final String fieldName) {
        this.provider = provider;
        this.target = target;
        this.fieldName = fieldName;
    }

    public void withValue(final String value) {
        new Mirror(provider).on(target).invoke().method(new Bean().setter(fieldName)).withArgs(value);
    }

}
