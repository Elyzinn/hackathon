package userHackathon.service;

import userHackathon.dao.EmpresaDao;
import userHackathon.model.Empresa;

import java.util.List;

public class EmpresaService {
    public List<Empresa> listar(){
        try{
            var dao = new EmpresaDao();
            return dao.listar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
