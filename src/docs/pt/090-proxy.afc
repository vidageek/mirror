[chapter Proxy]

Uma nova feature foi adicionada ao Mirror no release 1.6. A capacidade de criar proxys de classes e interfaces.

[section Aviso]

Para usar esta funcionalidade, você deve adicionar o CGLib ao seu classpath.

Se você usa maven:

[xml]
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib-nodep</artifactId>
    <version>2.1_3</version>
</dependency>
[/xml]

Ou você pode fazer o download manualmente em http://repo2.maven.org/maven2/cglib/cglib-nodep/2.1_3/cglib-nodep-2.1_3.jar . 

[section Como Usar]

[java]
UmaClasse proxy = new Mirror().proxify(UmaClasse.class)
		.interceptingWith(new OSeuMethodInterceptor());
[/java]

O método ::interceptingWith:: recebe qualquer número de objetos que implementem
::net.vidageek.mirror.proxy.dsl.MethodInterceptor::. Quando um método for invocado no proxy, o primeiro
::MethodInterceptor:: que aceitar aquele método será usado para interceptar o método. 



