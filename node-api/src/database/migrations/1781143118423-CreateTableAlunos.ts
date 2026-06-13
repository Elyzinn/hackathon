import { MigrationInterface, QueryRunner, Table, TableForeignKey } from "typeorm";

export class CreateTableAlunos1781143118423 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(
            new Table({
                name: "alunos",
                columns: [
                    { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                    { name: "nome", type: "varchar", length: "100", isNullable: false },
                    { name: "cpf", type: "varchar", length: "11", isNullable: false },
                    { name: "email", type: "varchar", length: "100", isNullable: false },
                    { name: "ra", type: "int", isNullable: false },
                    { name: "senha", type: "varchar", length: "100", isNullable: false },
                    { name: "telefone", type: "varchar", length: "11", isNullable: false },
                    { name: "curso", type: "varchar", length: "100", isNullable: false },
                    { name: "periodo", type: "int", isNullable: false },
                    { name: "data_nasc", type: "date", isNullable: false },
                    { name: "endereco_aluno", type: "int", isNullable: false }
                    ]
                })
            );

             await queryRunner.createForeignKey(
            "alunos",
            new TableForeignKey({
                name: "FK_aluno_endereco",
                columnNames: ["endereco_aluno"],
                referencedTableName: "endereco_aluno",
                referencedColumnNames: ["id"],
                onDelete: "CASCADE",
                onUpdate: "CASCADE"
                })
            );
        }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropForeignKey("alunos", "FK_aluno_endereco");
        await queryRunner.dropTable("alunos");
    }
}
