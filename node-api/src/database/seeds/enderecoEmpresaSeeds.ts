import 'reflect-metadata';
import { AppDataSource } from '../data-source';
import { EnderecoEmpresaEntity } from '../../models/EnderecoEmpresaEntity';


const enderecos = [
  {
    id: 1,
    logradouro: 'Avenida Brasil',
    num_logradouro: '2000',
    bairro: 'Centro',
    cep: '87500000',
    cidade: 'Umuarama',
    uf: 'PR',
  },
  {
    id: 2,
    logradouro: 'Rua das Flores',
    num_logradouro: '150',
    bairro: 'Zona II',
    cep: '87502000',
    cidade: 'Umuarama',
    uf: 'PR',
  },
  {
    id: 3,
    logradouro: 'Avenida Industrial',
    num_logradouro: '980',
    bairro: 'Distrito Industrial',
    cep: '87510000',
    cidade: 'Umuarama',
    uf: 'PR',
  },
];

export async function seedEnderecoEmpresa(): Promise<void> {
  const repo = AppDataSource.getRepository(EnderecoEmpresaEntity);
  const enderecosSeeds = await Promise.all(
    enderecos.map(async (enderecos) => {
      return {
        id: enderecos.id,
        logradouro: enderecos.logradouro,
        num_logradouro: enderecos.num_logradouro,
        bairro: enderecos.bairro,
        cep: enderecos.cep,
        cidade: enderecos.cidade,
        uf: enderecos.uf
      }
    })
  )

  await repo.save(enderecosSeeds);
}