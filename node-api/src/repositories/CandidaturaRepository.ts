import { Repository } from "typeorm";
import { CandidaturasEntity } from "../models/CandidaturasEntity";

export interface ICandidaturasRepository{
    getCandidaturaById(id: number):Promise<CandidaturasEntity | undefined>;
    createCandidatura(data: Omit<CandidaturasEntity, "id">): Promise<CandidaturasEntity>;
    saveCandidatura(entity: CandidaturasEntity): Promise<CandidaturasEntity>;
    deleteCandidatura(id: number): Promise<boolean>;
}; 

export class CandidaturasRepository implements ICandidaturasRepository{
    constructor(private readonly repo: Repository<CandidaturasEntity>){};

    async getCandidaturaById(id: number): Promise<CandidaturasEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }

    async createCandidatura(dados: Omit<CandidaturasEntity, "id">): Promise<CandidaturasEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return(save);
    }
    
    async saveCandidatura(entity: CandidaturasEntity): Promise<CandidaturasEntity> {
        const save = await this.repo.save(entity);
        return(save);
    }
    
    async deleteCandidatura(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }
    
}; 
