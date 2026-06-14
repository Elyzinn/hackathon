import { Column, 
    Entity, 
    PrimaryGeneratedColumn, 
} from "typeorm";

@Entity({name : "endereco_empresa"})
export class EnderecoEmpresaEntity { 
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "varchar",})
    logradouro!: string;

    @Column({ type: "varchar"})
    num_logradouro!: string;

    @Column({ type: "varchar"})
    bairro!: string;

    @Column({ type: "varchar"})
    cep!: string;

    @Column({ type: "varchar"})
    cidade!: string;

    @Column({ type: "varchar"})
    uf!: string;

}

export default EnderecoEmpresaEntity;