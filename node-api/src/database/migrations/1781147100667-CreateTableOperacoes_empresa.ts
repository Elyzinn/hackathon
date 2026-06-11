import { MigrationInterface, QueryRunner, Table, TableForeignKey  } from "typeorm";

export class CreateTableOperacoesEmpresa1781147100667 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(
                                    new Table({
                                        name: "operacoes_empresa",
                                        columns: [
                                            { name: "idoperacoes_aluno", type: "int", isPrimary: true, isGenerated: true, generationStrategy: "increment" },
                                            { name: "descricao", type: "varchar", length: "100", isNullable: false  },
                                            { name: "date_operacao", type: "datetime", isNullable: false },
                                            { name: "operadores_id", type: "int", isNullable: false },
                                            { name: "empresas_id", type: "int", isNullable: false }
                                        ]
                                    })
                                );
                        
                                await queryRunner.createForeignKey(
                                    "operacoes_empresa",
                                    new TableForeignKey({
                                        name: "FK_op_empresa_operador",
                                        columnNames: ["operadores_id"],
                                        referencedTableName: "operadores",
                                        referencedColumnNames: ["id"],
                                        onDelete: "CASCADE",
                                        onUpdate: "CASCADE"
                                    })
                                );
                        
                                await queryRunner.createForeignKey(
                                    "operacoes_empresa",
                                    new TableForeignKey({
                                        name: "FK_op_empresa_empresa",
                                        columnNames: ["empresas_id"],
                                        referencedTableName: "empresas",
                                        referencedColumnNames: ["id"],
                                        onDelete: "CASCADE",
                                        onUpdate: "CASCADE"
                                    })
                                );
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropForeignKey("operacoes_empresa", "FK_op_aluno_operador");
        await queryRunner.dropForeignKey("operacoes_empresa", "FK_op_aluno_aluno");
        await queryRunner.dropTable("operacoes_empresa");
    }

}
