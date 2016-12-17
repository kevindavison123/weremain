FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

RUN apt-get update

RUN mkdir /weremain
WORKDIR /weremain
COPY . /weremain

RUN npm install

EXPOSE 80

ENTRYPOINT ["node", "server.js"]
