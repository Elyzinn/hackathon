import type { Request, Response, NextFunction } from 'express';
import type { CandidaturaService } from '../services/CandidaturaService';
import AppError from '../utils/AppErrors';
import z from "zod";


export class CandidaturaController {
    constructor(private readonly candidaturaService: CandidaturaService){};

    private schemaCandidatura = z.object({
        id: z.number().optional(),
        aluno_id: z.number({message: "Aluno ID é obrigatório"}),
        vagas_id: z.number({message: "Vaga ID é obrigatório"}),
        data_candidatura: z.date({message: "Data da candidatura é obrigatória"}),
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
}