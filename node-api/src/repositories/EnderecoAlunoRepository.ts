import type { Repository } from "typeorm";
import type { EnderecoAlunoEntity } from "../models/EnderecoAlunoEntity";

export interface IEnderecoAlunoRepository {
    getEnderecoByCidade(cidade: string): Promise<EnderecoAlunoEntity | undefined>;
    getEnderecoById(id: number): Promise<EnderecoAlunoEntity | undefined>;
    getEnderecoByCep(cep: string): Promise<EnderecoAlunoEntity | undefined>;
    getEnderecoByUf(uf: string): Promise<EnderecoAlunoEntity | undefined>;
    createEnderecoAlunos(data: Omit<EnderecoAlunoEntity, "id">): Promise<EnderecoAlunoEntity>;
    saveEnderecoAlunos(entity: EnderecoAlunoEntity): Promise<EnderecoAlunoEntity>;
    deleteEnderecoAlunos(id: number): Promise<boolean>;
};

export class EnderecoAlunoRepository implements IEnderecoAlunoRepository{
    constructor(private readonly repo: Repository<EnderecoAlunoEntity>) {};

    async getEnderecoById(id: number): Promise<EnderecoAlunoEntity | undefined> {
        const row = await this.repo.findOne({where: { id }});
        return row ?? undefined;
    }

    async getEnderecoByCidade(cidade: string): Promise<EnderecoAlunoEntity | undefined> {
        const row = await this.repo.findOne({where: {cidade}});
        return row ?? undefined;
    }

    async getEnderecoByCep(cep: string): Promise<EnderecoAlunoEntity | undefined> {
        const row = await this.repo.findOne({where: {cep}});
        return row ?? undefined;
    }

    async getEnderecoByUf(uf: string): Promise<EnderecoAlunoEntity | undefined> {
        const row = await this.repo.findOne({where: {uf}});
        return row ?? undefined;
    }

    async createEnderecoAlunos(dados: Omit<EnderecoAlunoEntity, "id">): Promise<EnderecoAlunoEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return(save);
    }

    async saveEnderecoAlunos(entity: EnderecoAlunoEntity): Promise<EnderecoAlunoEntity> {
        const save = await this.repo.save(entity);
        return(save);
    }

    async deleteEnderecoAlunos(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }
}