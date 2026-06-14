import { AppDataSource } from "../data-source"
import { VagasEntity } from "../../models/VagasEntity";


const vagas = [
  {
    cargo: 'Desenvolvedor Frontend',
    vaga_preenchida: true,
    data_abertura: new Date('2026-01-10'),
    data_fechamento: new Date('2026-03-10'),
    empresa: 1,
  },
  {
    cargo: 'Analista de Dados',
    vaga_preenchida: false,
    data_abertura: new Date('2025-11-01'),
    data_fechamento: new Date('2026-01-31'),
    empresa: 1,
  },
  {
    cargo: 'Estagiário em Suporte de TI',
    vaga_preenchida: false,
    data_abertura: new Date('2026-02-15'),
    data_fechamento: new Date('2026-04-15'),
    empresa: 2,
  },
  {
    cargo: 'Desenvolvedor Backend',
    vaga_preenchida: true,
    data_abertura: new Date('2026-03-01'),
    data_fechamento: new Date('2026-05-01'),
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
        empresas_id: vagas.empresa,
      };
    })
  );

  await vagasRepo.save(vagasSeeds);
}