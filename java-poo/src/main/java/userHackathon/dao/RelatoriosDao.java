package userHackathon.dao;
import userHackathon.model.Relatorios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatoriosDao extends Dao {
    public List<Relatorios> listar() throws SQLException {

        List<Relatorios> relatorios = new ArrayList<>();

        var resultadorRelatorios = getConnection()
                .prepareStatement("select * from relatorios")
                .executeQuery();

        while (resultadorRelatorios.next()) {
            var r = new Relatorios();
            r.setId_empresas(resultadorRelatorios.getLong("id_empresa"));
            r.setId_alunos(resultadorRelatorios.getLong("id_alunos"));
            r.setId_vagas(resultadorRelatorios.getLong("id_vagas"));
            r.setId_candidaturas(resultadorRelatorios.getLong("id_candidaturas"));
            r.setId_contratos(resultadorRelatorios.getLong("id_contratos"));

            relatorios.add(r);
        }
        return relatorios;
    }
}
