import { Column, 
    Entity, 
    PrimaryGeneratedColumn, 
} from "typeorm";

@Entity({name : "operacoes_empresa"})
export class OperacoesEmpresasEntity { 
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "varchar",})
    descricao!: string;

    @Column({ type: "datetime"})
    date_operacao!: Date;

    @Column({ type: "integer"})
    operadores_id!: number;

    @Column({ type: "integer"})
    empresas_id!: number;

}

export default OperacoesEmpresasEntity;