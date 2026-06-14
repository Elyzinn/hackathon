
import { ICandidaturasRepository } from "../repositories/CandidaturaRepository";
import { CandidaturasEntity } from "../models/CandidaturasEntity";
import AppError from "../utils/AppErrors";


export class CandidaturaService{
    constructor(private readonly candidaturasRepo: ICandidaturasRepository){}

    async listarCandidaturasPorId(id: number): Promise<CandidaturasEntity>{
        const candidaturas = await this.candidaturasRepo.getCandidaturaById(id);
        if(!candidaturas){
            throw new AppError(404, "Candidatura não encontrada");
        }
        return candidaturas;
    }

    async createCandidatura(dados: Omit<CandidaturasEntity, "id">): Promise<CandidaturasEntity>{
        const candidaturas = await this.candidaturasRepo.createCandidatura(dados);
        return candidaturas;
    }

    async saveCandidatura(id: number, dados: Omit<CandidaturasEntity, "id">): Promise<CandidaturasEntity>{
        const candidaturaExistente = await this.candidaturasRepo.getCandidaturaById(id);
        if(!candidaturaExistente){
            throw new AppError(404, "Candidatura não encontrada");
        }
        const candidaturaAtualizada = {...candidaturaExistente, ...dados};
        return await this.candidaturasRepo.saveCandidatura(candidaturaAtualizada);
    }

    async deleteCandidatura(id: number): Promise<boolean>{
        const candidaturaExistente = await this.candidaturasRepo.getCandidaturaById(id);
        if(!candidaturaExistente){
            throw new AppError(404, "Candidatura não encontrada");
        }
        return await this.candidaturasRepo.deleteCandidatura(id);
    }

};