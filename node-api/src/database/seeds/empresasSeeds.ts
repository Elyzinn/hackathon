import 'reflect-metadata';
import { AppDataSource } from '../data-source';
import { EmpresasEntity } from '../../models/EmpresasEntity';
import { hash } from 'bcrypt';

const empresas = [
    {
        razao_social: 'TechSoft Solutions Ltda',
        cnpj: '12345678000101',
        email: 'contato@techsoft.com.br',
        telefone_contato: '44999880001',
        responsavel: 'Carlos Eduardo Mendes',
        status: false,
        endereco_empresa: 1,
    },
    {
        razao_social: 'Inovação Digital ME',
        cnpj: '23456789000102',
        email: 'rh@inovacaodigital.com.br',
        telefone_contato: '44999880002',
        responsavel: 'Fernanda Rocha',
        status: true,
        endereco_empresa: 2,
    },
    {
        razao_social: 'Sistemas Paraná S.A.',
        cnpj: '34567890000103',
        email: 'vagas@sistemaspr.com.br',
        telefone_contato: '44999880003',
        responsavel: 'Roberto Figueiredo',
        status: true,
        endereco_empresa: 3,
    },
];

export async function seedEmpresas(): Promise<void> {
    const empresaRepo = AppDataSource.getRepository(EmpresasEntity);

    const empresasSeeds = await Promise.all(
        empresas.map(async (empresa) => {
            const senhaHash = await hash(empresa.cnpj, 8);

            return {
                razao_social: empresa.razao_social,
                cnpj: empresa.cnpj,
                email: empresa.email,
                senha: senhaHash,
                telefone_contato: empresa.telefone_contato,
                responsavel: empresa.responsavel,
                status: empresa.status,
                endereco_empresa: empresa.endereco_empresa,
            };
        })
    );

    await empresaRepo.save(empresasSeeds);
}
