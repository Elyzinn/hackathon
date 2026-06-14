import { Router } from "express";
import { AppDataSource } from "../database/data-source";
import { EmpresasEntity } from "../models/EmpresasEntity";
import { EmpresaRepository } from "../repositories/EmpresaRepository";
import { EmpresaService } from "../services/EmpresaService";
import { EmpresaController } from "../controllers/EmpresaController";

const empresaRouter = Router();

const empresaRepository = new EmpresaRepository(AppDataSource.getRepository(EmpresasEntity));
const empresaService = new EmpresaService(empresaRepository);
const empresaController = new EmpresaController(empresaService);

empresaRouter.get("/empresas/:id", empresaController.listEmpresaPorId);
empresaRouter.post("/empresaLogin", empresaController.loginEmpresa);

export default empresaRouter;