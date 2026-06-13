import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: "empresas"})
export class CandidaturasEntity{
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({type: "varchar",})
    aluno_idaluno!: number;

    @Column({type: "varchar",})
    vagas_id!: number;

}

export default CandidaturasEntity;