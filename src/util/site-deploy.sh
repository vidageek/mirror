#! /bin/bash -i

ROOT=$PWD/../..;


SITE_DIR=$ROOT/target/tubaina/html/mirror;

SITE_DIR_PT=$ROOT/target/tubaina/html/mirror-pt;

REPORTS_DIR=$ROOT/target/site;

UTIL_DIR=$ROOT/src/util;

JAVADOC_DIR=$ROOT/target/site/apidocs;

SERVER=vidageek@vidageek.net:/home/vidageek/projetos.vidageek.net/mirror/;

cd $ROOT;

mvn clean -o;

#Generate english version of Mirror's site
mvn tubaina:build -o;

#Generate portuguese version of Mirror's site
mvn -Ppt tubaina:build -o;

#Generate javadoc for classes
mvn javadoc:javadoc -o;

#generate maven reports
mvn site -o;

#copy redirect file (Needed due to tubaina's structure)
cp $UTIL_DIR/index.html $SITE_DIR/.;

#copy portuguese redirect file
cp $UTIL_DIR/indexpt.html $SITE_DIR_PT/index.html;

#copy javadocs to it's place
mkdir $SITE_DIR/javadoc;
cp -R $JAVADOC_DIR/* $SITE_DIR/javadoc;

#copy maven files to it's place
mkdir $SITE_DIR/maven;
cp -R $REPORTS_DIR/* $SITE_DIR/maven/.;

#copy files to server
scp -rC $SITE_DIR/* $SERVER/.;
scp -rC $SITE_DIR_PT/ $SERVER/../.;

echo "Please check the site and make sure everything is ok!";
echo "Site deploy successful";
