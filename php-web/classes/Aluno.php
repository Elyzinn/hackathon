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

        public function atualizarSenha():array{
            return $this->request('GET', 'caminho');
        }
    }

