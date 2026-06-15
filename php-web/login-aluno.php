<?php
    session_start();
    require_once 'classes/Aluno.php';

    if ($_SERVER['REQUEST_METHOD'] === 'POST'){
        $aluno = new Aluno();
        $res = $aluno->logar($_POST['cpf'],$_POST['senha'], $_POST['email']);
        //var_dump($res);
        //die();
        //var_dump($aluno->loginSucesso($res));
        if ($aluno->loginSucesso($res)){
            $_SESSION['aluno_cpf'] = $_POST['cpf'];
            $_SESSION['aluno_email'] = $_POST['email'];
            $_SESSION['alunoLogado'] = true;
            //var_dump($_SESSION); 
            header('Location: estagioAluno.php');
        }else{
            $mensagem = 'Login inválido. Verifique seus dados e tente novamente.';
            $tipo_msg = 'erro';
        }
    }

?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portal de Estágios UniALFA - Aluno</title>
    <link rel="stylesheet" href="css/style.css">
    </head>
<body>

    <div class="login-container">
        <div class="login-card">
            <h2>ESTÁGIO - ALUNO</h2>
            
            <form method="POST">
                
                <div class="input-group">
                    <span class="icon-box user-icon"></span> 
                    <input type="text" name="cpf" placeholder="CPF" required>                    
                </div>

                <div class="input-group">
                    <span class="icon-box lock-icon"></span> 
                    <input type="password" name="senha" placeholder="Senha" required>
                </div>

                <div class="input-group">
                    <span class="icon-box email-icon"></span> 
                    <input type="email" name="email" placeholder="Email" required>
                    
                </div>
                <?php if(!empty($mensagem)): ?>
                    <div class="msg-alerta-<?= $tipo_msg ?>">
                        <?= htmlspecialchars($mensagem) ?>
                    </div>
                <?php endif; ?>
                  <button type="submit" class="btn-entrar">Entrar</button>

                <div class="links-uteis">
                    <a href="recuperarSenhaA.php">Esqueceu sua senha?</a><br>
                </div>
            </form>
        </div>
    </div>

</body>
</html>