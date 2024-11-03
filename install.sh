#!/bin/bash

JSK_HOME=$1
if [ -z "$JSK_HOME" ]; then
	echo "Usage: $0 <JSK_HOME>"
	exit 1
fi

if [ ! -d "$JSK_HOME" ]; then
	echo "Directory $JSK_HOME does not exist"
	exit 1
fi

LIB_DIR="${JSK_HOME}/apache-tomcat-7.0.65-sites/webapps/sites/WEB-INF/lib"
if [ ! -d "$LIB_DIR" ]; then
	echo "$LIB_DIR does not exist"
	exit 1
fi

DIR=$(pwd)
MAVEN=${DIR}/mvnw
if [[ ! (-f "$MAVEN" && -x "$MAVEN") ]]; then
	echo "Maven wrapper not found"
	exit 1
fi

VERSION="12.2.1.3.0"
GROUP="com.oracle.wcsites"

declare -a JARS=("sites-app" "sites-asset-api" "sites-rest-api")

for JAR in ${JARS[@]}; do
    ${MAVEN} install:install-file -Dfile="${LIB_DIR}/${JAR}.jar" -DgroupId=c${GROUP} -DartifactId=${JAR} -Dversion=${VERSION} -Dpackaging=jar
done

$MAVEN clean install

