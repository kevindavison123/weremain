FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

ENV CONTAINER_PATH /var/www/weremain

COPY	. $CONTAINER_PATH
WORKDIR $CONTAINER_PATH

RUN npm install nodemon -g

EXPOSE 8080

ENTRYPOINT ["nodemon", "server.js"]
