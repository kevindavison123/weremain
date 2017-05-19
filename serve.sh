#!/bin/bash

#install gcloud bash + get kubectl
cd /tmp/google-cloud-sdk*
  source path.bash.inc
  source completion.bash.inc
cd -
gcloud components update && \
gcloud components install kubectl --quiet

#setup gcloud permissions
cd /root
  gcloud auth activate-service-account --key-file weremainfund-d7df3b0d1b10.json && \
  gcloud config set compute/zone us-east1-d && \
  gcloud config set project black-network-156417 && \
  gcloud container clusters get-credentials wrf-us-east1
cd -

##setup cron
service cron start
COMMAND='echo "test" >> ${HOME}/log.txt'
echo "*/5 * * * * ${COMMAND}" | crontab -

if [ ! -z "${NODE_ENV}" ] && [ "${NODE_ENV}" = "production" ]
then
##production intent
#  echo "PRODUCTION"
  npm run build
  node dist/server/bin/www.js
else
##default to development environment
#  echo "DEFAULT"
  npm run build
  npm run _server:run
fi


