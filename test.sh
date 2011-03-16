#! /bin/bash

echo "packaging Mirror so we can test things after the jar is built"

mvn -Dmaven.test.skip clean package

mvn test
