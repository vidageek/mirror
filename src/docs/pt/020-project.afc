[chapter Projeto]

O Mirror é uma simples camada de DSL sobre a API de Reflection do Java, feita
para tornar o uso dessa poderosa ferramenta mais fácil. Sem as três mil exceções
lançadas na sua cara. Nem aquelas linhas de código completamente ilegíveis.

Simplicidade também é muito poderosa.

O projeto do Mirror começou por volta de Junho de 2008, implementando algo próximo
da idéia dada por um grande programador, Nico Steppat (http://www.caelum.com.br),
quando perguntado sobre como remover uma ReflectionUtil bem feia e cheia de métodos
estáticos do código de um projeto em que estávamos trabalhando.

[section Versionamento]

O Mirror usa um sistema bem simples de versionamento:

mirror-A.B.C.jar

[list]
* C - Correcões criticas de bugs.
* B - Adicão de funcionalidades e correcões de bugs.
* A - Muitas funcionalidades novas ou alteracão na API.
[/list]

Sendo o Mirror uma DSL, nós consideramos alteração na API como sendo qualquer
alteração na DSL. Nenhuma classe que é utilizada internamente pelo Mirror será
levada em consideração.

[section Release Notes]

[list]
* **Mirror 1.6.1 - 2011 March 19**

- Issue 54 - Proxyfy não funciona (BUGFIX)

* **Mirror 1.6 - 22 de Fevereiro de 2011**

- Issue 17 - Adicionar link para a documentação em outras linguas (BUGFIX)

- Issue 28 - instanciar sem chamar construtor (FEATURE)

- Issue 35 - Deve devolver MirrorList (FEATURE)

- Issue 36 - Marcar como deprecated fields/constructors/methods/annotationsMatching (FEATURE)

- Issue 38 - Implementar mapping (FEATURE)

- Issue 39 - MirrorList deve ser thread safe (FEATURE)

- Issue 43 - Mover Matcher para list.dsl (BUGFIX)

- Issue 44 - Adicionar método para refletir métodos com nome único (FEATURE)

- Issue 45 - Setar final fields (FEATURE)

- Issue 46 - Adicionar método para refletir construtor único (FEATURE)

- Issue 47 - Sun15ReflectionProvider (FEATURE)

- Issue 51 - Não ler configuração com java.io.File (BUGFIX)

- Issue 52 - Colocar a licensa em um lugar proeminente (BUGFIX)

- Issue 33 - Proxyfier parecido com o do VRaptor (FEATURE)

* **Mirror 1.5.1 - 04 de Agosto de 2009**

- Não encontra getter de field do tipo boolean (BUGFIX #40)

* **Mirror 1.5 - 24 de Julho de 2009**

- Não rodar static initializer (BUGFIX #19)

- Ler arquivo de configuração apenas uma vez (BUGFIX #32)

- Criar lista de discussão em português (FEATURE #29)

- Matchers para reflectAll() (FEATURE #30)

* **Mirror 1.4 - 5 de Junho de 2009**

- Adicionado suporte para invocação de setter/getter (FEATURE)

- Adicionado suporte para reflexão de setter/getter (FEATURE)

- Mirror é completamente baseado em interfaces. (FEATURE)

- net.vidageek.mirror.Mirror foi depreciado.. Use net.vidageek.mirror.dsl.Mirror no lugar. (DEPRECATION)

- Mudança de Mirror.on(AccessibleObject) para Mirror.on(AnnotatedElement) (BUGFIX)

- Busca por fields é feita em interfaces (BUGFIX)

- Lança IllegalArgumentException no lugar de NUllPointerException (BUGFIX)
 
* **Mirror 1.3 - 5 de Janeiro de 2009**

- Adicionado Reflection Providers

- Documentação em pt-BR

- Javadocs na maioria dos métodos e classes públicas

- Bugfixes 

* **Mirror 1.2 - 16 de Novembro de 2008**

- Primeiro release público
[/list]

[section Download]

Você pode acessar todos os lançamentos e códigos fonte disponíveis em 
http://maven.vidageek.net/net/vidageek/mirror ou http://github.com/vidageek/mirror/downloads .

Se você utiliza Maven, basta apenas adicionar as seguintes linhas
em seu pom.xml:

[xml]
<dependencies>
...
	<dependency>
	    <groupId>net.vidageek</groupId>
	    <artifactId>mirror</artifactId>
	    <version>1.6.1</version>
	</dependency>
...
</dependencies>
[/xml]

Ou até mesmo navegar e baixar os fontes no nosso repositório
localizado em http://github.com/vidageek/mirror/ .

[section BugTracker E Listas de E-mail]

[title Listas de E-mail]

[list]
* Users Mailing list: http://lista.vidageek.net/listinfo.cgi/mirror-users-pt-vidageek.net
** Dúvidas sobre o uso do mirror devem ser enviadas aqui.
* Development Mailing list: http://lista.vidageek.net/listinfo.cgi/mirror-dev-vidageek.net
** Discussões sobre novas funcionalidades e bugs.
* Commit Mailing list: http://lista.vidageek.net/listinfo.cgi/mirror-commit-vidageek.net
** Avisos de commits para os desenvolvedores (e qualquer pessoa que quer saber como o código
do mirror está mudando).
[/list]

[title Bugtracker]

Apenas acesse http://github.com/vidageek/mirror/issues e não se sinta envergonhado de criar um novo item!

[section Membros]

Atualmente os membros ativos no projeto são:

[list]
* Jonas Abreu (jonas [at] vidageek [dot] net)
* Adriano Almeida (adrianoalmeida7 [at] gmail [dot] com)
* Diego Feitosa (diego [at] dnfeitosa [dot] com)
* José Donizetti (jose.donizetti [at] caelum [dot] com [dot] br)
[/list]

[section Relatórios do Maven]

Você pode ver os relatórios do maven aqui (em inglês):

http://projetos.vidageek.net/mirror/maven

[section Javadoc]

Você pode ver o javadoc para este projeto aqui (em inglês):

http://projetos.vidageek.net/mirror/javadoc

[section Participe]

Você pode participar de várias maneiras. Simplesmente por usar o Mirror você já está nos ajudando.
Mas você sempre pode nos enviar patches (código-fonte, documentacão, etc) através do nosso bugtracker,
também respondendo a dúvidas de novos usuários através da nossa mailing list e também nos informando
sobre o seu uso.

Você também pode traduzir a documentacão do Mirror para sua língua. Será de enorme ajuda.
