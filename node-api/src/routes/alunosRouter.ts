import { Router } from "express";
import { AppDataSource } from "../database/data-source";
import { AlunosEntity } from "../models/AlunosEntity";
import { AlunoRepository } from "../repositories/AlunoRepository";
import { AlunoService } from "../services/AlunoService";
import { AlunoController } from "../controllers/AlunoController";

const alunoRouter = Router();

const alunoRepository = new AlunoRepository(AppDataSource.getRepository(AlunosEntity));
const alunoService = new AlunoService(alunoRepository);
const alunoController = new AlunoController(alunoService);

alunoRouter.get("/alunos/:id", alunoController.listAlunoPorId);
alunoRouter.post("/alunoLogin", alunoController.loginAluno);

export default alunoRouter;