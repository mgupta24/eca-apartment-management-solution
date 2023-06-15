#!/bin/bash

mi_tag=''
if [ -z "$1" ]
then
	mi_tag=latest
else 
	mi_tag=$1
	echo "$mi_tag"
fi
echo "docker tag $mi_tag"
mvn clean package -DskipTests
docker build -t singhvishab/eca-usermgmt:$mi_tag .

