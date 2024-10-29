#!/bin/bash

DIR=$(pwd)
MAVEN=${DIR}/mvnw
VERSION="12.2.1.3.0"
GROUP="com.oracle.wcsites"

for JAR in ${DIR}/*.jar; do
	FILE=$(basename $JAR)
	ARTIFACT=${FILE%.*}
	${MAVEN} install:install-file -Dfile=${JAR} -DgroupId=c${GROUP} -DartifactId=${ARTIFACT} -Dversion=${VERSION} -Dpackaging=jar
done

$MAVEN clean install

