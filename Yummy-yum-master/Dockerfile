FROM node:16
WORKDIR /usr/src/app
COPY . .
RUN npm cache clean --force
RUN npm install --no-optional
ENTRYPOINT ["npm","start"]