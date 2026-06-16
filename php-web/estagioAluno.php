<?php
    ob_start();
    session_start();
    //var_dump($_SESSION); 
    //var_dump($_GET);     
    require_once 'classes/Aluno.php';
    if (!isset($_SESSION['alunoLogado']) || $_SESSION['alunoLogado'] !== true){
        header('Location: login-aluno.php');
        exit;
    }
    $id = $_GET['id'] ?? $_SESSION['aluno_id'];
    $aluno = new Aluno();
    // Buscar as vagas em aberto
    $resposta = $aluno->buscarVagas();
    
    $vagas = [];

    if($resposta ['status'] === 200 && isset($resposta['data'])){
        $vagas = $resposta['data']['vagas'];
    }

    // Buscar as candidaturas do aluno

    $resposta = $aluno->buscarCandidaturas();
    $candidaturas = [];
    if($resposta ['status'] === 200 && isset($resposta['data']['candidaturas'])){
        $candidaturas = $resposta['data']['candidaturas'];
    }

    if($_SERVER['REQUEST_METHOD'] === 'POST'){
        $vagaId = isset($_POST['vaga_id']) ? (int)$_POST['vaga_id'] : 0;
        //var_dump($vagaId);
        //die();
        $res = $aluno->candidatar($vagaId);
        //var_dump($res);
        //die();
        if($aluno->resHttp($res)){
            $mensagem = "Candidatura realizada com sucesso";
            $tipo_msg = "sucesso";
        }else{
            $mensagem = "Candidatura não pode ser realizada";
            $tipo_msg = "sucesso";
        }
    }

    ob_end_flush();
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel do Aluno - UniALFA</title>
    
    <link rel="stylesheet" href="css/dashboard.css?v=<?php echo time(); ?>">
    <link rel="stylesheet" href="css/dashboard.css">
    <script src="js/script.js" defer></script>
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
                    
                    <?php if(empty($vagas)): ?>
                        <p>Não há vagas!</p>
                    <?php else: ?>
                        <?php foreach($vagas as $vaga): ?>
                            <div class="vaga-card">                                                
                                <div>
                                    <strong style="font-size: 1.1rem; color: #115c74;"><?=htmlspecialchars($vaga['vaga_cargo'] ?? 'Sem titulo')?></strong>
                                    <p style="color: #555; margin-top: 5px;">Empresa: <?=$vaga['empresa_razao_social'] ?? '' ?> 
                                    | Telefone de contato: <?=$vaga['telefone'] ?? '' ?> 
                                    | E-mail: <?=$vaga['empresa_email'] ?? '' ?>
                                    | Requisitos: <?=$vaga['vaga_requisitos'] ?? '' ?> </p>
                                </div>
                                <form method="POST">
                                    <input type="hidden" name="vaga_id" value="<?=$vaga['vaga_id']?>">
                                    <button class="btn-candidatar" type="submit">Candidatar-se</button>
                                </form>
                            </div>
                        <?php endforeach; ?>
                                <?php if(!empty($mensagem)): ?>
                                    <div class="msg-alerta-<?= $tipo_msg ?>">
                                        <?= htmlspecialchars($mensagem) ?>
                                    </div>
                                <?php endif; ?>
                    <?php endif; ?>  
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
                            </tr>
                        </thead>
                        <tbody>
                            <?php foreach($candidaturas as $candidatura):?>
                                <tr>
                                    <td><?= $candidatura['cargo']?></td>
                                    <td><?= $candidatura['empresa']?></td>
                                    <td><?= date('d/m/Y', strtotime($candidatura['data_candidatura'])) ?></td>
                                </tr>
                            <?php endforeach;?>
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
</body>
</html>