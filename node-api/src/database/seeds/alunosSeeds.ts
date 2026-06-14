import "reflect-metadata";
import { AppDataSource } from "../data-source";
import { hash } from "bcrypt";
import { AlunosEntity } from "../../models/AlunosEntity";

const alunosBase = [
    {
        nome: "Ana Carolina Silva",
        cpf: "12345678901",
        email: "ana.silva@email.com",
        ra: 2024001,
        telefone: "44999990001",
        curso: "Ciência da Computação",
        periodo: 3,
        data_nasc: new Date("2002-05-14"),
        endereco_aluno: 1,
    },
    {
        nome: "Bruno Henrique Costa",
        cpf: "23456789012",
        email: "bruno.costa@email.com",
        ra: 2024002,
        telefone: "44999990002",
        curso: "Sistemas de Informação",
        periodo: 5,
        data_nasc: new Date("2001-08-22"),
        endereco_aluno: 2,
    },
    {
        nome: "Camila Ferreira Souza",
        cpf: "34567890123",
        email: "camila.souza@email.com",
        ra: 2024003,
        telefone: "44999990003",
        curso: "Engenharia de Software",
        periodo: 2,
        data_nasc: new Date("2003-11-30"),
        endereco_aluno: 3,
    },
    {
        nome: "Diego Almeida Ramos",
        cpf: "45678901234",
        email: "diego.ramos@email.com",
        ra: 2024004,
        telefone: "44999990004",
        curso: "Análise e Desenvolvimento de Sistemas",
        periodo: 4,
        data_nasc: new Date("2000-03-17"),
        endereco_aluno: 4,
    },
    {
        nome: "Eduarda Lima Pinto",
        cpf: "56789012345",
        email: "eduarda.pinto@email.com",
        ra: 2024005,
        telefone: "44999990005",
        curso: "Ciência da Computação",
        periodo: 6,
        data_nasc: new Date("1999-07-09"),
        endereco_aluno: 5,
    },
];

export async function seedAlunos(): Promise<void> {
    const alunoRepo = AppDataSource.getRepository(AlunosEntity);

    const alunosSeeds = await Promise.all(
        alunosBase.map(async (aluno) => {
            const senhaHash = await hash(aluno.ra.toString(), 8);

            return {
                nome: aluno.nome,
                cpf: aluno.cpf,
                email: aluno.email,
                ra: aluno.ra,
                senha: senhaHash,
                telefone: aluno.telefone,
                curso: aluno.curso,
                periodo: aluno.periodo,
                data_nasc: aluno.data_nasc,
                endereco_aluno: aluno.endereco_aluno,
            };
        })
    );

    await alunoRepo.save(alunosSeeds);
}

