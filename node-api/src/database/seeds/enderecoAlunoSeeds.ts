import "reflect-metadata";
import { AppDataSource } from "../data-source";
import { EnderecoAlunoEntity } from "../../models/EnderecoAlunoEntity";

const enderecosBase = [
    {
        id: 1,
        logradouro: "Avenida Paraná",
        num_logradouro: "1500",
        bairro: "Zona I",
        cep: "87501000",
        cidade: "Umuarama",
        uf: "PR"
    },
    {
        id: 2,
        logradouro: "Rua Cambé",
        num_logradouro: "420",
        bairro: "Zona II",
        cep: "87502110",
        cidade: "Umuarama",
        uf: "PR"
    },
    {
        id: 3,
        logradouro: "Avenida Maringá",
        num_logradouro: "289",
        bairro: "Zona III",
        cep: "87503050",
        cidade: "Umuarama",
        uf: "PR"
    },
    {
        id: 4,
        logradouro: "Rua Ministro Oliveira Salazar",
        num_logradouro: "3105",
        bairro: "Centro",
        cep: "87505200",
        cidade: "Umuarama",
        uf: "PR"
    },
    {
        id: 5,
        logradouro: "Avenida Apucarana",
        num_logradouro: "88",
        bairro: "Zona I",
        cep: "87501010",
        cidade: "Umuarama",
        uf: "PR"
    },
];

export async function seedEnderecoAluno(): Promise<void> {
    const enderecoRepo = AppDataSource.getRepository(EnderecoAlunoEntity);

    const enderecosSeeds = await Promise.all(
        enderecosBase.map(async (endereco) => {
            return {
                id: endereco.id,
                logradouro: endereco.logradouro,
                num_logradouro: endereco.num_logradouro,
                bairro: endereco.bairro,
                cep: endereco.cep,
                cidade: endereco.cidade,
                uf: endereco.uf,
            };
        })
    );

   
    await enderecoRepo.save(enderecosSeeds);
}
