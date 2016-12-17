FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

RUN apt-get update
RUN npm install

EXPOSE 3000

ENTRYPOINT ["node", "server.js"]
