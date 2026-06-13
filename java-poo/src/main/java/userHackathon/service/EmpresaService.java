package userHackathon.service;

import userHackathon.dao.EmpresaDao;
import userHackathon.model.Empresa;

import java.util.List;

public class EmpresaService {

    public List<Empresa> listar(){
        try{
            //os invés de puxar info do banco, como o banco está vazio
            //criar array para teste.
            var dao = new EmpresaDao();

            return dao.listar();
        } catch (Exception e) {
            System.out.println("ERRO" + e.getMessage());
            return null;
        }
    }
}
