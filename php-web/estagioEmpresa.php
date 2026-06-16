<?php
    ob_start();
    session_start();
    require_once 'classes/Empresa.php';
    // Proteção da página (opcional, remova os comentários '//' se for usar)
    if (!isset($_SESSION['empresaLogado']) || $_SESSION['empresaLogado'] !== true) {
        header('Location: login-empresa.php');
        exit;
    }
    $id = $_GET['id'] ?? $_SESSION['empresa_id'];
    $empresa = new Empresa();
    //Buscar vagas cadastradas pela empresa
    $resposta = $empresa->buscarVagas();

    $vagas = [];

    if($resposta ['status'] === 200 && isset($resposta['data'])){
        $vagas = $resposta['data']['vagas'];
    }
    
    // Buscar candidatos das vagas
    $resposta = $empresa->buscarCandidatos();

    $candidatos = [];
    if($resposta ['status'] === 200 && isset($resposta['data'])){
        $candidatos = $resposta['data']['candidatos'];
    }

    ob_end_flush();
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel de Empresa - UniALFA</title>
    <link rel="stylesheet" href="css/dashboard.css?v=<?php echo time(); ?>">
    <script src="js/script.js" defer></script>
</head>

<body>

    <div class="dashboard-container">

        <header class="top-header">
            <div class="logo-area"><img src="img/unialfa.png" alt="Logo UniALFA" class="logo-main"></div>
            <div class="user-profile">Bem vindo, Empresa</div>
        </header>

        <nav class="sidebar">
            <ul>
                <li><a href="#" class="tab-link active" onclick="alternarAba(event, 'painel-geral')">Painel Geral</a></li>
                <li><a href="#" class="tab-link" onclick="alternarAba(event, 'minhas-vagas')">Mostrar suas vagas</a></li>
                <li><a href="#" class="tab-link" onclick="alternarAba(event, 'ver-candidatos')">Candidatos</a></li>
                <li><a href="#" class="tab-link" onclick="alternarAba(event, 'estagios-andamento')">Relatório de Estágios</a></li>
                <li class="menu-sair"><a href="index.php">Sair</a></li>
            </ul>
        </nav>

        <main class="main-content">

            <div id="painel-geral" class="tab-content active">
                <div class="hero-banner">
                    <h2>Painel Corporativo - UniALFA</h2>
                    <p>Gerencie seus processos seletivos e encontre os melhores talentos.</p>
                    <button class="btn-anunciar-vaga" onclick="alternarAba(event, 'criar-vaga-tela')">
                        Anunciar Nova Vaga de Estágio
                    </button>
                </div>

                <div class="content-grid">
                    <div class="card-box">
                        <h3>Estagiários Qualificados</h3>
                        <p style="color: #000000;">Ambiente perfeito para encontrar o estagiário qualificado que você precisa.</p>
                    </div>

                    <div class="card-box">
                        <h3>Informativos Rápidos</h3>
                        <p style="color: #000000;">Lembre-se de validar os relatórios de estágio dos seus alunos contratados até o final do mês corrente.</p>
                    </div>
                </div>
            </div>

            <div id="minhas-vagas" class="tab-content">
                <div class="card-box">
                    <h3>Vagas Abertas</h3>
                    <p style="color: #666; margin-bottom: 20px;">Acompanhe as oportunidades que a sua organização disponibilizou no portal:</p>

                    <?php if(empty($vagas)):?>
                        <p>Não há Vagas</p>
                    <?php else: ?>
                        <?php foreach($vagas as $vaga): ?>                
                            <div class="item-lista-painel">
                                <div>
                                    <strong style="font-size: 1.1rem; color: #115c74;">Vaga: <?=$vaga['vaga_cargo'] ?></strong>
                                    <p style="color: #555; margin-top: 5px;">Data de Fechamento: <?= date('d/m/Y', strtotime($vaga['vaga_data_fechamento'])) ?> 
                                    | Requsitos: <?=$vaga['vaga_requisitos'] ?>
                                </p>
                                </div>
                                <?php if($vaga['vaga_vaga_preenchida'] == 1): ?>
                                    <span class="badge status-recusado">Vaga Preenchida</span>
                                <?php else: ?>
                                    <span class="badge status-aprovado">Recebendo Currículos</span>
                                <?php endif; ?>
                            </div>
                        <?php endforeach; ?>
                    <?php endif; ?>   
                </div>
            </div>

            <div id="ver-candidatos" class="tab-content">
                <div class="card-box">
                    <h3>Alunos Candidatados às Vagas</h3>
                    <p style="color: #666; margin-bottom: 15px;">Analise os perfis e currículos dos estudantes inscritos nas suas posições:</p>

                    <table class="tabela-padrao">
                        <thead>
                            <tr>
                                <th>Nome do Aluno</th>
                                <th>Curso</th>
                                <th>Vaga Pretendida</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php if(empty($candidatos)): ?>
                                <p>Sem candidatos!</p>
                            <?php else: ?>
                                <?php foreach($candidatos as $candidato): ?>
                                    <tr>
                                        <td><?php $candidato['nome']?></td>
                                        <td><?php $candidato['curso']?></td>
                                        <td><?php $candidato['vaga']?></td>
                                        <td>
                                            <button class="btn-acao-primario" onclick="alert('Aluno Contratado com sucesso!')">Contratar</button>
                                        </td>
                                    </tr> 
                                <?php endforeach; ?>
                            <?php endif; ?>   
                        </tbody>
                    </table>
                </div>
            </div>

            <div id="estagios-andamento" class="tab-content">
                <div class="card-box">
                    <h3>Contratos de Estágio Ativos</h3>
                    <p style="color: #666; margin-bottom: 15px;">Estudantes atualmente vinculados e ativos na sua organização:</p>

                    <table class="tabela-padrao">
                        <thead>
                            <tr>
                                <th>Estagiário</th>
                                <th>Vaga / Cargo</th>
                                <th>Data de Início</th>
                                <th>Situação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Mariana Costa Ramos</td>
                                <td>Estágio em Banco de Dados</td>
                                <td>01/02/2026</td>
                                <td><span class="badge status-analise">Contrato Ativo</span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div id="criar-vaga-tela" class="tab-content">
                <div class="card-box">
                    <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #F1F5F9; padding-bottom: 15px; margin-bottom: 25px;">
                        <h3 style="margin-bottom: 0; border-bottom: none;">Criar Vaga de Estágio</h3>

                        <button onclick="alternarAba(event, 'painel-geral')" style="background: transparent; border: 1px solid #000; padding: 6px 12px; border-radius: 4px; font-weight: 600; cursor: pointer; font-size: 0.85rem;">
                            ← Voltar ao Painel
                        </button>
                    </div>

                    <p style="color: #475569; margin-bottom: 25px; font-size: 0.95rem;">
                        Insira as informações básicas do estágio. Os campos com asterisco (*) são obrigatórios para a publicação.
                    </p>

                    <form action="" method="POST" style="width: 100%;">

                        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-bottom: 20px;">

                            <div>
                                <label style="display: block; font-family: 'Montserrat', sans-serif; font-weight: 600; margin-bottom: 8px; font-size: 0.9rem;">
                                    Título da Vaga / Cargo *
                                </label>
                                <input type="text" name="titulo_vaga" placeholder="Ex: Estágio em Desenvolvimento Web" required
                                    style="width: 100%; padding: 12px 14px; border: 1px solid #E2E8F0; border-radius: 6px; font-size: 0.95rem; background-color: #FFFFFF;">
                            </div>

                            <div>
                                <label style="display: block; font-family: 'Montserrat', sans-serif; font-weight: 600; margin-bottom: 8px; font-size: 0.9rem;">
                                    Setor / Área de Atuação *
                                </label>
                                <input type="text" name="setor_vaga" placeholder="Ex: Tecnologia / TI" required
                                    style="width: 100%; padding: 12px 14px; border: 1px solid #E2E8F0; border-radius: 6px; font-size: 0.95rem; background-color: #FFFFFF;">
                            </div>

                            <div>
                                <label style="display: block; font-family: 'Montserrat', sans-serif; font-weight: 600; margin-bottom: 8px; font-size: 0.9rem;">
                                    Valor da Bolsa-Auxílio (R$) *
                                </label>
                                <input type="text" name="bolsa_vaga" placeholder="Ex: 1.200,00" required
                                    style="width: 100%; padding: 12px 14px; border: 1px solid #E2E8F0; border-radius: 6px; font-size: 0.95rem; background-color: #FFFFFF;">
                            </div>

                            <div>
                                <label style="display: block; font-family: 'Montserrat', sans-serif; font-weight: 600; margin-bottom: 8px; font-size: 0.9rem;">
                                    Carga Horária Semanal *
                                </label>
                                <select name="carga_vaga" required style="width: 100%; padding: 12px 14px; border: 1px solid #E2E8F0; border-radius: 6px; font-size: 0.95rem; background-color: #FFFFFF; height: 47px;">
                                    <option value="">Selecione...</option>
                                    <option value="20h">20 horas semanais (4h/dia)</option>
                                    <option value="30h">30 horas horas semanais (6h/dia)</option>
                                </select>
                            </div>

                            <div>
                                <label style="display: block; font-family: 'Montserrat', sans-serif; font-weight: 600; margin-bottom: 8px; font-size: 0.9rem;">
                                    Período / Horário de Trabalho
                                </label>
                                <input type="text" name="horario_vaga" placeholder="Ex: 08:00 às 14:00"
                                    style="width: 100%; padding: 12px 14px; border: 1px solid #E2E8F0; border-radius: 6px; font-size: 0.95rem; background-color: #FFFFFF;">
                            </div>

                            <!-- Campo 6: Localidade -->
                            <div>
                                <label style="display: block; font-family: 'Montserrat', sans-serif; font-weight: 600; margin-bottom: 8px; font-size: 0.9rem;">
                                    Modalidade *
                                </label>
                                <select name="modalidade_vaga" required style="width: 100%; padding: 12px 14px; border: 1px solid #E2E8F0; border-radius: 6px; font-size: 0.95rem; background-color: #FFFFFF; height: 47px;">
                                    <option value="Presencial">Presencial</option>
                                    <option value="Híbrido">Híbrido</option>
                                    <option value="Home Office">Home Office (Totalmente Remoto)</option>
                                </select>
                            </div>

                        </div>

                        <div style="margin-bottom: 30px;">
                            <label style="display: block; font-family: 'Montserrat', sans-serif; font-weight: 600; margin-bottom: 8px; font-size: 0.9rem;">
                                Requisitos e Qualificações Desejadas *
                            </label>
                            <textarea name="requisitos_vaga" rows="6" required
                                placeholder="Descreva as competências técnicas e comportamentais esperadas.&#10;Exemplo:&#10;- Conhecimento em lógica de programação e banco de dados.&#10;- Boa comunicação e trabalho em equipe.&#10;- Estar cursando a partir do 2º período."
                                style="width: 100%; padding: 14px 16px; border: 1px solid #E2E8F0; border-radius: 6px; font-size: 0.95rem; line-height: 1.6; resize: vertical; background-color: #FFFFFF;"></textarea>
                        </div>

                        <div style="display: flex; gap: 12px;">
                            <button type="submit" class="btn-principal" style="background-color: #22E836;">
                                Publicar Vaga Oficialmente
                            </button>
                            <button type="button" onclick="alternarAba(event, 'painel-geral')" class="btn-acao-secundario">
                                Cancelar
                            </button>
                        </div>

                    </form>
                </div>
            </div>

        </main>
    </div>

</body>

</html>