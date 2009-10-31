.PHONY: test

JAVA5_HOME='/System/Library/Frameworks/JavaVM.framework/Versions/1.5/Home'
JAVA6_HOME='/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home'

test:
	mvn clean test;
	cd provider/test && mvn clean test;
	cd provider/sun15 && JAVA_HOME=$(JAVA5_HOME) mvn clean test;
	cd provider/sun15 && JAVA_HOME=$(JAVA6_HOME) mvn clean test;
