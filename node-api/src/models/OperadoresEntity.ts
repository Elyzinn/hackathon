import { Column, 
    Entity, 
    PrimaryGeneratedColumn, 
} from "typeorm";

@Entity({name : "operadores"})
export class OperadoresEntity { 
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "varchar",})
    nome!: string;

    @Column({ type: "varchar"})
    cargo!: string;

    @Column({type: "date",})
    data_contratacao!: Date;

    @Column({type: "date",})
    data_recisao!: Date;
}

export default OperadoresEntity;