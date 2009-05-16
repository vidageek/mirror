package net.vidageek.mirror.provider;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 * Interface that defines reflection operations related to fields
 * 
 * @author jonasabreu
 */
public interface FieldReflectionProvider extends ReflectionElementReflectionProvider {

    /**
     * This method is used to set value on wrapped field.
     * 
     * @param value
     *            value to be set on wrapped field.
     * @throws ReflectionProviderException
     */
    void setValue(Object value);

    /**
     * This method is used to get wrapped field's value.
     * 
     * @return the value of the field represented by wrapped field.
     * @throws ReflectionProviderException
     */
    <T> T getValue();
}
