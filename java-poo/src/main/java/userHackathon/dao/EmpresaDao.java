package userHackathon.dao;

import userHackathon.model.Empresa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao extends Dao{
    public List<Empresa> listar() throws SQLException{
        List<Empresa> empresas = new ArrayList<>();

        var resultSet = getConnection()
                .prepareStatement("select * from empresas")
                .executeQuery();

        while(resultSet.next()){
            var e = new Empresa();
            e.setId(resultSet.getLong("id"));
            e.setRazaoSocial(resultSet.getString("razaoSocial"));
            e.setCnpj(resultSet.getString("cnpj"));
            e.setEmail(resultSet.getString("email"));
            e.setTelefoneContato(resultSet.getString("telefoneContato"));
            e.setResponsavel(resultSet.getString("responsavel"));
            e.setIdEnderecoEmpresa(resultSet.getLong("idEnderecoEmpresa"));
            e.setStatus(resultSet.getBoolean("status"));

            empresas.add(e);
        }
        return empresas;
    }
}
