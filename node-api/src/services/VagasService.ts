
import { IVagasRepository } from "../repositories/VagasRepository";
import { VagasEntity } from "../models/VagasEntity";
import AppError from "../utils/AppErrors";


export class VagasService{
    constructor(private readonly vagasRepo: IVagasRepository){}

    async listarVagasPorId(id: number): Promise<VagasEntity>{
        const vagas = await this.vagasRepo.getVagaById(id);
        if(!vagas){
            throw new AppError(404, "Vaga não encontrada");
        }
        return vagas;
    }

    async createVaga(dados: Omit<VagasEntity, "id">): Promise<VagasEntity>{
        const vagas = await this.vagasRepo.createVaga(dados);
        return vagas;
    }

    async saveVaga(id: number, dados: Omit<VagasEntity, "id">): Promise<VagasEntity>{
        const vagaExistente = await this.vagasRepo.getVagaById(id);
        if(!vagaExistente){
            throw new AppError(404, "Vaga não encontrada");
        }
        const vagaAtualizada = {...vagaExistente, ...dados};
        return await this.vagasRepo.saveVaga(vagaAtualizada);
    }

    async deleteVaga(id: number): Promise<boolean>{
        const vagaExistente = await this.vagasRepo.getVagaById(id);
        if(!vagaExistente){
            throw new AppError(404, "Vaga não encontrada");
        }
        return await this.vagasRepo.deleteVaga(id);
    }

};