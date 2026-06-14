
import { IOperadoresAlunosRepository } from "../repositories/OperadoresAlunosRepository";
import { OperadocoesAlunosEntity } from "../models/OperacoesAlunosEntity";
import AppError from "../utils/AppErrors";


export class OperadoresAlunoService {
    constructor(private readonly operadoresAlunosRepo: IOperadoresAlunosRepository){}

    async listarOperadoresAlunosPorId(id: number): Promise<OperadocoesAlunosEntity>{
        const opeAlunos = await this.operadoresAlunosRepo.getCandidaturaById(id);
        if(!opeAlunos){
            throw new AppError(404, "Operador não encontrado");
        }
        return opeAlunos;
    }

};