#!/bin/bash
export PORT=4200
export NODE_ENV=production

echo "remove active dockers"
docker rm $(docker ps -aq) -f # || exit 1

echo "remove docker images"
docker rmi $(docker images -aq) -f # || exit 1

echo "build the docker image"
docker-compose build --no-cache || exit 1

echo "push it to the cloud"
gcloud docker -- push gcr.io/black-network-156417/weremainfund.org || exit 1

echo "create the local deployment file"
kubectl run weremainfund --image=gcr.io/black-network-156417/weremainfund.org:latest --port=4200 --output yaml --dry-run > deployment.yaml

echo "delete current deployment"
#kubectl delete deployment weremainfund
sleep 2

echo "deploy the new image"
#kubectl create -f deployment.yaml

