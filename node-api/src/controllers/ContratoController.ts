import type { Request, Response, NextFunction } from 'express';
import type { ContratoService} from '../services/ContratoService';
import AppError from '../utils/AppErrors';
import z from "zod";


export class ContratoController{
    constructor(private readonly contratoService: ContratoService){};

    private schemaContrato = z.object({
        id: z.number().optional(),
        vagas_id: z.number({message: "Vaga ID é obrigatório"}),
        aluno_id: z.number({message: "Aluno ID é obrigatório"}),
        date_inicio: z.date({message: "Data de início é obrigatória"}),
        data_fim: z.date({message: "Data de fim é obrigatória"}),
    })

    criarContrato = async(req: Request, res: Response, next: NextFunction) =>{
        try {
            const dados = this.schemaContrato.parse(req.body);
            const contrato = await this.contratoService.createContrato(dados);
            res.status(201).json({message: "Contrato criado com sucesso", contrato});
        } catch (error) {
            next(error)
        }
    }

    listarContratos = async(req: Request, res: Response, next: NextFunction) =>{
         try {
            const contratos = await this.contratoService.listarContratosAll();
            res.json({ contratos });
        } catch (error) {
            next(error)
        }
    }

}