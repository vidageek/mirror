package net.vidageek.mirror.fixtures;

/**
 * 
 * @author jonasabreu
 * 
 */
public class ConstructorFixture {

	private final Integer constructor;

	@AnnotationFixture
	@AnotherAnnotationFixture
	public ConstructorFixture() {
		constructor = 0;
	}

	public ConstructorFixture(final String s) {
		constructor = 1;
	}

	public ConstructorFixture(final String s, final Integer i) {
		constructor = 2;
	}

	public ConstructorFixture(final int i, final long l, final boolean b) {
		constructor = 3;
	}

	@SuppressWarnings("unused")
	private ConstructorFixture(final Long l) {
		constructor = 4;
	}

	public ConstructorFixture(final int[] i) {
		constructor = 5;
	}

	public Integer getConstructor() {
		return constructor;
	}

}
