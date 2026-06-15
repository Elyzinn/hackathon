import { Router } from "express";
import { AppDataSource } from "../database/data-source";
import { VagasEntity } from "../models/VagasEntity";
import { VagasRepository } from "../repositories/VagasRepository";
import { VagasService } from "../services/VagasService";
import { VagasController } from "../controllers/VagasController";

const vagasRouter = Router();

const vagasRepository = new VagasRepository(AppDataSource.getRepository(VagasEntity));
const vagasService = new VagasService(vagasRepository);
const vagasController = new VagasController(vagasService);

vagasRouter.get("/vagas", vagasController.listarVagas);
vagasRouter.post("/criarVagas", vagasController.CriarVagas);

export default vagasRouter;