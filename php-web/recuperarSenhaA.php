<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperar Senha - UniALFA</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="cadastro-container">
        <div class="cadastro-card">
            <h2>RECUPERAR SENHA</h2>
            
            <div id="msg-sucesso" style="display: none;" class="msg-alerta-sucesso">
                Solicitação enviada! A equipe de suporte da UniAlfa analisará o seu caso e entrará em contato através do e-mail informado o mais breve possível.
            </div>

            <form id="form-recuperar" onsubmit="executarRecuperacao(event)">
                <div class="cadastro-group">
                    <label for="email">E-mail Cadastrado</label>
                    <input type="email" id="email" required placeholder="Digite o e-mail da sua conta">
                </div>

                <button type="submit" class="btn-cadastro">Enviar Solicitação</button>
            </form>

            <div class="cadastro-links">
                <a href="login-aluno.php">Voltar para o Login</a>
            </div>
        </div>
    </div>

    <script>
    function executarRecuperacao(event) {
        event.preventDefault();
        document.getElementById('form-recuperar').style.display = 'none';
        document.getElementById('msg-sucesso').style.display = 'block';
    }
    </script>

</body>
</html>