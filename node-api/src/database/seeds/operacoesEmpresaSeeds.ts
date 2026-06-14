import { AppDataSource } from "../data-source";
import { OperacoesEmpresasEntity } from "../../models/OperacoesEmpresasEntity";

const opEmpresas = [
    {
        descricao: 'Cadastro de empresa aprovado',
        date_operacao: new Date('2026-01-20T10:30:00'),
        operadores: 1,
        empresa: 3,
    },
    {
        descricao: 'Documentação CNPJ verificada',
        date_operacao: new Date('2026-02-05T14:00:00'),
        operadores: 2,
        empresa: 1,
    },
    {
        descricao: 'Empresa suspensa por pendências',
        date_operacao: new Date('2026-02-28T09:15:00'),
        operadores: 3,
        empresa: 2,
    },
]


export async function seedOperacoesEmpresa() {
    const opEmpresasRepo = AppDataSource.getRepository(OperacoesEmpresasEntity);

    const opEmpresasSeeds = await Promise.all(
        opEmpresas.map(async (opEmpresas) => {
            return {
                descricao: opEmpresas.descricao,
                date_operacao: opEmpresas.date_operacao,
                operadores_id: opEmpresas.operadores,
                empresas_id: opEmpresas.empresa,
            }
        })
    )

    await opEmpresasRepo.save(opEmpresasSeeds);
}