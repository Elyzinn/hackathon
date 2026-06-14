import "reflect-metadata";
import { AppDataSource } from "../data-source";
import { OperadoresEntity } from "../../models/OperadoresEntity";

const operadores =[
    {  
      nome: 'Mariana Souza',
      cargo: 'Analista de Estágios',
      data_contratacao: new Date('2022-03-01'),
      data_recisao: new Date('2099-12-31'), // data futura = ainda ativo
    },
    { 
      nome: 'João Paulo Lima',
      cargo: 'Coordenador de Parcerias',
      data_contratacao: new Date('2021-07-15'),
      data_recisao: new Date('2029-07-15'),
    },
    {
      nome: 'Patricia Oliveira',
      cargo: 'Assistente Administrativo',
      data_contratacao: new Date('2023-01-10'),
      data_recisao: new Date('2024-06-30'), // encerrado
    },
]

export async function seedOperadores() {
    const operadoresRepo = AppDataSource.getRepository(OperadoresEntity);

    const operadoresSeeds = await Promise.all(
        operadores.map(async (operadores) => {
            return{
                nome: operadores.nome,
                cargo: operadores.cargo,
                data_contratacao: operadores.data_contratacao,
                data_recisao: operadores.data_recisao,
            };
        })
    );
    await operadoresRepo.save(operadoresSeeds);
}