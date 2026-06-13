import * as bcrypt from "bcrypt";
import { IAlunoRepository } from "../repositories/AlunoRepository";
import {  AlunoPublico, AlunosEntity } from "../models/AlunosEntity";
import AppError from "../utils/AppErrors";


export class AlunoService{
    constructor(private readonly alunoRepo: IAlunoRepository){}

    async listarAlunoPorId(id: number): Promise<AlunoPublico>{
        const aluno = await this.alunoRepo.getAlunoById(id);
        if(!aluno){
            throw new AppError(404, "Usuario não encontrado");
        }
        const { senha: _s, ...alunoPublico} = aluno;
        return alunoPublico;
    }

    async loginAluno(dados: {cpf:string; senha: string; email:string}): Promise<AlunosEntity>{
        const aluno = await this.alunoRepo.getAlunoByCpf(dados.cpf);
        if(!aluno){
            throw new AppError(404, "Aluno sem matricula");
        }
        const senhaVerficada = await bcrypt.compare(dados.senha, aluno.senha);
        if(senhaVerficada == false){
            throw new AppError(401, "Credenciais erradas");
        }
        return aluno;
    }
}