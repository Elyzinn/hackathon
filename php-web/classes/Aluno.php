<?php

    require_once 'ApiClient.php';

    class Aluno extends ApiClient{
        
        public function logar($cpf, $senha, $email):array{
            return $this->request('POST', 'alunoLogin', [
                'cpf' => $cpf,
                'senha' => $senha,
                'email' => $email,
            ]);
        }
        
        public function loginSucesso(array $res): bool {
            //var_dump($res['status'] === 200); 
            //var_dump(isset($res['data']['message']));
        
            return $res['status'] === 200 && isset($res['data']['message']);
        }

        public function resHttp(array $res):bool{
            return $res['status'] === 201 && isset($res['data']['message']);
        }

        public function atualizarSenha():array{
            return $this->request('GET', 'caminho');
        }

        public function buscarVagas():array{
            return $this->request('GET', 'vagas');
        }

        public function buscarCandidaturas(): array {
            $id = $_SESSION['aluno_id'] ?? $_GET['id'];
            return $this->request('GET', "candAluno/$id");
        }

        public function candidatar($vagaId): array {
            $alunoId = $_SESSION['aluno_id'] ?? $_GET['id'];
            return $this->request('POST', 'criarCandidaturas', [
                'aluno_id' => $alunoId,
                'vagas_id' => $vagaId,
                'data_candidatura' => date('c'), 
            ]);
        }
    }

