import "reflect-metadata";
import { AppDataSource } from "../data-source";
import { CandidaturasEntity } from "../../models/CandidaturasEntity";

const canditaturas = [
    { aluno_idaluno: 4, vagas_id: 1 },
    { aluno_idaluno: 1, vagas_id: 4 },
    { aluno_idaluno: 2, vagas_id: 2 },
    { aluno_idaluno: 4, vagas_id: 3 },
    { aluno_idaluno: 5, vagas_id: 1 },
    { aluno_idaluno: 2, vagas_id: 2 },
];

export async function seedCandidaturas(): Promise<void> {
    const candRepo = AppDataSource.getRepository(CandidaturasEntity);

    const candiSeeds = await Promise.all(
        canditaturas.map(async (canditaturas) => {
            return {
                aluno_id: canditaturas.aluno_idaluno,
                vagas_id: canditaturas.vagas_id,
            };
        })
    );

    await candRepo.save(candiSeeds);
}
