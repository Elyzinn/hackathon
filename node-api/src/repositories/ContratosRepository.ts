import { Repository } from "typeorm";
import { ContratosEntity } from "../models/ContratosEntity";

export interface IContratosRepository{
    getContratoById(id: number):Promise<ContratosEntity | undefined>;
    createContrato(data: Omit<ContratosEntity, "id">): Promise<ContratosEntity>;
    saveContrato(entity: ContratosEntity): Promise<ContratosEntity>;
    deleteContrato(id: number): Promise<boolean>;
}; 

export class ContratosRepository implements IContratosRepository{
    constructor(private readonly repo: Repository<ContratosEntity>){};

    async getContratoById(id: number): Promise<ContratosEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }

    async createContrato(dados: Omit<ContratosEntity, "id">): Promise<ContratosEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return(save);
    }
    
    async saveContrato(entity: ContratosEntity): Promise<ContratosEntity> {
        const save = await this.repo.save(entity);
        return(save);
    }
    
    async deleteContrato(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }
    
}; 
