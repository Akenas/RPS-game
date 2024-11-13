# Stage 1: Build the Angular application
FROM node:18 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: Serve with Nginx
FROM nginx:alpine
# Remove default Nginx configurations
COPY --from=build /app/dist/rps-front/browser /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf
