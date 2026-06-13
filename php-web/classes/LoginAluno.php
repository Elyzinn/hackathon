<?php

    require_once 'ApiClient.php';

    class LoginUsuario extends ApiClient{
        
        public function logar():array{
            return $this->request('GET', 'caminho');
        }

        public function atualizarSenha():array{
            return $this->request('GET', 'caminho');
        }
    }

?>
