FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

RUN apt-get update
RUN npm install nodemon -g

RUN mkdir /weremain
WORKDIR /weremain
COPY . /weremain
RUN npm install

EXPOSE 3000

ENTRYPOINT ["nodemon", "server.js"]
