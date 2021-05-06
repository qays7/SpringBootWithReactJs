#!/bin/bash 

export PATH=/Users/mohamedemara/MyWork/apache-maven-3.6.2/bin:$PATH

NAME=mohamedemara/oasisplusapp:nursing-services

echo "Attemp To Generate ${NAME}" 

echo "Attemp to jar files " 

mvn package

echo "Attemp to docker file "

LINE=$(head -n 1 Dockerfile)

echo $LINE

OLD_VERSION="${LINE/\#version=/}"

NEW_VERSION="$(($OLD_VERSION + 1))"

FILE="Dockerfile"

sed -i '' "1s/.*/#version=$NEW_VERSION/" $FILE

echo $NEW_VERSION

NAME="$NAME"_"$NEW_VERSION"

echo $NAME

sed -i '' "2s~.*~#$NAME~" $FILE

docker build -t $NAME .

docker push $NAME

echo "docker success"