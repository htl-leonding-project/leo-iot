##STAGE 0, building sources
FROM node:dubnium as build-stage

#setting workdir
WORKDIR /app

#Copying package.json and lock file
COPY package*.json ./

#Installing Dependecies
RUN npm install

#Copying source files
COPY . ./

#building the sources
RUN npm run build-i18n

##STAGE 1, serving production build (exposing default port 80)
FROM nginx:stable-alpine

#Serving the production build with nginx
COPY --from=build-stage /app/dist/iot-webclient /usr/share/nginx/html

#Applying custom nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

#Starting on localhost:80
CMD ["nginx", "-g", "daemon off;"]
