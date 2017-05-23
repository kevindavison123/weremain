FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

ARG APP
ARG HTTP_PORT
ARG HTTPS_PORT
ARG NODE_ENV
ARG ADMIN_EMAIL

ENV WEBROOT /srv/www/${APP}
ENV HTTP_PORT ${HTTP_PORT}
ENV HTTPS_PORT ${HTTPS_PORT}
ENV NODE_ENV ${NODE_ENV}
ENV ADMIN_EMAIL ${ADMIN_EMAIL}
ENV CHALLENGE_WEBROOT /tmp/.well-known/acme-challenge


RUN \
  echo 'deb http://ftp.debian.org/debian jessie-backports main' >> /etc/apt/sources.list && \
  apt-get update && \
  apt-get install -y \
    certbot -t jessie-backports

WORKDIR ${WEBROOT}
COPY . ${WEBROOT}

RUN mkdir -p /tmp/.well-known/acme-challenge

#install gcloud
RUN cd /tmp && curl -LO https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-155.0.0-linux-x86_64.tar.gz && tar -xzvf google-cloud-sdk* && cd google-cloud-sdk* && ./install.sh --quiet && cd /tmp && rm -rf google-cloud-sdk*.tar.gz && cd /

COPY ./.kubernetes/auth/weremainfund-d7df3b0d1b10.json /root/

#install npm
RUN cd ${WEBROOT}; npm install

ENTRYPOINT ["./serve.sh"]
#ENTRYPOINT ["node", "dist/server/bin/www.js"]
#ENTRYPOINT ["sh", "-c", "while [ 1 ]; do sleep 120; done;"]
