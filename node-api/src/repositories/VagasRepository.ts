import { Repository } from "typeorm";
import { VagasEntity } from "../models/VagasEntity";

export interface IVagasRepository {
    getVagaById(id: number): Promise<VagasEntity | undefined>;
    getVagaAll(): Promise<any[]>;
    getVagaPorEmpresa(empresa_id: number): Promise<any[]>;
    getVagaByStatus(vaga_preenchida: boolean): Promise<VagasEntity[]>;
    createVaga(data: Omit<VagasEntity, "id">): Promise<VagasEntity>;
    saveVaga(entity: VagasEntity): Promise<VagasEntity>;
    deleteVaga(id: number): Promise<boolean>;
};

export class VagasRepository implements IVagasRepository {
    constructor(private readonly repo: Repository<VagasEntity>) { };

    async getVagaById(id: number): Promise<VagasEntity | undefined> {
        const linha = await this.repo.findOne({ where: { id } });
        return linha ?? undefined;
    }

    // 26 - Busca todas as vagas
    //27 -J unta com a empresa correspondente (se houver)
    // 32 - Retorna só os campos selecionados
    // 43 - Ordenadas da mais recente para a mais antiga
    async getVagaAll(): Promise<any[]> {
        const vagas = await this.repo.createQueryBuilder("vaga")
            .leftJoinAndSelect(
                "empresas",
                "empresa",
                "vaga.empresas_id = empresa.id"
            )
            .select([
                "vaga.id",
                "vaga.cargo",
                "vaga.vaga_preenchida",
                "vaga.data_abertura",
                "vaga.data_fechamento",
                "vaga.requisitos",
                "empresa.razao_social",
                "empresa.email",
                "empresa.telefone_contato"
            ])
            .orderBy("vaga.data_abertura", "DESC")
            .getRawMany();
        return vagas;
    }

    async getVagaPorEmpresa(empresa_id: number): Promise<any[]> {
        const vagas = await this.repo.createQueryBuilder("vaga")
            .leftJoin("empresas", "empresa", "vaga.empresas_id = empresa.id")
            .select("vaga.id", "id")
            .addSelect("vaga.cargo", "cargo")
            .addSelect("vaga.vaga_preenchida", "vaga_preenchida")
            .addSelect("DATE_FORMAT(vaga.data_abertura, '%d/%m/%Y')", "data_abertura")
            .addSelect("DATE_FORMAT(vaga.data_fechamento, '%d/%m/%Y')", "data_fechamento")
            .addSelect("vaga.requisitos", "requisitos")
            .addSelect("empresa.razao_social", "razao_social")
            .addSelect("empresa.email", "email")
            .addSelect("empresa.telefone_contato", "telefone_contato")
            .where("empresa.id = :empresa_id", { empresa_id })
            .orderBy("vaga.data_abertura", "DESC")
            .getRawMany();
        return vagas;
    }

    async getVagaByStatus(vaga_preenchida: boolean): Promise<VagasEntity[]> {
        const vagas = await this.repo.find({
            where: { vaga_preenchida },
            order: { data_abertura: "DESC" }
        });
        return vagas;
    }

    async createVaga(dados: Omit<VagasEntity, "id">): Promise<VagasEntity> {
        const dadosIn = this.repo.create(dados);
        const save = await this.repo.save(dadosIn);
        return (save);
    }

    async saveVaga(entity: VagasEntity): Promise<VagasEntity> {
        const save = await this.repo.save(entity);
        return (save);
    }

    async deleteVaga(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }


};


