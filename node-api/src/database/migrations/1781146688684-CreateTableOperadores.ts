import { MigrationInterface, QueryRunner, Table } from "typeorm";

export class CreateTableOperadores1781146688684 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(
                    new Table({
                        name: "operadores",
                        columns: [
                            { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                            { name: "nome", type: "varchar", length: "100", isNullable: false },
                            { name: "cargo", type: "varchar", length: "100", isNullable: false },
                            { name: "data_contratacao", type: "date", isNullable: false },
                            { name: "data_recisao", type: "date", isNullable: false }
                        ]
                    })
                );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropTable("operadores");
    }

}
