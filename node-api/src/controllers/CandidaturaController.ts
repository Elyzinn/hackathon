import type { Request, Response, NextFunction } from 'express';
import type { CandidaturaService } from '../services/candidaturaService';
import AppError from '../utils/AppErrors';
import z from "zod";


export class CandidaturaController {
    constructor(private readonly candidaturaService: CandidaturaService){};

    private schemaCandidatura = z.object({
        id: z.number().optional(),
        aluno_id: z.number({message: "Aluno ID é obrigatório"}),
        vagas_id: z.number({message: "Vaga ID é obrigatório"}),
        data_candidatura: z.coerce.date({message: "Data da candidatura é obrigatória"}),
    })

    realizarCandidatura = async(req: Request, res: Response, next: NextFunction) =>{
        try {
            const dados = this.schemaCandidatura.parse(req.body);
            const candidaturas = await this.candidaturaService.createCandidatura(dados);
            if(!candidaturas){
                throw new AppError(400, "Erro ao realizar candidatura")
            }
            res.status(201).json({message: "Candidatura criada", candidaturas});
        } catch (error) {
            next(error)
        }
    }

    listarCandidaturasPorAluno = async(req: Request, res: Response, next: NextFunction) => {
        try {
            const id = Number(req.params.id);
            if(!Number.isInteger(id) || id < 1){
                throw new AppError(400, "Parametros errados");
            }
            const candidaturas = await this.candidaturaService.listarCandidaturasPorAluno(id);
            if(!candidaturas){
                throw new AppError(404, "Nada encontrado")
            }
            res.json({candidaturas});
        } catch (error) {
            next(error)
        }
    }

    listarCandidaturasPorEmpresa = async(req: Request, res: Response, next: NextFunction) => {
        try {
            const id = Number(req.params.id);
            if(!Number.isInteger(id) || id < 1){
                throw new AppError(400, "Parametros errados");
            }
            const candidaturas = await this.candidaturaService.listarCandidaturasPorEmpresa(id);
            if(!candidaturas){
                throw new AppError(404, "Nada encontrado")
            }
            res.json({candidaturas});
        } catch (error) {
            next(error)
        }
    }
}