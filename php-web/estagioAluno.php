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
                <li><a href="#" class="tab-link active" onclick="alternarAba(event, 'painel-geral')">Painel Geral</a></li>
                <li><a href="#" class="tab-link" onclick="alternarAba(event, 'buscar-vagas')">Buscar Vagas</a></li>
                <li><a href="#" class="tab-link" onclick="alternarAba(event, 'minhas-candidaturas')">Minhas Candidaturas</a></li>
                <li><a href="#" class="tab-link" onclick="alternarAba(event, 'meu-curriculo')">Meu Currículo</a></li>
                <li class="menu-sair"><a href="index.php">Sair</a></li>
            </ul>
        </nav>

        <main class="main-content">
            
            <div id="painel-geral" class="tab-content active">
                <div class="hero-banner">
                    <h2>Vagas e Oportunidades de Estágio</h2>
                    <p>Encontre a oportunidade ideal para iniciar sua carreira no mercado de trabalho.</p>
                </div>

                <div class="content-grid">
                    <div class="card-box">
                        <h3>Resumo de Atividades</h3>
                        <p style="color: #000000;">Use o menu lateral para navegar pelas vagas abertas ou acompanhar suas inscrições ativas.</p>
                    </div>

                    <div class="card-box">
                        <h3>Alertas e Prazos</h3>
                        <p style="color: #000000;">Fique atento aos prazos de entrega dos relatórios de estágio obrigatório.</p>
                    </div>
                </div>
            </div>

            <div id="buscar-vagas" class="tab-content">
                <div class="card-box" style="min-height: auto; margin-bottom: 20px;">
                    <h3>Vagas Disponíveis para seu Curso</h3>
                    <p style="color: #666; margin-bottom: 20px;">Candidate-se às oportunidades enviando seu perfil diretamente às empresas parceiras.</p>
                    
                    <div class="vaga-card">
                        <div>
                            <strong style="font-size: 1.1rem; color: #115c74;">Estágio em Desenvolvimento Web Back-End</strong>
                            <p style="color: #555; margin-top: 5px;">Empresa: Alfa Tech Soluções | Bolsa: R$ 1.200,00</p>
                        </div>
                        <button class="btn-candidatar" onclick="alert('Candidatura enviada com sucesso!')">Candidatar-se</button>
                    </div>

                    <div class="vaga-card">
                        <div>
                            <strong style="font-size: 1.1rem; color: #115c74;">Estágio em Suporte e Infraestrutura</strong>
                            <p style="color: #555; margin-top: 5px;">Empresa: Geração de Saberes | Bolsa: R$ 1.000,00</p>
                        </div>
                        <button class="btn-candidatar" onclick="alert('Candidatura enviada com sucesso!')">Candidatar-se</button>
                    </div>
                </div>
            </div>

            <div id="minhas-candidaturas" class="tab-content">
                <div class="card-box">
                    <h3>Minhas Inscrições e Resultados</h3>
                    <p style="color: #666;">Acompanhe abaixo o andamento dos processos seletivos nos quais você demonstrou interesse:</p>
                    
                    <table class="tabela-candidaturas">
                        <thead>
                            <tr>
                                <th>Vaga / Cargo</th>
                                <th>Empresa</th>
                                <th>Data Inscrição</th>
                                <th>Status / Resultado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Desenvolvedor PHP Júnior (Estágio)</td>
                                <td>Alfa Tech Soluções</td>
                                <td>10/06/2026</td>
                                <td><span class="badge status-analise">Em Análise</span></td>
                            </tr>
                            <tr>
                                <td>Estágio em Banco de Dados</td>
                                <td>Beta Sistemas S.A.</td>
                                <td>01/06/2026</td>
                                <td><span class="badge status-aprovado">Aprovado 🎉</span></td>
                            </tr>
                            <tr>
                                <td>Suporte Técnico Interno</td>
                                <td>Inova Digital</td>
                                <td>25/05/2026</td>
                                <td><span class="badge status-recusado">Não Selecionado</span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div id="meu-curriculo" class="tab-content">
                <div class="card-box">
                    <h3>Meu Currículo Profissional</h3>
                    <p style="color: #000000;">Aqui você poderá carregar o seu arquivo de currículo em PDF e atualizar suas experiências para as empresas parceiras visualizarem no banco de dados.</p>
                </div>
            </div>

        </main>
    </div>

    <script>
        function alternarAba(event, idAba) {
            event.preventDefault();
            const conteudos = document.querySelectorAll('.tab-content');
            conteudos.forEach(conteudo => conteudo.classList.remove('active'));
            
            const links = document.querySelectorAll('.tab-link');
            links.forEach(link => link.classList.remove('active'));

            document.getElementById(idAba).classList.add('active');
            event.currentTarget.classList.add('active');
        }
    </script>

</body>
</html>