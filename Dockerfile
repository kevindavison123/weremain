FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

# idk if this is really the best place...
ARG APP
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
ENV PORT 4200
EXPOSE 4200

ENTRYPOINT ["node", "dist/server/bin/www.js"]
#ENTRYPOINT ["sh", "-c", "while [ 1 ]; do sleep 120; done;"]
