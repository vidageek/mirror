#! /bin/bash

echo "installing Mirror so we can test things after the jar is built"

mvn -Dmaven.test.skip -Ptest clean install

mvn test

cd packaged && mvn clean test;
