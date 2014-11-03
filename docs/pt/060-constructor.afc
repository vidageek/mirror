[chapter Constructor]

Manipulacão de Construtor usando o Mirror.

[section Refletindo]

Refletindo um construtor pelos argumentos (retornará null se não for encontrado):

[java]
Class<T> clazz;
Constructor<T> c = new Mirror().on(clazz).reflect().constructor()
						.withArgs(String.class, Object.class);
[/java]

Reflectindo o único construtor de uma classe (lançará uma exceção se encontrar mais de um contrutor)

[java]
Class<T> clazz;
Constructor<T> m = new Mirror().on(clazz).reflect().constructor().withAnyArgs();
[/java]

Refletindo todos os construtores de uma classe (retornará uma lista vazia caso nada seja encontrado):

[java]
Class<T> clazz;
List<Constructor<T>> l = new Mirror().on(clazz).reflectAll().constructors();
[/java]

Refletindo todos os construtores de uma classe que sejam aceitos por um Matcher<Constructor> (retornará uma lista vazia caso nada seja encontrado):

[java]
Class<T> clazz;
List<Constructor<T>> l = new Mirror().on(clazz).reflectAll()
                                       .constructors().matching(new SeuProprioMatcher());
[/java]

Você também pode mapear seus construtores para outros tipos:

[java]
Class<T> clazz;
List<String> l = new Mirror().on(clazz).reflectAll()
                    .constructors().mappingTo(new SeuProprioMapperDeConstrutorParaString());
[/java]

[section Invocando Construtores]

Invocando um construtor:

[java]
Class<T> clazz;
T t = new Mirror().on(clazz).invoke().constructor().withoutArgs();
[/java]

Você também pode passar um java.lang.reflect.Constructor:

[java]
Constructor<T> umConstrutor;
Class<T> clazz;
T t = new Mirror().on(clazz).invoke().constructor(umConstrutor).withArgs(valor1, valor2);
[/java]

[section Ignorando Construtores]
Com o Mirror, você pode ignorar completamente construtores para criar instancias:

[java]
Class<T> clazz;
T t = new Mirror().on(clazz).invoke().constructor().bypasser();
[/java]

Note que esse comportamento só é suportado pelas VMs suportadas pelo projeto Objenesis. 
Dê uma olhada em http://code.google.com/p/objenesis/wiki/ListOfCurrentlySupportedVMs .