import { Column, 
    Entity, 
    PrimaryGeneratedColumn, 
} from "typeorm";

@Entity({name : "operacoes_empresa"})
export class OperadoresEmpresasEntity { 
    @PrimaryGeneratedColumn()
    idoperacoes_aluno!: number;

    @Column({ type: "varchar",})
    descricao!: string;

    @Column({ type: "datetime"})
    date_operacao!: Date;

    @Column({ type: "integer"})
    operadores_id!: number;

    @Column({ type: "integer"})
    empresas_id!: number;

}

export default OperadoresEmpresasEntity;