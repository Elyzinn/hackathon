import { Column, 
    Entity, 
    PrimaryGeneratedColumn, 
} from "typeorm";

@Entity({name : "endereco_aluno"})
export class EnderecoAlunoEntity { 
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "varchar",})
    logadouro!: string;

    @Column({ type: "varchar"})
    num_logadouro!: number;

    @Column({ type: "varchar"})
    bairro!: string;

    @Column({ type: "varchar"})
    cep!: string;

    @Column({ type: "varchar"})
    cidade!: string;

    @Column({ type: "varchar"})
    uf!: string;

}

export default EnderecoAlunoEntity;