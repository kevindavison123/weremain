#!/bin/bash

if [ ! -z "${NODE_ENV}" ] && [ "${NODE_ENV}" = "production" ]
then
##production intent
#  echo "PRODUCTION"
  node dist/server/bin/www.js > /tmp/log.txt
else
##default to development environment
#  echo "DEFAULT"
  npm run _server:run
fi


