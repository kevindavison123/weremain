#!/bin/bash

filename='aws.zip'

rm -f ${filename}

zip -r ${filename} Dockerfile
zip -r ${filename} *.json
zip -ur ${filename} *.js
zip -ur ${filename} src
zip -ur ${filename} e2e
zip -ur ${filename} .ebextensions
