# from the "correct" docker-ubuntu image
FROM phusion/baseimage
MAINTAINER Kyle Davison <kdavison@gmail.com>
# UPDATE
RUN apt-get update

# TOOLS
RUN apt-get install -y build-essential
RUN apt-get install -y curl

# NODE.JS
RUN curl -sL https://deb.nodesource.com/setup_7.x | bash -
RUN apt-get install -y nodejs

# CREATE APP DIRECTORY
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# INSTALL APP DEPENDENCIES
COPY package.json /usr/src/app/
RUN npm install

# BUNDLE APP SOURCE
COPY . /usr/src/app

EXPOSE 8080
CMD [ "npm", "start" ]
