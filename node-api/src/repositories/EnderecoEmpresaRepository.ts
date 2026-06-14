import type { Repository } from "typeorm";
import type { EnderecoEmpresaEntity } from "../models/EnderecoEmpresaEntity";

export interface IEnderecoEmpresaRepository {
    getEnderecoByCidade(cidade: string): Promise<EnderecoEmpresaEntity | undefined>;
    getEnderecoById(id: number): Promise<EnderecoEmpresaEntity | undefined>;
    getEnderecoByCep(cep: string): Promise<EnderecoEmpresaEntity | undefined>;
    getEnderecoByUf(uf: string): Promise<EnderecoEmpresaEntity | undefined>;
    createEnderecoEmpresa(data: Omit<EnderecoEmpresaEntity, "id">): Promise<EnderecoEmpresaEntity>;
    saveEnderecoEmpresa(entity: EnderecoEmpresaEntity): Promise<EnderecoEmpresaEntity>;
    deleteEnderecoEmpresa(id: number): Promise<boolean>;
};

export class EnderecoEmpresaRepository implements IEnderecoEmpresaRepository{
    constructor(private readonly repo: Repository<EnderecoEmpresaEntity>) {};

    async getEnderecoById(id: number): Promise<EnderecoEmpresaEntity | undefined> {
        const row = await this.repo.findOne({where: { id }});
        return row ?? undefined;
    }

    async getEnderecoByCidade(cidade: string): Promise<EnderecoEmpresaEntity | undefined> {
        const row = await this.repo.findOne({where: {cidade}});
        return row ?? undefined;
    }

    async getEnderecoByCep(cep: string): Promise<EnderecoEmpresaEntity | undefined> {
        const row = await this.repo.findOne({where: {cep}});
        return row ?? undefined;
    }

    async getEnderecoByUf(uf: string): Promise<EnderecoEmpresaEntity | undefined> {
        const row = await this.repo.findOne({where: {uf}});
        return row ?? undefined;
    }

    async createEnderecoEmpresa(dados: Omit<EnderecoEmpresaEntity, "id">): Promise<EnderecoEmpresaEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return(save);
    }

    async saveEnderecoEmpresa(entity: EnderecoEmpresaEntity): Promise<EnderecoEmpresaEntity> {
        const save = await this.repo.save(entity);
        return(save);
    }

    async deleteEnderecoEmpresa(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }
}