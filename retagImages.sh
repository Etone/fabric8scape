#! /bin/bash

docker tag admin:0.0.1-SNAPSHOT etone/fabric8scape-admin:latest
docker tag registry:0.0.1-SNAPSHOT etone/fabric8scape-registry:latest
docker tag landscaper:0.0.1-SNAPSHOT etone/fabric8scape-landscaper:latest

docker push etone/fabric8scape-landscaper:latest
docker push etone/fabric8scape-admin:latest
docker push etone/fabric8scape-registry:latest