
import { IContratosRepository } from "../repositories/ContratosRepository";
import { ContratosEntity } from "../models/ContratosEntity";
import AppError from "../utils/AppErrors";
import { CandidaturasEntity } from "../models/CandidaturasEntity";


export class ContratoService {
    constructor(private readonly contratosRepo: IContratosRepository){}

    async listarContratosPorId(id: number): Promise<ContratosEntity>{
        const contratos = await this.contratosRepo.getContratoById(id);
        if(!contratos){
            throw new AppError(404, "Contrato não encontrado");
        }
        return contratos;
    }

    async listarContratosAll(): Promise<any[]>{
        const contratos = await this.contratosRepo.getContratoAll();  
        if(!contratos || contratos.length === 0){
            throw new AppError(404, "Nenhum contrato encontrado");
        }
        return contratos;
    }

    async createContrato(dados: Omit<ContratosEntity, "id">): Promise<ContratosEntity>{
        const contratos = await this.contratosRepo.createContrato(dados);
        return contratos;
    }

    async saveContrato(id: number, dados: Omit<ContratosEntity, "id">): Promise<ContratosEntity>{
        const contratoExistente = await this.contratosRepo.getContratoById(id);
        if(!contratoExistente){
            throw new AppError(404, "Contrato não encontrado");
        }
        const contratoAtualizado = {...contratoExistente, ...dados};
        return await this.contratosRepo.saveContrato(contratoAtualizado);
    }

    async deleteContrato(id: number): Promise<boolean>{
        const contratoExistente = await this.contratosRepo.getContratoById(id);
        if(!contratoExistente){
            throw new AppError(404, "Contrato não encontrado");
        }
        return await this.contratosRepo.deleteContrato(id);
    }

};