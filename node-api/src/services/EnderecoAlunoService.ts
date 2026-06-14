
import { EnderecoAlunoRepository, IEnderecoAlunoRepository } from "../repositories/EnderecoAlunoRepository";
import {  EnderecoAlunoEntity } from "../models/EnderecoAlunoEntity";
import AppError from "../utils/AppErrors";

export class EnderecoAlunoService{
    constructor(private readonly endal: IEnderecoAlunoRepository){}

    async getEnderecoByCidade(cidade: string): Promise<EnderecoAlunoEntity | undefined>{
        const cidadeAluno = await this.endal.getEnderecoByCidade(cidade);
        if(!cidadeAluno){
            throw new AppError(404, "Nenhum endereço encontrado");
        
        }
        return cidadeAluno;
    }

    async getEnderecoById(id: number): Promise<EnderecoAlunoEntity | undefined>{
        const endal = await this.endal.getEnderecoById(id);
        if(!endal){
            throw new AppError(404, "Endereço não encontrado");
        }
        return endal;
    }

    async getEnderecoByCep(cep: string): Promise<EnderecoAlunoEntity | undefined>{
        const endal = await this.endal.getEnderecoByCep(cep);
        if(!endal){
            throw new AppError(404, "Endereço não encontrado");
        }
        return endal;
    }

    async getEnderecoByUf(uf: string): Promise<EnderecoAlunoEntity | undefined>{
        const endal = await this.endal.getEnderecoByUf(uf);
        if(!endal){
            throw new AppError(404, "Endereço não encontrado");
        }
        return endal;
    }

    async atualizarEndereco(
        id: number,
        data: Partial<{logradouro: string; num_logradouro: string; bairro: string;cep: string; cidade: string; uf: string}>,
    ): Promise<EnderecoAlunoEntity>{ {
        const endal = await this.endal.getEnderecoById(id);
        if (!endal) {
            throw new AppError(404," Endereço não encontrado");
        }
        if(id && id !== endal.id){
            const another = await this.endal.getEnderecoById(id);
            if(another){
                throw new AppError(409, "Endereço já existe");
            }
        }

        if(data.logradouro !== undefined) endal.logradouro = data.logradouro;
        if(data.num_logradouro !== undefined) endal.num_logradouro = data.num_logradouro;
        if(data.bairro !== undefined) endal.bairro = data.bairro;
        if(data.cep !== undefined) endal.cep = data.cep;
        if(data.cidade !== undefined) endal.cidade = data.cidade;
        if(data.uf !== undefined) endal.uf = data.uf;

        return this.endal.saveEnderecoAlunos(endal);
        }
    }

    async deleteEnderecoAlunos(id: number): Promise<void>{
        const ok = await this.endal.deleteEnderecoAlunos(id);
        if(!ok){
            throw new AppError(404, "Endereço não encontrado");
        }
    }
}
