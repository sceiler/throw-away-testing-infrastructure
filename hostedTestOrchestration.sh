!/bin/bash
export DOCKER_IMAGE=$DOCKER_USERNAME/photon-demo-java:$(date +%s)
docker build -t $DOCKER_IMAGE ./
docker push $DOCKER_IMAGE
saucectl run