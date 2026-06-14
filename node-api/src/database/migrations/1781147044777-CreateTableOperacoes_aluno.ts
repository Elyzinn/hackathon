import { MigrationInterface, QueryRunner, Table, TableForeignKey } from "typeorm";

export class CreateTableOperacoesAluno1781147044777 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(
            new Table({
                name: "operacoes_aluno",
                columns: [
                    { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                    { name: "descricao", type: "varchar", length: "100", isNullable: false },
                    { name: "data", type: "date", isNullable: false },
                    { name: "operadores_id", type: "int", isNullable: false },
                    { name: "aluno_id", type: "int", isNullable: false }
                ]
            })
        );

        await queryRunner.createForeignKey(
            "operacoes_aluno",
            new TableForeignKey({
                name: "FK_op_aluno_operador",
                columnNames: ["operadores_id"],
                referencedTableName: "operadores",
                referencedColumnNames: ["id"],
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            })
        );

        await queryRunner.createForeignKey(
            "operacoes_aluno",
            new TableForeignKey({
                name: "FK_op_aluno_aluno",
                columnNames: ["aluno_id"],
                referencedTableName: "alunos",
                referencedColumnNames: ["id"],
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
            })
        );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropForeignKey("operacoes_aluno", "FK_op_aluno_operador");
        await queryRunner.dropForeignKey("operacoes_aluno", "FK_op_aluno_aluno");
        await queryRunner.dropTable("operacoes_aluno");
    }

}
