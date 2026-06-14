import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: "empresas"})
export class EmpresasEntity{
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({type: "varchar",})
    razao_social!: string;

    @Column({type: "varchar",})
    cnpj!: string;

    @Column({type: "varchar",})
    email!: string;

    @Column({type: "varchar",})
    senha!: string;
    
    @Column({type: "varchar",})
    telefone_contato!: string;

    @Column({type: "varchar",})
    responsavel!: string;

    @Column({type: "boolean"})
    status!: boolean;

    @Column({type: "integer"})
    endereco_empresa!:number;
}

export type EmpresaPublico = Omit<EmpresasEntity, "senha">;