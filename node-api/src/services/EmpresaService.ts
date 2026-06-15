import * as bcrypt from "bcrypt";
import { IEmpresaRepository } from "../repositories/EmpresaRepository";
import {  EmpresaPublico, EmpresasEntity } from "../models/EmpresasEntity";
import { EnderecoEmpresaEntity } from "../models/EnderecoEmpresaEntity";
import { EnderecoEmpresaRepository } from "../repositories/EnderecoEmpresaRepository";
import { EnderecoEmpresaService } from "./EnderecoEmpresaService";
import AppError from "../utils/AppErrors";

export class EmpresaService{
    constructor(private readonly empresaRepo: IEmpresaRepository){}

    async listarEmpresasPorId(id: number): Promise<EmpresaPublico>{
        const empresa = await this.empresaRepo.getEmpresaById(id);
        if(!empresa){
            throw new AppError(404, "Empresa não encontrada");
        }
        const { senha: _s, ...empresaPublico} = empresa;
        return empresaPublico;
    }

    async loginEmpresa(dados: {cnpj:string; senha: string; email:string}): Promise<EmpresasEntity>{
        const empresa = await this.empresaRepo.getEmpresaByCnpj(dados.cnpj);
        if(!empresa){
            throw new AppError(404, "Empresa não encontrada");
        }
        const senhaVerficada = await bcrypt.compare(dados.senha, empresa.senha);
        if(senhaVerficada == false){
            throw new AppError(401, "Credenciais erradas");
        }
        return empresa;
    }

    async cadastrarEmpresa(dados: {razao_social: string, cnpj:string; senha: string; email: string; telefone_contato: string; responsavel: string; status: boolean; endereco_empresa: number}): Promise<EmpresasEntity>{
        const senhaCriptografada = await bcrypt.hash(dados.senha, 10);
        const dadosComSenhaCriptografada = { ...dados, senha: senhaCriptografada };
        const cadastroEmpresa = await this.empresaRepo.createEmpresa(dadosComSenhaCriptografada);
        return cadastroEmpresa;
    }
}