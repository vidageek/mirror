[chapter Annotations]

Manipulação de anotações com o Mirror.

[section Refletindo]

Você pode facilmente refletir todas as anotações de uma classe:

[java]
Class<T> clazz;
List<Annotation> annotations = new Mirror().on(clazz).reflectAll()
									.annotations().atClass();
[/java]

Da mesma forma que você também pode facilmente refletir todas as anotações de
um field ou de um método:

[java]
Class<T> clazz;
List<Annotation> methodAnnotations = new Mirror().on(clazz).reflectAll()
							.annotations().atMethod("nomeMetodo").withoutArgs();
							
List<Annotation> fieldAnnotations = new Mirror().on(clazz).reflectAll()
							.annotations().atField("nomeField");
[/java]

Também é possível recuperar um valor de uma propriedade da anotação,
como no código seguinte:

[java]
FooAnnotation annotation = new Mirror().on(clazz).reflect()
									.annotation(FooAnnotation.class).atClass();
String valor = annotation.aProperty();
[/java]

E também funciona para anotações em fields e métodos: 

[java]
FooAnnotation annotation = new Mirror().on(clazz).reflect()
						.annotation(FooAnnotation.class).atField("nomeField");
String valor = annotation.aProperty();

FooAnnotation annotation = new Mirror().on(clazz).reflect()
						.annotation(FooAnnotation.class)
						.atMethod("nomeMetodo").withoutArgs();
String valor = annotation.aProperty();
[/java]