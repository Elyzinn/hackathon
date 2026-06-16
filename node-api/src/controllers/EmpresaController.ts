import type { Request, Response, NextFunction } from 'express';
import AppError from '../utils/AppErrors';
import z from "zod";
import { EmpresaService } from '../services/EmpresaService';


export class EmpresaController {
    constructor(private readonly empresaService: EmpresaService) { };

    private schemaLogin = z.object({
        cnpj: z.string({ message: "CNPJ é obrigatório" }).length(14),
        senha: z.string({ message: "Senha é obrigatorio" }),
        email: z.string().email(),
    })
    private schemaCadastrar = z.object({
        razao_social: z.string({ message: "Razão social é obrigatória" }).max(100),
        cnpj: z.string({ message: "CNPJ é obrigatório" }).length(14),
        email: z.string().email(),
        telefone_contato: z.string().min(10).max(11),
        responsavel: z.string().max(100),
        senha: z.string({ message: "Senha é obrigatorio" })
            .regex(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/),
        cep: z.string().length(8),
        logradouro: z.string().max(100),
        numero: z.string().max(10),
        bairro: z.string().max(50),
        cidade: z.string().max(50),
        uf: z.string().length(2),
    });

    listEmpresaPorId = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const id = Number(req.params.id);
            if (!Number.isInteger(id) || id < 1) {
                throw new AppError(400, "Parametro errado");
            }
            const empresas = await this.empresaService.listarEmpresasPorId(id);
            if (!empresas) {
                throw new AppError(404, "Não encontrado")
            }
            res.json({ empresas });
        } catch (error) {
            next(error)
        }
    }

    loginEmpresa = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const dados = this.schemaLogin.parse(req.body);
            const login = await this.empresaService.loginEmpresa(dados);
            if (!login) {
                throw new AppError(404, "Erro ao logar");
            }
            const { senha, ...loginSemSenha } = login;
            res.status(200).json({ message: "Empresa logada", loginSemSenha })
        } catch (error) {
            next(error)
        }
    }

    cadastrarEmpresa = async (req:Request, res: Response, next: NextFunction) => {
        try {
            const dados = this.schemaCadastrar.parse(req.body);
            await this.empresaService.criarEmpresa(dados);
            res.status(201).json({ message: "Empresa cadastrada com sucesso!"})
        } catch (error) {
            next(error);
        }
        }
    }

