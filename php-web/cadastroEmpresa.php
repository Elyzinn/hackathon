<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Empresa - UniALFA</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="pag-formulario-longo">
    <div class="cadastro-container">
        <div class="cadastro-card">
            <h2>CADASTRO DE EMPRESA</h2>
            
            <div id="msg-sucesso" style="display: none;" class="msg-alerta-sucesso">
                Empresa e Endereço cadastrados com sucesso!!!
            </div>

            <form id="form-empresa" onsubmit="salvarEmpresaCRUD(event)">
                
                <div class="cadastro-group">
                    <label for="razao_social">Razão Social</label>
                    <input type="text" id="razao_social" maxlength="100" required placeholder="Nome jurídico da empresa">
                </div>

                <div class="cadastro-group">
                    <label for="cnpj">CNPJ (Apenas números)</label>
                    <input type="text" id="cnpj" maxlength="14" required placeholder="Ex: 12345678000199">
                </div>

                <div class="cadastro-group">
                    <label for="email">E-mail Corporativo</label>
                    <input type="email" id="email" maxlength="100" required placeholder="contato@empresa.com">
                </div>

                <div class="cadastro-group">
                    <label for="telefone_contato">Telefone de Contato (Apenas números)</label>
                    <input type="tel" id="telefone_contato" maxlength="11" required placeholder="Ex: 4433333333">
                </div>

                <div class="cadastro-group">
                    <label for="responsavel">Nome do Responsável / Recrutador</label>
                    <input type="text" id="responsavel" maxlength="100" required placeholder="Quem responderá pela empresa">
                </div>

                <div class="cadastro-group" style="margin-top: 25px; border-top: 1px solid rgba(255,255,255,0.2); padding-top: 15px;">
                    <label for="cep">CEP (Apenas números)</label>
                    <input type="text" id="cep" maxlength="8" required placeholder="Ex: 87500000">
                </div>

                <div class="cadastro-group">
                    <label for="logradouro">Rua / Avenida</label>
                    <input type="text" id="logradouro" maxlength="100" required placeholder="Ex: Av. Rio Branco">
                </div>

                <div class="cadastro-group">
                    <label for="numero">Número</label>
                    <input type="text" id="numero" maxlength="10" required placeholder="Ex: 123 ou S/N">
                </div>

                <div class="cadastro-group">
                    <label for="bairro">Bairro</label>
                    <input type="text" id="bairro" maxlength="50" required placeholder="Ex: Centro">
                </div>

                <div class="cadastro-group">
                    <label for="cidade">Cidade</label>
                    <input type="text" id="cidade" maxlength="50" required placeholder="Ex: Umuarama">
                </div>

                <div class="cadastro-group">
                    <label for="estado">Estado (UF)</label>
                    <input type="text" id="estado" maxlength="2" required placeholder="Ex: PR">
                </div>

                <div class="cadastro-group" style="margin-top: 20px;">
                    <label for="senha">Criar Senha</label>
                    <input type="password" id="senha" required placeholder="Crie uma senha corporativa">
                </div>

                <button type="submit" class="btn-cadastro">Cadastrar Empresa</button>
            </form>

            <div class="cadastro-links">
                <a href="login-empresa.php">Voltar para o Login da Empresa</a>
            </div>
        </div>
    </div>

    <script>
    function salvarEmpresaCRUD(event) {
        event.preventDefault();

        // 1. Simulamos a criação do Endereço (que alimentaria a tabela endereco_empresa)
        const novoEndereco = {
            id_simulado: Math.floor(Math.random() * 1000) + 1, // Gera um ID fictício para a FK
            cep: document.getElementById('cep').value,
            logradouro: document.getElementById('logradouro').value,
            numero: document.getElementById('numero').value,
            bairro: document.getElementById('bairro').value,
            cidade: document.getElementById('cidade').value,
            estado: document.getElementById('estado').value
        };

        // 2. Mapeamos os dados da empresa usando o ID do endereço como Chave Estrangeira (endereco_empresa)
        const novaEmpresa = {
            razao_social: document.getElementById('razao_social').value,
            cnpj: document.getElementById('cnpj').value,
            email: document.getElementById('email').value,
            telefone_contato: document.getElementById('telefone_contato').value,
            responsavel: document.getElementById('responsavel').value,
            status: 1, 
            senha_simulada: document.getElementById('senha').value,
            
            // Aqui está a amarração da FK igualzinha ao seu banco de dados:
            endereco_empresa: novoEndereco.id_simulado 
        };

        // Salvando os objetos no localStorage para simular o Banco de Dados rodando
        localStorage.setItem('bd_endereco_empresa_atual', JSON.stringify(novoEndereco));
        localStorage.setItem('bd_empresa_atual', JSON.stringify(novaEmpresa));

        // Esconde o form e mostra o alerta de sucesso reluzente
        document.getElementById('form-empresa').style.display = 'none';
        document.getElementById('msg-sucesso').style.display = 'block';

        // Mostra no console do desenvolvedor como os dados ficaram estruturados
        console.log("Objeto Endereço criado:", novoEndereco);
        console.log("Objeto Empresa criado com a FK vinculada:", novaEmpresa);
    }
    </script>

</body>
</html>