
import { Repository } from "typeorm";
import { OperadocoesAlunosEntity } from "../models/OperacoesAlunosEntity";

export interface IOperadoresAlunosRepository{
    getCandidaturaById(id: number):Promise<OperadocoesAlunosEntity | undefined>;

}; 

export class OperadoresAlunosRepository implements IOperadoresAlunosRepository{
    constructor(private readonly repo: Repository<OperadocoesAlunosEntity>){};

    async getCandidaturaById(id: number): Promise<OperadocoesAlunosEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }
    
}; 
