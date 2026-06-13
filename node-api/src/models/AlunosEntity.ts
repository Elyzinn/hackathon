import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: "alunos"})
export class AlunosEntity{
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({type: "varchar",})
    nome!: string;

    @Column({type: "varchar",})
    cpf!: string;

    @Column({type: "varchar",})
    email!: string;
    
    @Column({type: "varchar",})
    ra!: number;

    @Column({type: "varchar",})
    senha!: string;

    @Column({type: "varchar"})
    telefone!:string;

    @Column({type: "varchar"})
    curso!:string;

    @Column({type: "integer"})
    periodo!:number;
    
    @Column({type: "date"})
    data_nasc!:Date

    @Column({type: "integer"})
    endereco_aluno!:number;
}

export type AlunoPublico = Omit<AlunosEntity, "senha">;