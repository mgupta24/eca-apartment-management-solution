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
docker push singhvishab/eca-discovery:$mi_tag


