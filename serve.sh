#!/bin/bash

if [ ! -z "${NODE_ENV}" ] && [ "${NODE_ENV}" = "production" ]
then
##production intent
#  echo "PRODUCTION"
  npm run build
  node dist/server/bin/www.js
else
##default to development environment
#  echo "DEFAULT"
  npm run start
fi
