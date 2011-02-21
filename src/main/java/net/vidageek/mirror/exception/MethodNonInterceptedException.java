package net.vidageek.mirror.exception;

/**
 * 
 * @author Juliano Alves
 * 
 */
@SuppressWarnings("serial")
public class MethodNonInterceptedException extends ReflectionProviderException {

	public MethodNonInterceptedException(String message) {
		super(message);
	}
}
