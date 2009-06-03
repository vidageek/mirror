/**
 * 
 */
package net.vidageek.mirror.exception;

/**
 * Exception that usually works as a wrapper for checked exceptions. It is just
 * thrown by reflection providers.
 * 
 * @author jonasabreu
 * 
 */
@SuppressWarnings("serial")
public final class ReflectionProviderException extends MirrorException {

	public ReflectionProviderException(final String message) {
		super(message);
	}

	public ReflectionProviderException(final String message, final Throwable t) {
		super(message, t);
	}
}
