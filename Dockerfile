FROM node:latest

MAINTAINER Kyle Davison <kdavison@gmail.com>

RUN apt-get update

# idk if this is really the best place...
RUN mkdir -p /usr/src/weremain
WORKDIR /usr/src/weremain

# Install application dependencies
COPY package.json /usr/src/weremain/
RUN npm install

COPY . /usr/src/weremain

EXPOSE 4200

CMD ["npm", "start" ]
