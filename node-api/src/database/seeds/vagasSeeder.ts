import { AppDataSource } from "../data-source"
import { VagasEntity } from "../../models/VagasEntity";
import { describe } from "zod/v4/core";


const vagas = [
  {
    cargo: 'Desenvolvedor Frontend',
    vaga_preenchida: true,
    data_abertura: new Date('2026-01-10'),
    data_fechamento: new Date('2026-03-10'),
    requisitos: "Vaga para desenvolvedor frontend com experiência em React e TypeScript.",
    empresa: 1,
  },
  {
    cargo: 'Analista de Dados',
    vaga_preenchida: false,
    data_abertura: new Date('2025-11-01'),
    data_fechamento: new Date('2026-01-31'),
    requisitos: "Vaga para analista de dados com experiência em Python e SQL.",
    empresa: 1,
  },
  {
    cargo: 'Estagiário em Suporte de TI',
    vaga_preenchida: false,
    data_abertura: new Date('2026-02-15'),
    data_fechamento: new Date('2026-04-15'),
    requisitos: "Vaga para estagiário em suporte de TI.",
    empresa: 2,
  },
  {
    cargo: 'Desenvolvedor Backend',
    vaga_preenchida: true,
    data_abertura: new Date('2026-03-01'),
    data_fechamento: new Date('2026-05-01'),
    requisitos: "Vaga para desenvolvedor backend com experiência em Node.js e TypeScript.",
    empresa: 3,
  },
]

export async function seedVagas(): Promise<void> {
  const vagasRepo = AppDataSource.getRepository(VagasEntity);

  const vagasSeeds = await Promise.all(
    vagas.map(async (vagas) => {
      return {
        cargo: vagas.cargo,
        vaga_preenchida: vagas.vaga_preenchida,
        data_abertura: vagas.data_abertura,
        data_fechamento: vagas.data_fechamento,
        requisitos: vagas.requisitos,
        empresas_id: vagas.empresa,
      };
    })
  );

  await vagasRepo.save(vagasSeeds);
}