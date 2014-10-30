package net.vidageek.mirror;

/**
 * Utility class to check preconditions.
 * 
 * @author Otávio Scherer Garcia
 */
public final class Preconditions {

	/**
	 * Throws an {@link IllegalArgumentException} if parameter <code>condition</code> is <code>false</code>.
	 */
	public static void checkArgument(boolean condition, String message, Object... messageArguments) {
		if (!condition) {
			throw new IllegalArgumentException(String.format(message, messageArguments));
		}
	}
}
