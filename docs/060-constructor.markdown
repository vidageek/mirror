[chapter Constructor]

Constructor Manipulation using Mirror.

[section Reflecting]

Reflecting a constructor by arguments (will return null if not found):

[java]
Class<T> clazz;
Constructor<T> c = new Mirror().on(clazz).reflect().constructor()
						.withArgs(String.class, Object.class);
[/java]

Reflecting the only constructor of a class (will throw MirrorException if more than one is found):

[java]
Class<T> clazz;
Constructor<T> m = new Mirror().on(clazz).reflect().constructor().withAnyArgs();
[/java]

Reflecting all constructors of a class (will return an empty list if none found):

[java]
Class<T> clazz;
List<Constructor<T>> l = new Mirror().on(clazz).reflectAll().constructors();
[/java]

Reflecting all constructors of a class that matches a Matcher<Constructor> (will return an empty list if none found):

[java]
Class<T> clazz;
List<Constructor<T>> l = new Mirror().on(clazz).reflectAll()
                                  .constructors().matching(new YourCustomMatcher());
[/java]

You can also map your constructors to other types:
[java]
Class clazz;
List<String> l = new Mirror().on(clazz).reflectAll()
                      .constructors().mappingTo(new YourConstructorToStringMapper());
[/java]


[section Invoking Constructors]

Invoking a constructor:

[java]
Class<T> clazz;
T t = new Mirror().on(clazz).invoke().constructor().withoutArgs();
[/java]

You can also pass a java.lang.reflect.Constructor:

[java]
Constructor<T> aConstructor;
Class<T> clazz;
T t = new Mirror().on(clazz).invoke().constructor(aConstructor).withArgs(value1, value2);
[/java]

[section Bypassing Constructors]
With Mirror, you can completely bypass constructors to create instances.

[java]
Class<T> clazz;
T t = new Mirror().on(clazz).invoke().constructor().bypasser();
[/java]

Please note that this is only supported on Objenesis supported VMs. Take a look at http://code.google.com/p/objenesis/wiki/ListOfCurrentlySupportedVMs .