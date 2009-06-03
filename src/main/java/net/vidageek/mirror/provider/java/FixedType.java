package net.vidageek.mirror.provider.java;

enum FixedType {

	VOID(void.class),

	BOOLEAN(boolean.class),

	BYTE(byte.class),

	SHORT(short.class),

	CHAR(char.class),

	INT(int.class),

	LONG(long.class),

	FLOAT(float.class),

	DOUBLE(double.class);

	private final Class<?> clazz;

	private FixedType(final Class<?> clazz) {
		this.clazz = clazz;
	}

	public static Class<?> fromValue(final String className) {
		for (final FixedType type : values()) {
			if (type.clazz.toString().equals(className)) {
				return type.clazz;
			}
		}
		return null;
	}

}
