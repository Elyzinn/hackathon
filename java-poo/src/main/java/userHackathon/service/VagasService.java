package userHackathon.service;

import userHackathon.dao.VagasDao;
import userHackathon.model.Vagas;

import java.util.List;


public class VagasService {
    public List<Vagas> listarVagas() {
        try {
            var daoVagas = new VagasDao();
            return daoVagas.listarVagas();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }

}
