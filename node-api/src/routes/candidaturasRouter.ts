import { Router } from "express";
import { AppDataSource } from "../database/data-source";
import { CandidaturasEntity } from "../models/CandidaturasEntity";
import { CandidaturasRepository } from "../repositories/CandidaturaRepository";
import { CandidaturaService } from "../services/CandidaturaService";
import { CandidaturaController } from "../controllers/CandidaturaController";

const candidaturaRouter = Router();

const candidaturaRepository = new CandidaturasRepository(AppDataSource.getRepository(CandidaturasEntity));
const candidaturaService = new CandidaturaService(candidaturaRepository);
const candidaturaController = new CandidaturaController(candidaturaService);

candidaturaRouter.post("/criarCandidaturas", candidaturaController.realizarCandidatura);

export default candidaturaRouter    ;