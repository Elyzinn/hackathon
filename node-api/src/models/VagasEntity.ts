import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: "vagas"})
export class VagasEntity{
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({type: "varchar",})
    cargo!: string;

    @Column({type: "varchar",})
    vaga_preenchida!: boolean;

    @Column({type: "date",})
    data_abertura!: Date;

    @Column({type: "date",})
    data_fechamento!: Date;
    
    @Column({type: "varchar",})
    empresas_id!: number;

}

export default VagasEntity;