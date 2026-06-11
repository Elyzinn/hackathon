import { MigrationInterface, QueryRunner, Table, TableForeignKey } from "typeorm";

export class CreateTableEmpresas1781144178064 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
         await queryRunner.createTable(
            new Table({
                name: "empresas",
                columns: [
                    { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                    { name: "razao_social", type: "varchar", length: "100", isNullable: false },
                    { name: "cnpj", type: "varchar", length: "14", isNullable: false },
                    { name: "email", type: "varchar", length: "100", isNullable: false },
                    { name: "telefone_contato", type: "varchar", length: "11", isNullable: false },
                    { name: "responsavel", type: "varchar", length: "100", isNullable: false },
                    { name: "status", type: "tinyint", isNullable: false },
                    { name: "endereco_empresa", type: "int", isNullable: false }
                ]
            })
        );

        await queryRunner.createForeignKey(
            "empresas",
            new TableForeignKey({
                name: "FK_empresas_endereco",
                columnNames: ["endereco_empresa"],
                referencedTableName: "endereco_empresa",
                referencedColumnNames: ["id"],
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            })
        );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropForeignKey("empresas", "FK_empresas_endereco");
        await queryRunner.dropTable("empresas");
    }

}
