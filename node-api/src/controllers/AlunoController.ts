import type { Request, Response, NextFunction } from 'express';
import type { AlunoService } from '../services/AlunoService';
import AppError from '../utils/AppErrors';
import z from "zod";


export class AlunoController{
    constructor(private readonly alunoSerivice: AlunoService){};

    private schemaLogin = z.object({
        cpf: z.string({message: "CPF é obrigatório"}).length(11),
        senha: z.string({message: "Senha é obrigatorio"}),
        email: z.string().email(),
    })

    listAlunoPorId = async(req: Request, res: Response, next: NextFunction) =>{
        try {
            const id = Number(req.params.id);
            if(!Number.isInteger(id) || id < 1){
                throw new AppError(400, "Parametro errado");
            }
            const alunos = await this.alunoSerivice.listarAlunoPorId(id);
            if(!alunos){
                throw new AppError(404, "Não encontrado")
            }
            res.json({ alunos });
        } catch (error) {
            next(error)
        }
    }

    loginAluno = async(req: Request, res: Response, next: NextFunction) => {
        try {
            const dados = this.schemaLogin.parse(req.body);
            const login = await this.alunoSerivice.loginAluno(dados);
            if(!login){
                throw new AppError(404, "Erro ao logar");
            }
            res.status(200).json({message: "Aluno logado"})
        } catch (error) {
            next(error)
        }
    }
}