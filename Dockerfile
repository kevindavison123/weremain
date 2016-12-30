FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

RUN apt-get update

RUN mkdir /weremain
WORKDIR /weremain
COPY . /weremain

RUN npm install

EXPOSE 4200

ENTRYPOINT ["node", "server.js"]
