import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: "candidaturas"})
export class CandidaturasEntity{
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({type: "integer"})
    aluno_id!: number;

    @Column({type: "integer"})
    vagas_id!: number;

    @Column({type: "date"})
    data_candidatura!: Date;

}

export default CandidaturasEntity;