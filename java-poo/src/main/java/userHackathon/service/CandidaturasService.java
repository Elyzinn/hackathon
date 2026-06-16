package userHackathon.service;

import userHackathon.dao.CandidaturaDao;
import userHackathon.model.Candidaturas;

import java.util.List;

public class CandidaturasService {
    public List<Candidaturas> listarCandidaturas() {
        try {
            var daoCand = new CandidaturaDao();
            return daoCand.listarCandidaturas();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }
}

