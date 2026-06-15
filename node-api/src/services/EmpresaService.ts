import * as bcrypt from "bcrypt";
import { IEmpresaRepository } from "../repositories/EmpresaRepository";
import { EmpresaPublico, EmpresasEntity } from "../models/EmpresasEntity";
import { EnderecoEmpresaEntity } from "../models/EnderecoEmpresaEntity";
import AppError from "../utils/AppErrors";

export class EmpresaService {
    constructor(private readonly empresaRepo: IEmpresaRepository) { }

    async listarEmpresasPorId(id: number): Promise<EmpresaPublico> {
        const empresa = await this.empresaRepo.getEmpresaById(id);
        if (!empresa) {
            throw new AppError(404, "Empresa não encontrada");
        }
        const { senha: _s, ...empresaPublico } = empresa;
        return empresaPublico;
    }

    async loginEmpresa(dados: { cnpj: string; senha: string; email: string }): Promise<EmpresasEntity> {
        const empresa = await this.empresaRepo.getEmpresaByCnpj(dados.cnpj);
        if (!empresa) {
            throw new AppError(404, "Empresa não encontrada");
        }
        const senhaVerficada = await bcrypt.compare(dados.senha, empresa.senha);
        if (senhaVerficada == false) {
            throw new AppError(401, "Credenciais erradas");
        }
        return empresa;
    }

    async criarEmpresa(dados: {
        razao_social: string; cnpj: string; email: string;
        telefone_contato: string; responsavel: string; senha: string;
        cep: string; logradouro: string; numero: string;
        bairro: string; cidade: string; uf: string;
    }): Promise<EmpresaPublico> {
        const cnpjExitente = await this.empresaRepo.getEmpresaByCnpj(dados.cnpj);
        if (cnpjExitente) {
            throw new AppError(409, "CNPJ já cadastrado");
        }

        const senhaHash = await bcrypt.hash(dados.senha, 8);

        const dadosEmpresa = {
            razao_social: dados.razao_social,
            cnpj: dados.cnpj,
            email: dados.email,
            telefone_contato: dados.telefone_contato,
            responsavel: dados.responsavel,
            senha: senhaHash,
            status: false,
        } as Omit<EmpresasEntity, "id">;

        const dadosEndereco = {
            cep: dados.cep,
            logradouro: dados.logradouro,
            num_logradouro: dados.numero,
            bairro: dados.bairro,
            cidade: dados.cidade,
            uf: dados.uf,
        } as Omit<EnderecoEmpresaEntity, "id">;

        const empresa = await this.empresaRepo.createEmpresa(dadosEmpresa, dadosEndereco);

        const {senha: _s, ...empresaPublico } = empresa;
        return empresaPublico;
    }
}