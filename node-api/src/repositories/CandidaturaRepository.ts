import { Repository } from "typeorm";
import { CandidaturasEntity } from "../models/CandidaturasEntity";

export interface ICandidaturasRepository {
    getCandidaturaById(id: number): Promise<CandidaturasEntity | undefined>;
    createCandidatura(data: Omit<CandidaturasEntity, "id">): Promise<CandidaturasEntity>;
    saveCandidatura(entity: CandidaturasEntity): Promise<CandidaturasEntity>;
    deleteCandidatura(id: number): Promise<boolean>;
    getCandidaturaByAluno(id_aluno: number): Promise<any[]>;
    getCandidaturaByEmpresa(empresa_id: number): Promise<any[]>;
};

export class CandidaturasRepository implements ICandidaturasRepository {
    constructor(private readonly repo: Repository<CandidaturasEntity>) { };

    async getCandidaturaById(id: number): Promise<CandidaturasEntity | undefined> {
        const linha = await this.repo.findOne({ where: { id } });
        return linha ?? undefined;
    }

    async createCandidatura(dados: Omit<CandidaturasEntity, "id">): Promise<CandidaturasEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return (save);
    }

    async saveCandidatura(entity: CandidaturasEntity): Promise<CandidaturasEntity> {
        const save = await this.repo.save(entity);
        return (save);
    }

    async deleteCandidatura(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }

    async getCandidaturaByAluno(aluno_id: number): Promise<any[]> {
        const rows = await this.repo.createQueryBuilder("candidatura")
            .leftJoin("vagas", "vaga", "candidatura.vagas_id = vaga.id")
            .leftJoin("alunos", "aluno", "candidatura.aluno_id = aluno.id")
            .leftJoin("empresas", "empresa", "vaga.empresas_id = empresa.id")
            .select("candidatura.id", "id")
            .addSelect("candidatura.data_candidatura", "data_candidatura")
            .addSelect("vaga.id", "vaga.id")
            .addSelect("vaga.cargo", "cargo")
            .addSelect("empresa.razao_social", "empresa")
            .addSelect("aluno.id", "aluno_id")
            .where("aluno.id = :aluno_id", { aluno_id })
            .orderBy("aluno.id", "ASC")
            .getRawMany();
        return rows;
    }
    async getCandidaturaByEmpresa(empresa_id: number): Promise<any[]> {
        const rows = await this.repo.createQueryBuilder("candidatura")
            .leftJoin("vagas", "vaga", "candidatura.vagas_id = vaga.id")
            .leftJoin("alunos", "aluno", "candidatura.aluno_id = aluno.id")
            .leftJoin("empresas", "empresa", "vaga.empresas_id = empresa.id")
            .select("candidatura.id", "id")
            .addSelect("DATE_FORMAT(candidatura.data_candidatura, '%d/%m/%Y')", "data_candidatura")
            .addSelect("vaga.cargo", "cargo")
            .addSelect("aluno.nome", "nome")
            .addSelect("aluno.curso", "curso")
            .addSelect("empresa.razao_social", "empresa")
            .where("empresa.id = :empresa_id", { empresa_id })
            .orderBy("aluno.nome", "ASC")
            .getRawMany();
        return rows;
    }
}; 
