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
            e.setRazaoSocial(resultSet.getString("razao_social"));
            e.setCnpj(resultSet.getString("cnpj"));
            e.setEmail(resultSet.getString("email"));
            e.setTelefoneContato(resultSet.getString("telefone_contato"));
            e.setResponsavel(resultSet.getString("responsavel"));
            e.setIdEnderecoEmpresa(resultSet.getLong("endereco_empresa"));
            e.setStatus(resultSet.getBoolean("status"));

            empresas.add(e);
        }
        return empresas;
    }

    public void atualizar(Empresa empresa) throws SQLException{
        var sqlUpdate = "update empresas set status=? where id =?";
        var ps = getConnection().prepareStatement(sqlUpdate);

        ps.setBoolean(1,empresa.getStatus());

        ps.execute();
    }
}
