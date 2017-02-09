FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

# idk if this is really the best place...
ENV APP development.org
ENV WEBROOT /srv/www/${APP}

RUN \
  apt-get update && \
### node-sass needs this for idk why
  ln -s /usr/bin/nodejs /usr/bin/node && \
  mkdir -p ${WEBROOT}

WORKDIR ${WEBROOT}
COPY . ${WEBROOT}

#install npm
RUN \
  npm install -g angular-cli@latest && \
  npm install -g typescript && \
### don't need this for 'production'
#  npm install -g nodemon && \
  npm install && \
  npm run build

ENV NODE_ENV production
ENV PORT 4200
EXPOSE 4200

ENTRYPOINT ["node", "dist/server/bin/www.js"]
