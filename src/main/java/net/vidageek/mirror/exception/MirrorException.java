package net.vidageek.mirror.exception;

/**
 * Exception that usually works as a wrapper for checked exceptions.
 * 
 * @author jonasabreu
 */
public class MirrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MirrorException(final String message) {
		super(message);
	}

	public MirrorException(final String message, final Throwable t) {
		super(message, t);
	}

}
