# Dependencias do Node:
## Verifique se as dependencias não instalaram dentro do container antes de executar comandos
#### Para incializar o package.json
- npm init - y
#### Instalar as dependencias 
- npm install 
    - express 
    - dotenv 
    - typeorm 
    - reflect-metadata 
    - zod 
    - cors 
    - tsx 
    - msql2

#### Instalar as dependencias de desenvolvimento (devDependecies)

- npm install -D 
    - @types/node
    - @types/express
    - @types/node
    - nodemon
    - ts-node
    - typescript      
# Configurção do TypeScript

- npx tsc --init
- npx ts-node src/server.ts