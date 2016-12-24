#!/bin/bash

filename='aws.zip'
  
zip -r ${filename} *.json
zip -ur ${filename} *.js
zip -ur ${filename} src