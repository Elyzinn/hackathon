<?php
    /**
     * ApiClient é uma classe para gerenciar as req php -> node
     *
     * Suporta: GET, POST, PUT, DELETE;
    */ 
    class ApiClient{
        private $baseUrl;
        private $headers;

        public function __construct(){
           //confirmar URL
            $this->baseUrl = rtrim(getenv('NODE_API_URL'), '/') . '/'; // pega URL do docker compose
            $this->headers = [
                'Content-Type: application/json',
                'Accept: application/json',
            ];
        }
       //montando a requisição em curl
        protected function request(string $method, string $endpoint, array $body = []): array{
            
            $ch = curl_init($this->baseUrl . $endpoint);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_HTTPHEADER, $this->headers);
            curl_setopt($ch, CURLOPT_CUSTOMREQUEST, strtoupper($method));

            if(!empty($body)){
                curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($body));
            }

           $response = curl_exec($ch);
           //$erro = curl_error($ch);
           $statusCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
           curl_close($ch);

            return[
                //'erro' => $erro,
                'status' => $statusCode,
                'data' => json_decode($response, true),
            ];
        }
    }

 