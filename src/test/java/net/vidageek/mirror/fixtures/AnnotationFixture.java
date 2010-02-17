package net.vidageek.mirror.fixtures;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationFixture {

	String value() default "foo";

}
