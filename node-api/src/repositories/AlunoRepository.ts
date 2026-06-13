import { Repository } from "typeorm";
import { AlunosEntity, AlunoPublico } from "../models/AlunosEntity";

export interface IAlunoRepository{
    getAlunoById(id: number):Promise<AlunosEntity | undefined>;
    getAlunoByCpf(cpf:string):Promise<AlunosEntity | undefined>
}; 

function noPassword(a: AlunosEntity): AlunoPublico{
    const { senha: _s, ...rest} = a;
    return rest;
}

export class AlunoRepository implements IAlunoRepository{
    constructor(private readonly repo: Repository<AlunosEntity>){};

    async getAlunoById(id: number): Promise<AlunosEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }

    async getAlunoByCpf(cpf: string): Promise<AlunosEntity | undefined> {
        const aluno = await this.repo.findOne({where: {cpf}});
        return aluno ?? undefined;
    }
}; 
