
import { Repository } from "typeorm";
import { OperadoresEmpresasEntity } from "../models/OperadoresEmpresasEntity";

export interface IOperadoresEmpresasRepository{
    getCandidaturaById(id: number):Promise<OperadoresEmpresasEntity | undefined>;

}; 

export class OperadoresEmpresasRepository implements IOperadoresEmpresasRepository{
    constructor(private readonly repo: Repository<OperadoresEmpresasEntity>){};

    async getCandidaturaById(id: number): Promise<OperadoresEmpresasEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }
    
}; 
