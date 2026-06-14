import { Repository } from "typeorm";
import { EmpresasEntity, EmpresaPublico } from "../models/EmpresasEntity";

export interface IEmpresaRepository{
    getEmpresaById(id: number):Promise<EmpresasEntity | undefined>;
    getEmpresaByCnpj(cnpj:string):Promise<EmpresasEntity | undefined>;
    createEmpresa(data: Omit<EmpresasEntity, "id">): Promise<EmpresasEntity>;
    saveEmpresa(entity: EmpresasEntity): Promise<EmpresasEntity>;
    deleteEmpresa(id: number): Promise<boolean>;
}; 

function noPassword(a: EmpresasEntity): EmpresaPublico{
    const { senha: _s, ...rest} = a;
    return rest;
}

export class EmpresaRepository implements IEmpresaRepository{
    constructor(private readonly repo: Repository<EmpresasEntity>){};

    async getEmpresaById(id: number): Promise<EmpresasEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }

    async getEmpresaByCnpj(cnpj: string): Promise<EmpresasEntity | undefined> {
        const aluno = await this.repo.findOne({where: {cnpj}});
        return aluno ?? undefined;
    }

    async createEmpresa(dados: Omit<EmpresasEntity, "id">): Promise<EmpresasEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return(save);
    }
    
    async saveEmpresa(entity: EmpresasEntity): Promise<EmpresasEntity> {
        const save = await this.repo.save(entity);
        return(save);
    }
    
    async deleteEmpresa(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }
    
}; 
