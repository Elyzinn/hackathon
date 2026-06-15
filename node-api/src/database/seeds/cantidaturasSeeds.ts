import "reflect-metadata";
import { AppDataSource } from "../data-source";
import { CandidaturasEntity } from "../../models/CandidaturasEntity";

const canditaturas = [
    { aluno_idaluno: 4, vagas_id: 1, data_candidatura: new Date("2024-01-15") },
    { aluno_idaluno: 1, vagas_id: 4, data_candidatura: new Date("2024-01-16") },
    { aluno_idaluno: 2, vagas_id: 2, data_candidatura: new Date("2024-01-17") },
    { aluno_idaluno: 4, vagas_id: 3, data_candidatura: new Date("2024-01-18") },
    { aluno_idaluno: 5, vagas_id: 1, data_candidatura: new Date("2024-01-19") },
    { aluno_idaluno: 2, vagas_id: 2, data_candidatura: new Date("2024-01-20") },
];

export async function seedCandidaturas(): Promise<void> {
    const candRepo = AppDataSource.getRepository(CandidaturasEntity);

    const candiSeeds = await Promise.all(
        canditaturas.map(async (canditaturas) => {
            return {
                aluno_id: canditaturas.aluno_idaluno,
                vagas_id: canditaturas.vagas_id,
                data_candidatura: canditaturas.data_candidatura,
            };
        })
    );

    await candRepo.save(candiSeeds);
}
