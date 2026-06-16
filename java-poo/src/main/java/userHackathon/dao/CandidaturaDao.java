package userHackathon.dao;

import userHackathon.model.Candidaturas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidaturaDao extends Dao {
    public List<Candidaturas> listarCandidaturas() throws SQLException {
        List<Candidaturas> candidaturas = new ArrayList<>();

        var resultSet = getConnection()

                .prepareStatement("Select * from candidaturas")
                .executeQuery();

        while (resultSet.next()){
            var c = new Candidaturas();
            c.setId(resultSet.getLong("ID"));
            c.setAluno_id(resultSet.getLong("ID do Aluno"));
            c.setVagas_id(resultSet.getLong("ID da Vaga"));
            c.setData_candidatura(resultSet.getDate("Data da Candidatura"));

            candidaturas.add(c);
        }return candidaturas;
    }
}
