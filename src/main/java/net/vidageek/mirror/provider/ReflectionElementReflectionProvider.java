/**
 * 
 */
package net.vidageek.mirror.provider;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 *Interface that defines common methods of reflection elements
 * 
 * @author jonasabreu
 */
public interface ReflectionElementReflectionProvider {

	/**
	 * set wrapped object as accessible.
	 * 
	 * @throws ReflectionProviderException
	 */
	void setAccessible();

}
