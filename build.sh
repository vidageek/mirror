#! /bin/bash

if test -d ~/.m2/repository/br/com/caelum/tubaina/
then
	echo "Tubaina ja encontrado na maquina, pulando a instalacao"
else
	echo "Tubaina nao esta na maquina... instalando"
	git clone git://github.com/caelum/tubaina.git
	cd tubaina
	mvn install:install-file -DgroupId=org.w3c.tidy -DartifactId=Tidy -Dversion=1.0 -Dpackaging=jar -Dfile=lib/Tidy.jar
	mvn install:install-file -DgroupId=com.sun.media -DartifactId=jai_codec -Dversion=1.1.2_01 -Dpackaging=jar -Dfile=lib/jai_codec.jar
	mvn install:install-file -DgroupId=com.sun.media -DartifactId=jai_imageio -Dversion=1.0_01 -Dpackaging=jar -Dfile=lib/jai_imageio.jar
    mvn install:install-file -DgroupId=javax.media -DartifactId=jai_core -Dversion=1.1.2_01 -Dpackaging=jar -Dfile=lib/jai_core.jar
	mvn install -Dmaven.test.skip
	cd ..
	rm -rf tubaina
fi

if test -d ~/.m2/repository/br/com/caelum/tubaina-maven-plugin/
then
	echo "TubainaMavenPlugin ja encontrado na maquina, pulando a instalacao"
else
	echo "TubainaMavenPlugin nao esta na maquina... instalando"
	svn co https://tubaina.svn.sourceforge.net/svnroot/tubaina/tubaina-maven-plugin tubaina-maven-plugin
	cd tubaina-maven-plugin
	mvn install -Dmaven.test.skip
	cd ..
	rm -rf tubaina-maven-plugin
fi
echo "Agora rodando os testes"
mvn clean test