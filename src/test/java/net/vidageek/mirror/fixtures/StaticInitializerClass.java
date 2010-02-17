package net.vidageek.mirror.fixtures;

final public class StaticInitializerClass {

	static {
		// This needs to be done this, otherwise compiler can predict that this
		// initializer won't finish.
		throwsException();
	}

	private static void throwsException() {
		throw new RuntimeException("ReflectionProvider did run static initializer. It shouldn't.");
	}

}
