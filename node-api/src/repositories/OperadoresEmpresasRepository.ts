
import { Repository } from "typeorm";
import { OperacoesEmpresasEntity } from "../models/OperacoesEmpresasEntity";

export interface IOperadoresEmpresasRepository{
    getCandidaturaById(id: number):Promise<OperacoesEmpresasEntity | undefined>;

}; 

export class OperadoresEmpresasRepository implements IOperadoresEmpresasRepository{
    constructor(private readonly repo: Repository<OperacoesEmpresasEntity>){};

    async getCandidaturaById(id: number): Promise<OperacoesEmpresasEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }
    
}; 
