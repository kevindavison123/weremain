FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

RUN apt-get update

# idk if this is really the best place...
ENV APP weremainfund.org
ENV WEBROOT /srv/www/${APP}

RUN mkdir -p ${WEBROOT}
WORKDIR ${WEBROOT}
COPY . ${WEBROOT}

#install npm
RUN npm install -g angular-cli@latest
RUN npm install

EXPOSE 4200

CMD ["npm", "start" ]
