<?php
    require_once 'ApiClient.php';

    class Empresa extends ApiClient{
        public function logar($cnpj, $senha, $email):array{
        //var_dump($this->request);    
        return $this->request('POST', 'empresaLogin',[
                'cnpj' => $cnpj,
                'senha' => $senha,
                'email' => $email,
            ]);
        }

        public function resHttp(array $res):bool{
            //var_dump($res['status'] === 200); 
            //var_dump(isset($res['data']['message']));
            return $res['status'] === 200 && isset($res['data']['message']);
        }

        public function registar(
                $razao_social, 
                $cnpj,
                $email,
                $telefone_contato,
                $responsavel,
                $logradouro,
                $numero,
                $bairro,
                $cidade,
                $estado,
                $senha,
            ):array{
            return $this->request('POST', 'empresaRegistar',[
                'razao_social' => $razao_social, 
                'cnpj' => $cnpj,
                'email' => $email,
                'telefone_contato' => $telefone_contato,
                'responsavel' => $responsavel,
                'logradouro' => $logradouro,
                'numero' => $numero,
                'bairro' => $bairro,
                'cidade' => $cidade,
                'uf' => $estado,
                'senha' => $senha,
            ]);
        }
    }
