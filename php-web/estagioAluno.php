<?php
    session_start();
    
    if (!isset($_SESSION['alunoLogado']) || $_SESSION['alunoLogado'] !== true){
        header('Location: login-aluno.php');
        exit;
    }
?>


<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel do Aluno - UniALFA</title>
    
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>

    <div class="dashboard-container">
        
        <header class="top-header">
            <div class="logo-area"><img src="img/unialfa.png" alt="Logo UniALFA" class="logo-main"></div>
            <div class="user-profile">Bem vindo, Aluno</div>
        </header>

        <nav class="sidebar">
            <ul>
                <li><a href="#">Painel Geral</a></li>
                <li><a href="#">Buscar Vagas</a></li>
                <li><a href="#">Minhas Candidaturas</a></li>
                <li><a href="#">Meu Currículo</a></li>
                <li class="menu-sair"><a href="index.php"> Sair</a></li>
            </ul>
        </nav>

        <main class="main-content">
            
            <div class="hero-banner">
                <h2>Vagas e Oportunidades de Estágio</h2>
                <p>Encontre a oportunidade ideal para iniciar sua carreira no mercado de trabalho.</p>
            </div>

            <div class="content-grid">
                <div class="card-box">
                    <h3>Vagas Disponíveis para seu Curso</h3>
                    <p style="color: #000000;">Nenhuma vaga correspondente encontrada no momento.</p>
                </div>

                <div class="card-box">
                    <h3>Alertas e Prazos</h3>
                    <p style="color: #000000;">Fique atento aos prazos de entrega dos relatórios de estágio.</p>
                </div>
            </div>

        </main>
    </div>

</body>
</html>