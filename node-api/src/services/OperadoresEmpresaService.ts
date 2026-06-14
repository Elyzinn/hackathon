
import { IOperadoresEmpresasRepository } from "../repositories/OperadoresEmpresasRepository";
import { OperacoesEmpresasEntity } from "../models/OperacoesEmpresasEntity";
import AppError from "../utils/AppErrors";


export class OperadoresEmpresaService {
    constructor(private readonly operadoresEmpresasRepo: IOperadoresEmpresasRepository){}

    async listarOperadoresEmpresasPorId(id: number): Promise<OperacoesEmpresasEntity>{
        const opeEmpresas = await this.operadoresEmpresasRepo.getCandidaturaById(id);
        if(!opeEmpresas){
            throw new AppError(404, "Operador não encontrado");
        }
        return opeEmpresas;
    }

};