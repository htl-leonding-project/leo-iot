#!/usr/bin/env bash

#while ! nc -z mysql 3306; do
#    echo "waiting for godot..."
#    sleep 1
#done
#sleep 2
echo "starting quarkus..."
pwd
ls -l
./application -Dquarkus.http.host=0.0.0.0
