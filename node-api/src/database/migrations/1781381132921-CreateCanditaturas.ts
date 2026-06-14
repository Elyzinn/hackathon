import { MigrationInterface, QueryRunner, Table, TableForeignKey } from "typeorm";

export class CreateTableCandidaturas1781144449520 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
         await queryRunner.createTable(
            new Table({
                name: "candidaturas",
                columns: [
                    { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                    { name: "aluno_id", type: "int", isNullable: false  },
                    { name: "vagas_id", type: "int", isNullable: false }
                ]
            })
        );

        await queryRunner.createForeignKey(
            "candidaturas",
            new TableForeignKey({
                name: "FK_candidatura_aluno",
                columnNames: ["aluno_id"],
                referencedTableName: "alunos",
                referencedColumnNames: ["id"],
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            })
        );

        await queryRunner.createForeignKey(
            "candidaturas",
            new TableForeignKey({
                name: "FK_candidatura_vagas",
                columnNames: ["vagas_id"],
                referencedTableName: "vagas",
                referencedColumnNames: ["id"],
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            })
        );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropForeignKey("candidaturas", "FK_candidatura_aluno");
        await queryRunner.dropForeignKey("candidaturas", "FK_candidatura_vagas");
        await queryRunner.dropTable("candidaturas");
    }

}
