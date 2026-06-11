import { MigrationInterface, QueryRunner, Table } from "typeorm";

export class CreateTableEnderecoEmpresa1781142625727 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
            await queryRunner.createTable(
            new Table({
                name: "endereco_empresa",
                columns: [
                    { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                    { name: "logradouro", type: "varchar", length: "100", isNullable: false },
                    { name: "num_logradouro", type: "varchar", length: "6", isNullable: false },
                    { name: "bairro", type: "varchar", length: "100", isNullable: false },
                    { name: "cep", type: "varchar", length: "8", isNullable: false },
                    { name: "cidade", type: "varchar", length: "100" , isNullable: false },
                    { name: "uf", type: "varchar", length: "100" ,isNullable: false } 
                ]
            })
       );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropTable("endereco_empresa");
    }

}
