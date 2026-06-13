import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: "contratos"})
export class ContratosEntity{
    @PrimaryGeneratedColumn()
    vagas_id!: number;

    @Column({type: "varchar",})
    aluno_id!: number;

    @Column({type: "date",})
    date_inicio!: Date;

    @Column({type: "date",})
    data_fim!: Date;

}

export default ContratosEntity;