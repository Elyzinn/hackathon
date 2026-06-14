<?php
    ob_start();
    session_start();
    require_once 'classes/Empresa.php';

    if($_SERVER['REQUEST_METHOD'] === 'POST'){
        $empresa = new Empresa();
        $res = $empresa->logar($_POST['cnpj'], $_POST['senha'], $_POST['email']);

        if($aluno->resHttp($res)){
            $_SESSION['empresa_cnpj'] = $_POST['cnpj'];
            $_SESSION['empresa_email'] = $_POST['email'];
            $_SESSION['empresalogada'] = true;

            header('Location: estagioEmpresa.php');
        }else{
            $_SESSION['erro_login'] = 'CPNJ, email ou senha invalidos';
            ob_end_clean();
            header('Location: login-empresa.php');
        }
        exit;
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
            
            <form action="processa_login.php" method="POST">
                
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

                <div class="links-uteis">
                    <a href="recuperarSenhaE.php">Esqueceu sua senha?</a><br>
                    <a href="cadastroEmpresa.php">Criar novo cadastro</a>
                </div>

            </form>
        </div>
    </div>

</body>
</html>