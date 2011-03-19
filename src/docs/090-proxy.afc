[chapter Proxy]

Release 1.6 added a new feature to Mirror. The ability to create proxys of classes and interfaces.

[section Warning]

To use this feature, you must add CGLib to your classpath. 

If you use maven:

[xml]
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib-nodep</artifactId>
    <version>2.1_3</version>
</dependency>
[/xml]

Or you can download it manually at http://repo2.maven.org/maven2/cglib/cglib-nodep/2.1_3/cglib-nodep-2.1_3.jar . 

[section Usage]

[java]
SomeClass proxy = new Mirror().proxify(SomeClass.class)
		.interceptingWith(new YourCustomMethodInterceptor());
[/java]

The method ::interceptingWith:: receives any number of objects that implement 
::net.vidageek.mirror.proxy.dsl.MethodInterceptor::. These are called one after another until one of them
accepts intercepting the invoked method.



