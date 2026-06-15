import type { Request, Response, NextFunction } from 'express';
import type { CandidaturaService } from '../services/CandidaturaService';
import AppError from '../utils/AppErrors';
import z from "zod";


export class CandidaturaController {
    constructor(private readonly candidaturaService: CandidaturaService){};

    private schemaLogin = z.object({
        cpf: z.string({message: "CPF é obrigatório"}).length(11),
        senha: z.string({message: "Senha é obrigatorio"}),
        email: z.string().email(),
    })

    realizarCandidatura = async(req: Request, res: Response, next: NextFunction) =>{
        try {
            const id = Number(req.params.id);
            if(!Number.isInteger(id) || id < 1){
                throw new AppError(400, "Parametro errado");
            }
            const candidaturas = await this.candidaturaService.realizarCandidatura(id);
            if(!candidaturas){
                throw new AppError(404, "Não encontrado")
            }
            res.json({ candidaturas });
        } catch (error) {
            next(error)
        }
    }

    loginAluno = async(req: Request, res: Response, next: NextFunction) => {
        try {
            const dados = this.schemaLogin.parse(req.body);
            const login = this.alunoSerivice.loginAluno(dados);
            if(!login){
                throw new AppError(404, "Erro ao logar");
            }
            res.status(200).json({message: "Aluno logado"})
        } catch (error) {
            next(error)
        }
    }
}