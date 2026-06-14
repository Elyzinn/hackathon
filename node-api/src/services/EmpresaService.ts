import * as bcrypt from "bcrypt";
import { IEmpresaRepository } from "../repositories/EmpresaRepository";
import {  EmpresaPublico, EmpresasEntity } from "../models/EmpresasEntity";
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
}