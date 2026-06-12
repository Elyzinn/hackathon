<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Aluno - UniALFA</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="pag-formulario-longo">
    <div class="cadastro-container">
        <div class="cadastro-card">
            <h2>CADASTRO DE ALUNO</h2>
            
            <div id="msg-sucesso" style="display: none;" class="msg-alerta-sucesso">
                Aluno cadastrado com sucesso!!!
            </div>

            <form id="form-aluno" onsubmit="salvarAlunoCRUD(event)">
                <div class="cadastro-group">
                    <label for="nome">Nome Completo</label>
                    <input type="text" id="nome" maxlength="100" required placeholder="Digite seu nome completo">
                </div>

                <div class="cadastro-group">
                    <label for="cpf">CPF (Apenas números)</label>
                    <input type="text" id="cpf" maxlength="11" required placeholder="Ex: 12345678901">
                </div>

                <div class="cadastro-group">
                    <label for="email">E-mail</label>
                    <input type="email" id="email" maxlength="100" required placeholder="seuemail@exemplo.com">
                </div>

                <div class="cadastro-group">
                    <label for="telefone">Telefone (Com DDD, apenas números)</label>
                    <input type="tel" id="telefone" maxlength="11" required placeholder="Ex: 44999999999">
                </div>

                <div class="cadastro-group">
                    <label for="curso">Curso</label>
                    <input type="text" id="curso" maxlength="100" required placeholder="Ex: Análise e Desenvolvimento de Sistemas">
                </div>

                <div class="cadastro-group">
                    <label for="periodo">Período (Semestre)</label>
                    <input type="number" id="periodo" min="1" max="12" required placeholder="Ex: 1">
                </div>

                <div class="cadastro-group">
                    <label for="data_nasc">Data de Nascimento</label>
                    <input type="date" id="data_nasc" required>
                </div>

                <div class="cadastro-group">
                    <label for="senha">Criar Senha</label>
                    <input type="password" id="senha" required placeholder="Crie uma senha segura">
                </div>

                <button type="submit" class="btn-cadastro">Cadastrar Aluno</button>
            </form>

            <div class="cadastro-links">
                <a href="login-aluno.php">Já tem uma conta? Faça Login</a>
            </div>
        </div>
    </div>

    <script>
    function salvarAlunoCRUD(event) {
        event.preventDefault();
        
        // Objeto mapeado exatamente igual às colunas da tabela do banco
        const novoAluno = {
            nome: document.getElementById('nome').value,
            cpf: document.getElementById('cpf').value,
            email: document.getElementById('email').value,
            telefone: document.getElementById('telefone').value,
            curso: document.getElementById('curso').value,
            periodo: parseInt(document.getElementById('periodo').value),
            data_nasc: document.getElementById('data_nasc').value,
            senha_simulada: document.getElementById('senha').value,
            endereco_aluno: null // Será gerado quando houver a tabela de endereços
        };

        // CRUD - Create local no navegador
        localStorage.setItem('bd_aluno_atual', JSON.stringify(novoAluno));

        document.getElementById('form-aluno').style.display = 'none';
        document.getElementById('msg-sucesso').style.display = 'block';
        
        console.log("Dados salvos simulando o Banco de Dados:", novoAluno);
    }
    </script>

</body>
</html>