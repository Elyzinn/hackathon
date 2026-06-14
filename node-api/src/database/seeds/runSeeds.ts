import 'reflect-metadata';
import { AppDataSource } from '../data-source';
import { seedEnderecoAluno } from './enderecoAlunoSeeds';
import { seedEnderecoEmpresa } from './enderecoEmpresaSeeds';
import { seedAlunos } from './alunosSeeds';
import { seedEmpresas } from './empresasSeeds';
import { seedOperadores } from './operadoresSeedes';
import { seedVagas } from './vagasSeeder';
import { seedCandidaturas } from './cantidaturasSeeds';
import { seedContratos } from './contratosSeeds';
import { seedOperacoesAluno } from './operacoesAlunoSeeds';
import { seedOperacoesEmpresa } from './operacoesEmpresaSeeds';

async function runSeeds(): Promise<void> {
  await AppDataSource.initialize();
  try {
    //Sem dependências
    await seedEnderecoAluno();
    await seedEnderecoEmpresa();
    await seedOperadores();

    //Dependem de endereços
    await seedAlunos();
    await seedEmpresas();

    //Dependem de empresas
    await seedVagas();

    //Dependem de alunos + vagas
    await seedCandidaturas();
    await seedContratos();

    //Dependem de operadores + alunos/empresas
    await seedOperacoesAluno();
    await seedOperacoesEmpresa();

    console.log('Todos os seeds executados com sucesso!');
  } catch (error) {
    console.error('Erro durante os seeds:', error);
    throw error;
  } finally {
    await AppDataSource.destroy();
  }
}

runSeeds();