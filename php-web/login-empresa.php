<?php
    ob_start();    
    session_start();
    require_once 'classes/Empresa.php';
  
    if($_SERVER['REQUEST_METHOD'] === 'POST'){
        $empresa = new Empresa();
        $res = $empresa->logar($_POST['cnpj'], $_POST['senha'], $_POST['email']);
        //var_dump($res);
        //var_dump($empresa->resHttp($res));
        session_unset(); 
        session_destroy();
        session_start();

        if ($empresa->loginSucess($res)){
            $id = $res['data']['loginSemSenha']['id'];
            $_SESSION['empresa_cnpj'] = $_POST['cnpj'];
            $_SESSION['empresa_email'] = $_POST['email'];
            $_SESSION['empresaLogado'] = true;
            $_SESSION['empresa_id'] = $id;

            //var_dump($_SESSION);
            header('Location: estagioEmpresa.php?id=' . $id);
        }else{
           $mensagem = 'Erro ao entrar no cadastros empresa. Verifique os dados e tente novamente.';
           $tipo_msg = 'erro'; 
        }
    }
    ob_end_flush();    
?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portal de Estágios UniALFA - Empresa</title>
    <link rel="stylesheet" href="css/style.css">
    </head>
<body>

    <div class="login-container">
        <div class="login-card">
            <h2>ESTÁGIO - EMPRESA</h2>
            
            <form method="POST">
                
                <div class="input-group">
                    <span class="icon-box CNPJ-icon"></span>
                    <input type="text" name="cnpj" placeholder="CNPJ" required>
                </div>

                <div class="input-group">
                    <span class="icon-box lock-icon"></span>
                    <input type="password" name="senha" placeholder="Senha" required>
                </div>

                <div class="input-group">
                    <span class="icon-box email-icon"></span>
                    <input type="text" name="email" placeholder="email" required>
                </div>

                    <button type="submit" class="btn-entrar">Entrar</button>

                <?php if(!empty($mensagem)): ?>
                    <div class="msg-alerta-<?= $tipo_msg ?>">
                        <?= htmlspecialchars($mensagem) ?>
                    </div>
                <?php endif; ?>
                <div class="links-uteis">
                    <a href="recuperarSenhaE.php">Esqueceu sua senha?</a><br>
                    <a href="cadastroEmpresa.php">Criar novo cadastro</a>
                </div>

            </form>
        </div>
    </div>

</body>
</html>