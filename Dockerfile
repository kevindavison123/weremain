FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

ARG APP
ARG PORT

ENV PORT ${PORT}
EXPOSE ${PORT}
ENV WEBROOT /srv/www/${APP}

RUN \
  apt-get update && \
  mkdir -p ${WEBROOT}

WORKDIR ${WEBROOT}
COPY . ${WEBROOT}

#install npm
RUN \
  npm install && \
  npm run build

ENV NODE_ENV production

ENTRYPOINT ["node", "dist/server/bin/www.js"]
#ENTRYPOINT ["sh", "-c", "while [ 1 ]; do sleep 120; done;"]
