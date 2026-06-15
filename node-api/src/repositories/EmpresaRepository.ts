import { Repository, DataSource } from "typeorm";
import { EmpresasEntity, EmpresaPublico } from "../models/EmpresasEntity";
import EnderecoEmpresaEntity from "../models/EnderecoEmpresaEntity";

export interface IEmpresaRepository{
    getEmpresaById(id: number):Promise<EmpresasEntity | undefined>;
    getEmpresaByCnpj(cnpj:string):Promise<EmpresasEntity | undefined>;
    createEmpresa(dadosEmpresa: Omit<EmpresasEntity, "id" | "endereco_empresa">,dadosEndereco: Omit<EnderecoEmpresaEntity, "id">): Promise<EmpresasEntity>;
    saveEmpresa(entity: EmpresasEntity): Promise<EmpresasEntity>;
    deleteEmpresa(id: number): Promise<boolean>;
}; 

function noPassword(a: EmpresasEntity): EmpresaPublico{
    const { senha: _s, ...rest} = a;
    return rest;
}

export class EmpresaRepository implements IEmpresaRepository{
    constructor(private readonly repo: Repository<EmpresasEntity>,
        private readonly dataSource: DataSource
    ){};

    async getEmpresaById(id: number): Promise<EmpresasEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }

    async getEmpresaByCnpj(cnpj: string): Promise<EmpresasEntity | undefined> {
        const aluno = await this.repo.findOne({where: {cnpj}});
        return aluno ?? undefined;
    }

    async createEmpresa(dadosEmpresa: Omit<EmpresasEntity, "id" | "endereco_empresa">, dadosEndereco: Omit<EnderecoEmpresaEntity, "id">): Promise<EmpresasEntity> {
        const qR = this.dataSource.createQueryRunner();
        await qR.connect();
        await qR.startTransaction();

        try {
            const endereco = qR.manager.create(EnderecoEmpresaEntity, dadosEndereco);
            await qR.manager.save(endereco);

            const empresa = qR.manager.create(EmpresasEntity,{
                ...dadosEmpresa,
                endereco_empresa: endereco.id,
            });

            await qR.manager.save(empresa);

            await qR.commitTransaction();
            return empresa;
        } catch (error) {
            await qR.rollbackTransaction(); 
            throw error;
        }finally{
            await qR.release();
        }
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
