import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity({name: "vagas"})
export class VagasEntity{
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({type: "varchar",})
    cargo!: string;

    @Column({type: "boolean"})
    vaga_preenchida!: boolean;

    @Column({type: "date",})
    data_abertura!: Date;

    @Column({type: "date",})
    data_fechamento!: Date;

    @Column({type: "varchar", length: 200})
    requisitos!: string;
    
    @Column({type: "integer",})
    empresas_id!: number;
}

export default VagasEntity;