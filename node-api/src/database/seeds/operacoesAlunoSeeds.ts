import { AppDataSource } from "../data-source";
import { OperadocoesAlunosEntity } from "../../models/OperacoesAlunosEntity";

const opeAlunos = [
    {
        descricao: 'Cadastro de aluno realizado',
        data: new Date('2026-01-15'),
        operadores: 1,
        aluno: 1,
    },
    {
        descricao: 'Atualização de dados cadastrais',
        data: new Date('2026-02-10'),
        operadores: 2,
        aluno: 4,
    },
    {
        descricao: 'Validação de documentos',
        data: new Date('2026-03-05'),
        operadores: 5,
        aluno: 5,
    },
    {
        descricao: 'Cadastro de aluno realizado',
        data: new Date('2026-03-20'),
        operadores: 4,
        aluno: 3,
    },
    {
        descricao: 'Atualização de período letivo',
        data: new Date('2026-04-01'),
        operadores: 4,
        aluno: 4,
    },
]

export async function seedOperacoesAluno() {
    const opAlunosRepo = AppDataSource.getRepository(OperadocoesAlunosEntity);

    const opAlunoSeeds = await Promise.all(
        opeAlunos.map(async (opeAluno) => {
            return {
                descricao: opeAluno.descricao,
                data: opeAluno.data,
                operadores_id: opeAluno.operadores,
                aluno_id: opeAluno.aluno,
            }
        })
    )

    await opAlunosRepo.save(opAlunoSeeds);
}