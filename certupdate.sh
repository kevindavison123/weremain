#!/bin/bash

PREVIOUS_DIR=/tmp/.previous
mkdir -p ${PREVIOUS_DIR}

KUBECTL=/tmp/google-cloud-sdk/bin/kubectl
SECRET_NAME=weremain-cert
STORAGE=${CERTIFICATE_STORAGE}/${APPLICATION}

#
# ${KUBECTL} create secret generic ${SECRET_NAME} \
#     --from-file=tls.crt=${CERTIFICATE_STORAGE}/fullchain.pem \
#     --from-file=tls.key=${CERTIFICATE_STORAGE}/privkey.pem \
#     --output yaml \
#     --dry-run \
#     > secret.yaml
#
# ${KUBECTL} apply -f secret.yaml
#

FILELIST=(privkey.pem fullchain.pem chain.pem cert.pem)

COUNT_CHECK=0
for file in "${FILELIST[@]}"
do
  if [ -e "${STORAGE}/${file}" ]
  then
    COUNT_CHECK=$((COUNT_CHECK+1))    
  fi
done

#make sure all the files are present, else something dumb might happen
UPDATE_REQUIRED=0
if [ "${COUNT_CHECK}" -ne 4 ]
then
  exit 1
else
  for file in "${FILELIST[@]}"
  do
    current_content=($(md5sum "${STORAGE}/${file}"))
    if [ -e "${PREVIOUS_DIR}/${file}.previous" ]
    then  
      previous_content=$(cat "${PREVIOUS_DIR}/${file}.previous")

      if [ "${current_content}" != "${previous_content}" ]
      then
        echo "${file} changed ${current_content}  --  ${previous_content}"
        UPDATE_REQUIRED=$((UPDATE_REQUIRED+1))
      fi
    else
      echo "new entry ${file}"
      UPDATE_REQUIRED=$((UPDATE_REQUIRED+1))
    fi
  done
fi

#if 4/4 items need to be updated then do the update
if [ "${UPDATE_REQUIRED}" -eq 4 ]
then
  for file in "${FILELIST[@]}"
  do
    current_content=($(md5sum "${STORAGE}/${file}"))
    echo "${current_content}" > "${PREVIOUS_DIR}/${file}.previous"
  done

#use kubectl to create the secret
#  ${KUBECTL} create secret generic ${SECRET_NAME} \
#    --from-file=tls.key=${STORAGE}/privkey.pem \
#    --from-file=tls.crt=${STORAGE}/fullchain.pem \
#    --output yaml \
#    --dry-run \
#    > /tmp/secret.yaml

#update the secret
#  ${KUBECTL} apply -f /tmp/secret.yaml

#delete the secret
#  mv /tmp/secret.yaml /tmp/secret.yaml.previous
fi

