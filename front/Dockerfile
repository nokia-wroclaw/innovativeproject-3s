# base image
FROM node:12.2.0 as build-node

WORKDIR /front
RUN npm install
COPY ./default.conf  /front/
COPY . .

RUN npm run build --prod

FROM nginx:1.15

#source paths depend on your config and project structure
COPY --from=build-node /front/dist/front/ /usr/share/nginx/html
COPY --from=build-node /front/ /etc/nginx/conf.d/

EXPOSE 4200
