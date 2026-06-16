package userHackathon.dao;

import userHackathon.model.Vagas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VagasDao extends Dao {
    public List<Vagas> listarVagas() throws SQLException {
        List<Vagas> vagas = new ArrayList<>();

        var resultSet = getConnection()

                .prepareStatement("Select * from vagas")
                .executeQuery();

        while (resultSet.next()){
            var v = new Vagas();
            v.setId(resultSet.getLong("ID"));
            v.setVaga_preenchida(String.valueOf(resultSet.getBoolean("Status da Vaga")));
            v.setData_abertura(resultSet.getDate("Data de Abertura"));
            v.setData_fechamento(resultSet.getDate("Data de Fechamento"));
            v.setRequisitos(resultSet.getString("Requisitos"));
            v.setEmpresas_id(resultSet.getLong("Id da empresa"));

            vagas.add(v);
        }
        return vagas;
    }

}
