import { MigrationInterface, QueryRunner, Table, TableForeignKey } from "typeorm";

export class CreateTableContratos1781146099886 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(
                    new Table({
                        name: "contratos",
                        columns: [
                            { name: "id", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                            { name: "vagas_id", type: "int", isNullable: false  },
                            { name: "aluno_id", type: "int", isNullable: false },
                            { name: "date_inicio", type: "date", isNullable: false },
                            { name: "data_fim", type: "date", isNullable: false }
                        ]
                    })
                );
        
                await queryRunner.createForeignKey(
                    "contratos",
                    new TableForeignKey({
                        name: "FK_contrato_vagas",
                        columnNames: ["vagas_id"],
                        referencedTableName: "vagas",
                        referencedColumnNames: ["id"],
                        onDelete: "CASCADE",
                        onUpdate: "CASCADE"
                    })
                );
        
                await queryRunner.createForeignKey(
                    "contratos",
                    new TableForeignKey({
                        name: "FK_contrato_aluno",
                        columnNames: ["aluno_id"],
                        referencedTableName: "alunos",
                        referencedColumnNames: ["id"],
                        onDelete: "CASCADE",
                        onUpdate: "CASCADE"
                    })
                );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropForeignKey("contratos", "FK_contrato_vagas");
        await queryRunner.dropForeignKey("contratos", "FK_contrato_aluno");
        await queryRunner.dropTable("contratos");
    }

}
