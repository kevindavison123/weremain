FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

# idk if this is really the best place...
ENV APP weremainfund.org
ENV WEBROOT /srv/www/${APP}

RUN \
  apt-get update && \
  mkdir -p ${WEBROOT}

WORKDIR ${WEBROOT}
COPY . ${WEBROOT}

#install npm
RUN \
  npm install -g angular-cli@latest && \
  npm install

EXPOSE 4200

CMD ["npm", "start" ]
