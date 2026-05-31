FROM node:24-alpine

WORKDIR /var/www/html

COPY package*.json ./
COPY .env /.env
COPY . .

RUN npm install

COPY . /var/www/html/

CMD ["npm", "run", "dev"]