import { Column, 
    Entity, 
    PrimaryGeneratedColumn, 
} from "typeorm";

@Entity({name : "operacoes_aluno"})
export class OperadocoesAlunosEntity { 
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "varchar",})
    descricao!: string;

    @Column({ type: "datetime"})
    data!: Date;

    @Column({ type: "integer"})
    operadores_id!: number;

    @Column({ type: "integer"})
    aluno_id!: number;

}

export default OperadocoesAlunosEntity;