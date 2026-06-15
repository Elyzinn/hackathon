<?php
    require_once 'classes/Empresa.php';   
    if($_SERVER['REQUEST_METHOD'] === 'POST'){
        $empresa = new Empresa();
        $res = $empresa->registar(
            $_POST['razao_social'], 
            $_POST['cnpj'], 
            $_POST['email'], 
            $_POST['telefone_contato'], 
            $_POST['responsavel'],
            $_POST['senha'],
            $_POST['cep'],
            $_POST['logradouro'],
            $_POST['numero'],
            $_POST['bairro'],
            $_POST['cidade'],
            $_POST['estado']
            );
        if($empresa->resHttp($res)){
            $mensagem = 'Empresa cadastrada com sucesso! Aguardando Aprovação.';
            $tipo_msg = 'sucesso';
        } else {
            $mensagem = 'Erro ao cadastrar empresa. Verifique os dados e tente novamente.';
            $tipo_msg = 'erro';
        }
        //var_dump($res);
    }

?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Empresa - UniALFA</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script.js" defer></script>
</head>
<body class="pag-formulario-longo">
    <div class="cadastro-container">
        <div class="cadastro-card">
            <h2>CADASTRO DE EMPRESA</h2>
            
            <div id="msg-sucesso" style="display: none;" class="msg-alerta-sucesso">
                Empresa e Endereço cadastrados com sucesso!!!
            </div>

            <form method="POST">
                
                <div class="cadastro-group">
                    <label for="razao_social">Razão Social</label>
                    <input type="text" name="razao_social" maxlength="100" required placeholder="Nome jurídico da empresa">
                </div>

                <div class="cadastro-group">
                    <label for="cnpj">CNPJ (Apenas números)</label>
                    <input type="text" name="cnpj" maxlength="14" required placeholder="Ex: 12345678000199">
                </div>

                <div class="cadastro-group">
                    <label for="email">E-mail Corporativo</label>
                    <input type="email" name="email" maxlength="100" required placeholder="contato@empresa.com">
                </div>

                <div class="cadastro-group">
                    <label for="telefone_contato">Telefone de Contato (Apenas números)</label>
                    <input type="tel" name="telefone_contato" maxlength="11" required placeholder="Ex: 4433333333">
                </div>

                <div class="cadastro-group">
                    <label for="responsavel">Nome do Responsável / Recrutador</label>
                    <input type="text" name="responsavel" maxlength="100" required placeholder="Quem responderá pela empresa">
                </div>

                <div class="cadastro-group" style="margin-top: 25px; border-top: 1px solid rgba(255,255,255,0.2); padding-top: 15px;">
                    <label for="cep">CEP (Apenas números)</label>
                    <input type="text" id="cep" name="cep" maxlength="8" required placeholder="Ex: 87500000" onblur="buscarCep(this.value)">
                    <span id="cep-loading" style="display:none; font-size: 0.8rem; color: #aaa;">Buscando...</span>
                    <span id="cep-erro" style="display:none; font-size: 0.8rem; color: #f66;">CEP não encontrado.</span>
                </div>

                <div class="cadastro-group">
                    <label for="logradouro">Rua / Avenida</label>
                    <input type="text" id="logradouro" name="logradouro" maxlength="100" required placeholder="Ex: Av. Rio Branco">
                </div>

                <div class="cadastro-group">
                    <label for="numero">Número</label>
                    <input type="text" id="numero" name="numero" maxlength="10" required placeholder="Ex: 123 ou S/N">
                </div>

                <div class="cadastro-group">
                    <label for="bairro">Bairro</label>
                    <input type="text" id="bairro" name="bairro" maxlength="50" required placeholder="Ex: Centro">
                </div>

                <div class="cadastro-group">
                    <label for="cidade">Cidade</label>
                    <input type="text" id="cidade" name="cidade" maxlength="50" required placeholder="Ex: Umuarama">
                </div>

                <div class="cadastro-group">
                    <label for="estado">Estado (UF)</label>
                    <input type="text" id="estado" name="estado" maxlength="2" required placeholder="Ex: PR">
                </div>

                <div class="cadastro-group" style="margin-top: 20px;">
                    <label for="senha">Criar Senha</label>
                    <input type="password" name="senha" required placeholder="Crie uma senha forte (xJ#9vL$2mQ!8pZ@)">
                </div>

                <button type="submit" class="btn-cadastro">Cadastrar Empresa</button>
            </form>
            <?php if(!empty($mensagem)): ?>
                <div class="msg-alerta-<?= $tipo_msg ?>">
                    <?= htmlspecialchars($mensagem) ?>
                </div>
            <?php endif; ?>
            <div class="cadastro-links">
                <a href="login-empresa.php">Voltar para o Login da Empresa</a>
            </div>
        </div>
    </div>
</body>
</html>