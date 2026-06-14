package userHackathon.service;

import userHackathon.dao.EmpresaDao;
import userHackathon.model.Empresa;

import java.util.ArrayList;
import java.util.List;

public class EmpresaService {

    //public List<Empresa> listar(){
        //try{
            //os invés de puxar info do banco, como o banco está vazio
            //criar array para teste.
            //var dao = new EmpresaDao();

            //return dao.listar();
       // } catch (Exception e) {
           // System.out.println("ERRO" + e.getMessage());
           // return null;
        //}

    private static final List<Empresa> listaMock = new ArrayList<>();

    // Bloco estático para popular a lista assim que a classe for carregada
        static {
            Empresa e1 = new Empresa();
            e1.setId(1L);
            e1.setRazaoSocial("Tech Solutions Ltda");
            e1.setCnpj("12.345.678/0001-90");
            e1.setEmail("contato@tech.com");
            e1.setTelefoneContato("(11) 99999-9999");
            e1.setResponsavel("Carlos Silva");
            e1.setIdEnderecoEmpresa(101L);
            e1.setStatus(true);
            listaMock.add(e1);

            Empresa e2 = new Empresa();
            e2.setId(2L);
            e2.setRazaoSocial("Inovações Café e TI");
            e2.setCnpj("98.765.432/0001-10");
            e2.setEmail("cafe@ti.com");
            e2.setTelefoneContato("(21) 88888-8888");
            e2.setResponsavel("Ana Souza");
            e2.setIdEnderecoEmpresa(102L);
            e2.setStatus(false);
            listaMock.add(e2);
        }

    public List<Empresa> listar() {
        return listaMock;
    }

    public void atualizar(Empresa empresa){
        try{
            var dao = new EmpresaDao();

            //terminar a lógica aqui, para quando o banco integrado ja funcionar

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}

