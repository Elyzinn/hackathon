
import { EnderecoEmpresaRepository, IEnderecoEmpresaRepository } from "../repositories/EnderecoEmpresaRepository";
import AppError from "../utils/AppErrors";
import { EnderecoEmpresaEntity } from "../models/EnderecoEmpresaEntity";

export class EnderecoEmpresaService{
    constructor(private readonly endemp: IEnderecoEmpresaRepository){}

    async getEnderecoByCidade(cidade: string): Promise<EnderecoEmpresaEntity | undefined>{
        const cidadeEmpresa = await this.endemp.getEnderecoByCidade(cidade);
        if(!cidadeEmpresa){
            throw new AppError(404, "Nenhum endereço encontrado");
        
        }
        return cidadeEmpresa;
    }

    async getEnderecoById(id: number): Promise<EnderecoEmpresaEntity | undefined>{
        const endemp = await this.endemp.getEnderecoById(id);
        if(!endemp){
            throw new AppError(404, "Endereço não encontrado");
        }
        return endemp;
    }

    async getEnderecoByCep(cep: string): Promise<EnderecoEmpresaEntity | undefined>{
        const endemp = await this.endemp.getEnderecoByCep(cep);
        if(!endemp){
            throw new AppError(404, "Endereço não encontrado");
        }
        return endemp;
    }

    async getEnderecoByUf(uf: string): Promise<EnderecoEmpresaEntity | undefined>{
        const endemp = await this.endemp.getEnderecoByUf(uf);
        if(!endemp){
            throw new AppError(404, "Endereço não encontrado");
        }
        return endemp;
    }

    async atualizarEndereco(
        id: number,
        data: Partial<{logradouro: string; num_logradouro: string; bairro: string;cep: string; cidade: string; uf: string}>,
    ): Promise<EnderecoEmpresaEntity>{ {
        const endemp = await this.endemp.getEnderecoById(id);
        if (!endemp) {
            throw new AppError(404," Endereço não encontrado");
        }
        if(id && id !== endemp.id){
            const another = await this.endemp.getEnderecoById(id);
            if(another){
                throw new AppError(409, "Endereço já existe");
            }
        }

        if(data.logradouro !== undefined) endemp.logradouro = data.logradouro;
        if(data.num_logradouro !== undefined) endemp.num_logradouro = data.num_logradouro;
        if(data.bairro !== undefined) endemp.bairro = data.bairro;
        if(data.cep !== undefined) endemp.cep = data.cep;
        if(data.cidade !== undefined) endemp.cidade = data.cidade;
        if(data.uf !== undefined) endemp.uf = data.uf;

        return this.endemp.saveEnderecoEmpresa(endemp);
        }
    }

    async deleteEnderecoAlunos(id: number): Promise<void>{
        const ok = await this.endemp.deleteEnderecoEmpresa(id);
        if(!ok){
            throw new AppError(404, "Endereço não encontrado");
        }
    }
}
