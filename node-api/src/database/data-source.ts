import "reflect-metadata";
import "dotenv/config";
import { DataSource } from "typeorm";


const databaseHost = process.env.DATABASE_HOST || "db";
const databasePort = process.env.DATABASE_PORT || "3306";
const databaseUsername = process.env.DATABASE_USERNAME || "root";
const databasePassword = process.env.DATABASE_PASSWORD || "root";
const databaseName = process.env.DATABASE_NAME || "hackathon";


export const AppDataSource = new DataSource({
    type: "mysql",
    host: databaseHost,
    port: Number(databasePort),
    username: databaseUsername,
    password: databasePassword,
    database: databaseName,
    entities:["src/models/**/*.ts"],
    migrations: ["src/database/migrations/*.ts"],
    migrationsRun: true,
});