#! /bin/bash

echo "installing Mirror so we can test things after the jar is built"

cd magic; mvn -Dmaven.test.skip clean install; cd ..;

mvn -Dmaven.test.skip -Ptests clean install

mvn test

cd packaged && mvn clean test;
