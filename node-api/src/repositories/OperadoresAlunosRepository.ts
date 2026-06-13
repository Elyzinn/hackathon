
import { Repository } from "typeorm";
import { OperadoresAlunosEntity } from "../models/OperadoresAlunosEntity";

export interface IOperadoresAlunosRepository{
    getCandidaturaById(id: number):Promise<OperadoresAlunosEntity | undefined>;

}; 

export class OperadoresAlunosRepository implements IOperadoresAlunosRepository{
    constructor(private readonly repo: Repository<OperadoresAlunosEntity>){};

    async getCandidaturaById(idoperacoes_aluno: number): Promise<OperadoresAlunosEntity | undefined> {
        const linha = await this.repo.findOne({where: { idoperacoes_aluno }});
        return linha ?? undefined;
    }
    
}; 
