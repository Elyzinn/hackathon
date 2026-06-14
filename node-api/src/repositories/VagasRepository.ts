import { Repository } from "typeorm";
import { VagasEntity } from "../models/VagasEntity";

export interface IVagasRepository{
    getVagaById(id: number):Promise<VagasEntity | undefined>;
    createVaga(data: Omit<VagasEntity, "id">): Promise<VagasEntity>;
    saveVaga(entity: VagasEntity): Promise<VagasEntity>;
    deleteVaga(id: number): Promise<boolean>;
}; 

export class VagasRepository implements IVagasRepository{
    constructor(private readonly repo: Repository<VagasEntity>){};

    async getVagaById(id: number): Promise<VagasEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }

    async createVaga(dados: Omit<VagasEntity, "id">): Promise<VagasEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return(save);
    }
    
    async saveVaga(entity: VagasEntity): Promise<VagasEntity> {
        const save = await this.repo.save(entity);
        return(save);
    }
    
    async deleteVaga(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }

    
}; 


