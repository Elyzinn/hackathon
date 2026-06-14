import "reflect-metadata";
import { AppDataSource } from "../data-source";
import { ContratosEntity } from "../../models/ContratosEntity";


const contratos = [
    {
        vagas: 1,
        aluno: 4,
        date_inicio: new Date('2025-12-01'),
        data_fim: new Date('2026-06-30'),
    },
    {
        vagas: 2,
        aluno: 1,
        date_inicio: new Date('2026-02-01'),
        data_fim: new Date('2026-08-01'),
    },
]



export async function seedContratos() {
    const contratosRepo = AppDataSource.getRepository(ContratosEntity);

    const contratosSeeds = await Promise.all(
        contratos.map(async (contratos) => {
            return {
                vagas_id: contratos.vagas,
                aluno_id: contratos.aluno,
                date_inicio: contratos.date_inicio,
                data_fim: contratos.data_fim,
            }
        })
    )

    await contratosRepo.save(contratosSeeds);
}