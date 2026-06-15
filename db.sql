-- Criação da tabela: endereco_aluno
CREATE TABLE endereco_aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    logadouro VARCHAR(100),
    num_logadouro VARCHAR(6),
    bairro VARCHAR(100),
    cep VARCHAR(8),
    cidade VARCHAR(100),
    uf VARCHAR(100)
);

-- Criação da tabela: endereco_empresa
CREATE TABLE endereco_empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    logadouro VARCHAR(100),
    num_logadouro VARCHAR(6),
    bairro VARCHAR(100),
    cep VARCHAR(8),
    cidade VARCHAR(100),
    uf VARCHAR(100)
);

-- Criação da tabela: alunos (Importante: Nome no PLURAL)
CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(11),
    email VARCHAR(100),
    telefone VARCHAR(11),
    curso VARCHAR(100),
    periodo INT,
    data_nasc DATE,
    endereco_aluno INT,
    CONSTRAINT FK_aluno_endereco FOREIGN KEY (endereco_aluno) 
        REFERENCES endereco_aluno(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Criação da tabela: empresas
CREATE TABLE empresas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    razao_social VARCHAR(100),
    cnpj VARCHAR(14),
    email VARCHAR(100),
    telefone_contato VARCHAR(11),
    responsavel VARCHAR(100),
    status TINYINT,
    endereco_empresa INT,
    CONSTRAINT FK_empresas_endereco FOREIGN KEY (endereco_empresa) 
        REFERENCES endereco_empresa(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Criação da tabela: vagas
CREATE TABLE vagas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(100),
    vaga_preenchida TINYINT,
    data_abertura DATE,
    data_fechamento DATE,
    empresas_id INT,
    CONSTRAINT FK_vagas_empresas FOREIGN KEY (empresas_id) 
        REFERENCES empresas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Criação da tabela: candidatura (Tabela pivô N:N)
CREATE TABLE candidaturas (
    aluno_idaluno INT,
    vagas_id INT,
    PRIMARY KEY (aluno_idaluno, vagas_id),
    CONSTRAINT FK_candidatura_aluno FOREIGN KEY (aluno_idaluno) 
        REFERENCES alunos(id) ON DELETE CASCADE ON UPDATE CASCADE, -- Corrigido para 'alunos'
    CONSTRAINT FK_candidatura_vagas FOREIGN KEY (vagas_id) 
        REFERENCES vagas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Criação da tabela: contrato
CREATE TABLE contratos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vagas_id INT,
    aluno_id INT,
    data_inicio DATE, -- Corrigido de date_inicio para data_inicio
    data_fim DATE,
    CONSTRAINT FK_contrato_vagas FOREIGN KEY (vagas_id) 
        REFERENCES vagas(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_contrato_aluno FOREIGN KEY (aluno_id) 
        REFERENCES alunos(id) ON DELETE RESTRICT ON UPDATE CASCADE -- Corrigido para 'alunos'
);

-- Criação da tabela: operadores
CREATE TABLE operadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cargo VARCHAR(100),
    data_contratacao DATE,
    data_recisao DATE
);

-- Criação da tabela: operacoes_aluno
CREATE TABLE operacoes_aluno (
    idoperacoes_aluno INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100),
    data DATETIME,
    operadores_id INT,
    aluno_id INT,
    CONSTRAINT FK_op_aluno_operador FOREIGN KEY (operadores_id) 
        REFERENCES operadores(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_op_aluno_aluno FOREIGN KEY (aluno_id) 
        REFERENCES alunos(id) ON DELETE CASCADE ON UPDATE CASCADE -- Corrigido para 'alunos'
);

-- Criação da tabela: operacoes_empresa
CREATE TABLE operacoes_empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100),
    data_operacao DATETIME, -- Corrigido de date_operacao para data_operacao
    operadores_id INT,
    empresas_id INT,
    CONSTRAINT FK_op_empresa_operador FOREIGN KEY (operadores_id) 
        REFERENCES operadores(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT FK_op_empresa_empresa FOREIGN KEY (empresas_id) 
        REFERENCES empresas(id) ON DELETE CASCADE ON UPDATE CASCADE
);
