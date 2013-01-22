package net.vidageek.mirror.exception;

public class ProxyProviderNotFoundException extends ReflectionProviderException {

	private static final long serialVersionUID = 1L;
	
	public ProxyProviderNotFoundException(String message) {
		super(message);
	}
}
