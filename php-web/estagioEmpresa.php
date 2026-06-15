<?php
    session_start();

    if(!isset($_SESSION['empresalogada']) || !$_SESSION['empresalogada']){
        header('Location: login-empresa.php');
        exit;
    }
?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel de Empresa - UniALFA</title>
    
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>

    <div class="dashboard-container">
        
        <header class="top-header">
            <div class="logo-area"><img src="img/unialfa.png" alt="Logo UniALFA" class="logo-main"></div>
            <div class="user-profile">Bem vindo, Empresa</div>
        </header>

        <nav class="sidebar">
            <ul>
                <li><a href="#">Painel Geral</a></li>
                <li><a href="#">Mostrar suas vagas</a></li>
                <li><a href="#">Relatório de Estágios</a></li>
                <li class="menu-sair"><a href="index.php"> Sair</a></li>
            </ul>
        </nav>

        <main class="main-content">
            
            <div class="hero-banner">
                <h2>Estagiários com qualificações para as vagas</h2>
                <p>Ambiente perfeito para encontrar o estagiário qualificado que você precisa.</p>
            </div>

            <div class="content-grid">
                <div class="card-box">
                    <h3>Candidatos Disponíveis para sua Empresa</h3>
                    <p style="color: #000000;">Nenhum candidato encontrado no momento.</p>
                </div>

                <div class="card-box">
                    <h3>Vagas em Aberto</h3>
                    <p style="color: #000000;">Nenhuma vaga em aberto no momento.</p>
                </div>

                <div class="hero-banner">
                    <h2>Painel Corporativo - UniALFA</h2>
                    <p>Gerencie seus processos seletivos e encontre os melhores talentos.</p>
                        <button class="btn-anunciar-vaga" 
                    style="margin-top: 15px; 
                    padding: 10px 20px; 
                    background-color: #0084ff; 
                    color: white; 
                    border: none; 
                    border-radius: 4px; 
                    font-weight: bold; 
                    cursor: pointer;">
                            Anunciar Nova Vaga de Estágio
                        </button>
                </div>

            </div>

        </main>
    </div>

</body>
</html>