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

        public function loginSucess(array $res):bool{
            //var_dump($res['status'] === 200); 
            //var_dump(isset($res['data']['message']));
            return $res['status'] === 200 && isset($res['data']['message']);
        }
        public function resHttp(array $res):bool{
            //var_dump($res['status'] === 200); 
            //var_dump(isset($res['data']['message']));
            return $res['status'] === 201 && isset($res['data']['message']);
        }

        public function registar(
                $razao_social, 
                $cnpj,
                $email,
                $telefone_contato,
                $responsavel,
                $senha,
                $cep,
                $logradouro,
                $numero,
                $bairro,
                $cidade,
                $estado,
            ):array{
            return $this->request('POST', 'empresaCad',[
                'razao_social' => $razao_social, 
                'cnpj' => $cnpj,
                'email' => $email,
                'telefone_contato' => $telefone_contato,
                'responsavel' => $responsavel,
                'senha' => $senha,
                'cep' => $cep, 
                'logradouro' => $logradouro,
                'numero' => $numero,
                'bairro' => $bairro,
                'cidade' => $cidade,
                'uf' => $estado,
                
            ]);
        }

        public function buscarVagas():array{
            $id = $_SESSION['empresa_id'] ?? $_GET['id'];
            return $this->request('GET', 'vagasPorEmpresa/$id');
        }

        public function buscarCandidatos():array{
            $id = $_SESSION['empresa_id'] ?? $_GET['id'];
            return $this->request('GET', 'vagasPorEmpresa/$id');
        }
    }
