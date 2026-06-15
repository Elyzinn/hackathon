import "reflect-metadata";
import "dotenv";
import express, {NextFunction, Request, Response} from 'express';
import cors from 'cors';
import { AppDataSource } from "./database/data-source";
import { errorMiddleware } from "./middlewares/ErrorMiddleware";
import alunoRouter from "./routes/alunosRouter";
import empresaRouter from "./routes/empresasRouter";

const server = express();
const PORT = process.env.PORT;
server.use(cors())

server.use(express.json());

server.use(alunoRouter);
server.use(empresaRouter);

server.use(errorMiddleware);

AppDataSource.initialize()
    .then(() => {
        console.log('Database connection has been initialized!');
    })
    .catch((err) => {
        console.error('Error during Data Source initialization:', err);
});

server.listen(Number(PORT), () => {
    console.log(`Server running on port ` + PORT);
});
