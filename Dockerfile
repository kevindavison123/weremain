FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

ARG APP
ARG NODE_ENV
ARG PORT

ENV WEBROOT /srv/www/${APP}
ENV NODE_ENV ${NODE_ENV}
ENV PORT ${PORT}

RUN apt-get update

WORKDIR ${WEBROOT}
COPY package.json ${WEBROOT}

#install npm
RUN npm install

COPY . ${WEBROOT}

ENTRYPOINT ["./serve.sh"]
#ENTRYPOINT ["node", "dist/server/bin/www.js"]
#ENTRYPOINT ["sh", "-c", "while [ 1 ]; do sleep 120; done;"]
