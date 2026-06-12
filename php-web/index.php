<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Institucional - Centro Universitário ALFA</title>

    <!-- IMPORTAÇÃO OFICIAL GOOGLE FONTS (Montserrat e Roboto) -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500;600;700;800&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- Ícones e CSS local -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="css/institucional.css">
</head>
<body>

    <!-- 1. BARRA SUPERIOR FIEL À IMAGEM -->
    <div class="top-utility-bar">
        <div class="top-bar-container">
            <!-- Informações da Esquerda -->
            <div class="top-left-info">
                <a href="tel:4436222500"><i class="fa-solid fa-phone"></i> Disk Vestibular: (44) 3622-2500</a>
                <a href="#"><i class="fa-solid fa-envelope"></i> Contato</a>
            </div>
            <!-- Portais da Direita -->
            <div class="top-right-links">
                <a href="#">Webmail</a>
                <span class="pipe">|</span>
                <a href="#">Portal do Aluno TOTVS</a>
                <span class="pipe">|</span>
                <a href="#">Portal do Professor TOTVS</a>
                <span class="pipe">|</span>
                <a href="#">AVA</a>
                <span class="pipe">|</span>
                <a href="#">Bibliotecas</a>
                <span class="pipe">|</span>
                <a href="#">OAB na Medida</a>
            </div>
        </div>
    </div>

    <!-- O resto do cabeçalho (<header>) continua igual abaixo... -->

    <!-- 2. CABEÇALHO PRINCIPAL BRANCO -->
    <header class="site-header">
        <div class="header-container">
            <div class="logo-area">
                <img src="img/unialfa.png" alt="Logo UniALFA" class="logo-main">
            </div>
            <nav class="main-navigation">
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#" class="nav-active">Sobre a Instituição</a></li>
                    
                    <!-- DROPDOWN ALUNOS -->
                    <li class="dropdown-item">
                        <a href="#" class="drop-trigger">ALUNOS <span class="arrow">▾</span></a>
                        <div class="dropdown-menu">
                            <a href="login-aluno.php">Portal de Estágios (Login)</a>
                            <a href="#">Vagas Disponíveis</a>
                            <a href="#">Documentação e Manuais</a>
                        </div>
                    </li>

                    <!-- DROPDOWN EMPRESA -->
                    <li class="dropdown-item">
                        <a href="#" class="drop-trigger">EMPRESA <span class="arrow">▾</span></a>
                        <div class="dropdown-menu">
                            <a href="login-empresa.php">Portal da Empresa (Login)</a>
                            <a href="#">Cadastrar Nova Vaga</a>
                            <a href="#">Convênio de Estágio</a>
                        </div>
                    </li>

                    <li><a href="#">Contato</a></li>
                </ul>
            </nav>
            
        </div>
    </header>

    <!-- 3. BARRA DE TÍTULO E BREADCRUMB -->
    <div class="page-title-bar">
        <div class="bar-container">
            <h1 class="page-title">Institucional</h1>
        </div>
    </div>

    <!-- 4. CORPO DA PÁGINA (Layout Split Lado a Lado) -->
    <div class="page-wrapper">
        
        <!-- MENU LATERAL (Sidebar) -->
        <aside class="sidebar-menu">
            <div class="sidebar-title">Sobre a FAU</div>
            <ul>
                <li><a href="#" class="sidebar-active">Apresentação Institucional</a></li>
                <li><a href="#">Missão, Visão e Valores</a></li>
                <li><a href="#">Histórico da Faculdade</a></li>
                <li><a href="#">Corpo Diretivo</a></li>
                <li><a href="#">CPA (Avaliação Institucional)</a></li>
                <li><a href="#">Ouvidoria</a></li>
            </ul>
        </aside>

        <!-- CONTEÚDO PRINCIPAL (Texto) -->
        <main class="main-content">
            <article class="content-article">
                <h2>História da Faculdade ALFA Umuarama - UniALFA</h2>
                <p>O Centro Universitário ALFA (UniALFA), atento às transformações globais e às demandas do mercado regional, consolidou-se como referência na formação de profissionais altamente qualificados, especialmente no desenvolvimento de tecnologias e inovação com o curso de <strong>Sistemas para Internet</strong>.</p>
                
                <p>Nossa proposta pedagógica alia de forma indissociável a fundamentação teórica sólida com a vivência prática profissional. Entendemos que o ecossistema de estágios e a aproximação com empresas parceiras formam o pilar central para a inserção bem-sucedida de nossos acadêmicos no ecossistema de TI.</p>

                <div class="highlight-box">
                    <h3>Setor de Estágios e Carreiras</h3>
                    <p>O Portal de Estágios da UniALFA funciona como uma ponte inteligente e automatizada. Aqui, os processos burocráticos de validação de contratos, relatórios e termos de compromisso são realizados em conformidade com as diretrizes acadêmicas vigentes, acelerando a contratação.</p>
                </div>

                <p>Por meio desta central institucional, tanto os discentes em busca de evolução contínua quanto as organizações focadas na captação de talentos encontram uma estrutura transparente, ágil e focada em resultados mútuos.</p>
            </article>
        </main>

    </div>

</body>
</html>

<!-- ==========================================================================
   RODAPÉ OFICIAL (Fidelidade à image_d8f148.png)
   ========================================================================== -->
<footer class="site-footer">
    
    <!-- 1. FAIXA ESCURA SUPERIOR DO RODAPÉ -->
    <div class="footer-top-links">
        <div class="footer-top-container">
            <a href="#"><i class="fa-solid fa-magnifying-glass"></i> Conheça a UniALFA</a>
            <a href="#"><i class="fa-solid fa-briefcase"></i> Trabalhe Conosco</a>
            <a href="#"><i class="fa-solid fa-map-location-dot"></i> Mapa do Site</a>
            <a href="#"><i class="fa-solid fa-headset"></i> Central de Atendimento</a>
        </div>
    </div>

    <!-- 2. CORPO DO RODAPÉ (4 COLUNAS) -->
    <div class="footer-main">
        <div class="footer-main-container">
            
            <!-- Coluna 1: Logos e e-MEC -->
            <div class="footer-col col-brand">
                <img src="img/unialfa.png" alt="Logo UniALFA" class="footer-logo">
                
                <div class="emec-box">
                    <div class="emec-header">
                        <span class="emec-e">e</span><span class="emec-mec">-MEC</span>
                    </div>
                    <!-- Substitua pelo seu QR Code real se necessário -->
                    <div class="emec-qrcode">
                        <i class="fa-solid fa-qrcode"></i>
                    </div>
                    <div class="emec-footer">ACESSE JÁ!</div>
                </div>
                <p class="emec-text">Consulte o cadastro da IES no e-MEC</p>
            </div>

            <!-- Coluna 2: Links Institucionais -->
            <div class="footer-col">
                <h3>SOBRE</h3>
                <ul>
                    <li><a href="#">&gt; Sobre a UniALFA</a></li>
                    <li><a href="#">&gt; Estrutura</a></li>
                    <li><a href="#">&gt; Localização</a></li>
                    <li><a href="#">&gt; Vestibular</a></li>
                    <li><a href="#">&gt; Cursos de Capacitação</a></li>
                    <li><a href="#">&gt; Formas de Ingresso</a></li>
                    <li><a href="#">&gt; Financiamento Estudantil</a></li>
                    <li><a href="#">&gt; Downloads</a></li>
                    <li><a href="#">&gt; Política de Privacidade</a></li>
                    <li><a href="#">&gt; Ouvidoria</a></li>
                </ul>
            </div>

            <!-- Coluna 3: Cursos -->
            <div class="footer-col">
                <h3>CURSOS DE GRADUAÇÃO</h3>
                <ul>
                    <li><a href="#">&gt; Administração</a></li>
                    <li><a href="#">&gt; Ciências Contábeis</a></li>
                    <li><a href="#">&gt; Direito</a></li>
                    <li><a href="#">&gt; Marketing</a></li>
                    <li><a href="#">&gt; Pedagogia</a></li>
                    <li><a href="#">&gt; Processos Gerenciais</a></li>
                    <li><a href="#">&gt; Psicologia</a></li>
                    <li><a href="#">&gt; Sistemas para Internet</a></li>
                </ul>
                <h3 class="sub-heading">CURSOS DE PÓS-GRADUAÇÃO</h3>
            </div>

            <!-- Coluna 4: Contatos e Botão -->
            <div class="footer-col col-contact">
                <div class="contact-item">
                    <i class="fa-solid fa-location-dot"></i>
                    <p>Av. Paraná 7327, Zona III, CEP 87502-000, Umuarama - PR</p>
                </div>
                <div class="contact-item">
                    <i class="fa-brands fa-whatsapp"></i>
                    <p>(44) 36222500 WhatsApp</p>
                </div>
                <a href="#" class="btn-enroll">INSCREVA-SE NO VESTIBULAR!</a>
            </div>

        </div>
    </div>

    <!-- 3. BARRA INFERIOR DE DIREITOS AUTORAIS -->
    <div class="footer-bottom">
        <div class="footer-bottom-container">
            <p class="copyright">Faculdade ALFA Umuarama - UniALFA - 2026 - Todos os direitos reservados</p>
            <div class="footer-socials">
                <a href="#" class="social-icon"><i class="fa-brands fa-facebook-f"></i></a>
                <a href="#" class="social-icon"><i class="fa-brands fa-twitter"></i></a>
                <a href="#" class="social-icon"><i class="fa-brands fa-instagram"></i></a>
                <a href="#" class="social-icon"><i class="fa-brands fa-youtube"></i></a>
                <a href="#" class="social-icon"><i class="fa-solid fa-envelope"></i></a>
            </div>
        </div>
    </div>
</footer>

<!-- 4. BOTÃO FLUTUANTE DO WHATSAPP -->
<a href="https://wa.me/554436222500" class="whatsapp-floating-btn" target="_blank">
    <i class="fa-brands fa-whatsapp"></i>
</a>