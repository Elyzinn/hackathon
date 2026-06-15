import { Repository } from "typeorm";
import { ContratosEntity } from "../models/ContratosEntity";

export interface IContratosRepository{
    getContratoById(id: number):Promise<ContratosEntity | undefined>;
    getContratoAll(): Promise<any[]>;
    createContrato(data: Omit<ContratosEntity, "id">): Promise<ContratosEntity>;
    saveContrato(entity: ContratosEntity): Promise<ContratosEntity>;
    deleteContrato(id: number): Promise<boolean>;
}; 

export class ContratosRepository implements IContratosRepository{
    constructor(private readonly repo: Repository<ContratosEntity>){};

    async getContratoById(id: number): Promise<ContratosEntity | undefined> {
        const linha = await this.repo.findOne({where: { id }});
        return linha ?? undefined;
    }

    async getContratoAll(): Promise<any[]> {
        const contratos = await this.repo.createQueryBuilder("contrato")
            .leftJoinAndSelect(
                "vagas",
                "vaga",
                "contrato.vagas_id = vaga.id"
            )
            .leftJoinAndSelect(
                "alunos",
                "aluno",
                "contrato.aluno_id = aluno.id"
            )
            .select([
                "contrato.id",
                "contrato.date_inicio",
                "contrato.data_fim",
                "vaga.cargo",
                "vaga.requisitos",
                "aluno.nome",
                "aluno.email",
                "aluno.cpf"
            ])
            .orderBy("contrato.date_inicio", "DESC")
            .getMany();

        return contratos;
    }

    async createContrato(dados: Omit<ContratosEntity, "id">): Promise<ContratosEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return(save);
    }
    
    async saveContrato(entity: ContratosEntity): Promise<ContratosEntity> {
        const save = await this.repo.save(entity);
        return(save);
    }
    
    async deleteContrato(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }
    
}; 
