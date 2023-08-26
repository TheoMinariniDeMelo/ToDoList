FROM node:latest


WORKDIR /app


COPY . .

RUN npm update && npm install @angular-cli

ENTRYPOINT [ "ng", "serve" ]


CMD [ "--host", "4200" ]