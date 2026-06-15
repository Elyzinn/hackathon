import { Router } from "express";
import { AppDataSource } from "../database/data-source";
import { ContratosEntity } from "../models/ContratosEntity";
import { ContratosRepository } from "../repositories/ContratosRepository";
import { ContratoService } from "../services/ContratoService";
import { ContratoController } from "../controllers/ContratoController";


const contratoRouter = Router();

const contratoRepository = new ContratosRepository(AppDataSource.getRepository(ContratosEntity));
const contratoService = new ContratoService(contratoRepository);
const contratoController = new ContratoController(contratoService);

contratoRouter.post("/criarContratos", contratoController.criarContrato);
contratoRouter.get("/contratos", contratoController.listarContratos);
  
export default contratoRouter;