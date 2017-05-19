FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

ARG APP
ARG NODE_ENV
ARG PORT
ARG SECRET_DIR

ENV WEBROOT /srv/www/${APP}
ENV NODE_ENV ${NODE_ENV}
ENV PORT ${PORT}
ENV SECRET_DIR ${SECRET_DIR}

RUN echo 'deb http://ftp.debian.org/debian jessie-backports main' >> /etc/apt/sources.list
RUN apt-get update

WORKDIR ${WEBROOT}
COPY . ${WEBROOT}

RUN mkdir -p ${SECRET_DIR}

#install gcloud
RUN cd /tmp && curl -LO https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-155.0.0-linux-x86_64.tar.gz && tar -xzvf google-cloud-sdk* && cd google-cloud-sdk* && ./install.sh --quiet

COPY ./.kubernetes/auth/weremainfund-d7df3b0d1b10.json /root/

#install cerbot
RUN apt-get install -y certbot -t jessie-backports && mkdir -p ./.well-known/acme-challenge
RUN apt-get install -y cron

#install npm
RUN cd ${WEBROOT}; npm install

ENTRYPOINT ["./serve.sh"]
#ENTRYPOINT ["node", "dist/server/bin/www.js"]
#ENTRYPOINT ["sh", "-c", "while [ 1 ]; do sleep 120; done;"]
