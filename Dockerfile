FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

RUN apt-get update

# idk if this is really the best place...
ENV APP_NAME weremain
ENV SERVER_ROOT_PATH /srv/${APP_NAME}

RUN mkdir -p ${SERVER_ROOT_PATH}
WORKDIR ${SERVER_ROOT_PATH}
COPY . ${SERVER_ROOT_PATH}

#install npm
RUN npm install -g angular-cli@latest
RUN npm install

EXPOSE 4200

CMD ["npm", "start" ]
