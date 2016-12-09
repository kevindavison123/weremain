FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

ENV CONTAINER_PATH /var/www/weremain

WORKDIR $CONTAINER_PATH

RUN npm install nodemon -g

EXPOSE 3000

ENTRYPOINT ["nodemon", "server.js"]
