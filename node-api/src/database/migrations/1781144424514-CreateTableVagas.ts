import { MigrationInterface, QueryRunner, Table, TableForeignKey } from "typeorm";

export class CreateTableVagas1781144424514 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(
            new Table({
                name: "vagas",
                columns: [
                    { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                    { name: "cargo", type: "varchar", length: "100", isNullable: false },
                    { name: "vaga_preenchida", type: "varchar", length: "14", isNullable: false },
                    { name: "data_abertura", type: "date", isNullable: false },
                    { name: "data_fechamento", type: "date", isNullable: false },
                    { name: "empresas_id", type: "int", isNullable: false }
                ]
            })
        );

        await queryRunner.createForeignKey(
            "vagas",
            new TableForeignKey({
                name: "FK_vagas_empresas",
                columnNames: ["empresas_id"],
                referencedTableName: "empresas",
                referencedColumnNames: ["id"],
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            })
        );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropForeignKey("vagas", "FK_vagas_empresas");
        await queryRunner.dropTable("vagas");
    }

}
