import type { Request, Response, NextFunction } from 'express';
import AppError from '../utils/AppErrors';
import z from "zod";
import { EmpresaService } from '../services/EmpresaService';


export class EmpresaController{
    constructor(private readonly empresaService: EmpresaService){};

    private schemaLogin = z.object({
        cnpj: z.string({message: "CNPJ é obrigatório"}).length(14),
        senha: z.string({message: "Senha é obrigatorio"})
        .regex(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/)
        .regex(/[0-9]/),
        email: z.string().email(),
    })

    listEmpresaPorId = async(req: Request, res: Response, next: NextFunction) =>{
        try {
            const id = Number(req.params.id);
            if(!Number.isInteger(id) || id < 1){
                throw new AppError(400, "Parametro errado");
            }
            const empresas = await this.empresaService.listarEmpresasPorId(id);
            if(!empresas){
                throw new AppError(404, "Não encontrado")
            }
            res.json({ empresas });
        } catch (error) {
            next(error)
        }
    }

    loginEmpresa = async(req: Request, res: Response, next: NextFunction) => {
        try {
            const dados = this.schemaLogin.parse(req.body);
            const login = this.empresaService.loginEmpresa(dados);
            if(!login){
                throw new AppError(404, "Erro ao logar");
            }
            res.status(200).json({message: "Empresa logada"})
        } catch (error) {
            next(error)
        }
    }
}