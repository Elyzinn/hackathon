package userHackathon.dao;

import userHackathon.model.Empresa;
import userHackathon.model.EnderecoAluno;
import userHackathon.model.EnderecoEmpresa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao extends Dao {
    public List<Empresa> listar() throws SQLException {
        List<Empresa> empresas = new ArrayList<>();

        String sql = "select e.*, ee.logradouro, ee.num_logradouro,ee.bairro,ee.cep,ee.cidade, ee.uf From empresas e " +
                "left join endereco_empresa ee ON e.endereco_empresa = ee.id";

        var resultSet = getConnection()
                .prepareStatement(sql)
                .executeQuery();

        while (resultSet.next()) {
            var e = new Empresa();
            e.setId(resultSet.getLong("id"));
            e.setRazaoSocial(resultSet.getString("razao_social"));
            e.setCnpj(resultSet.getString("cnpj"));
            e.setEmail(resultSet.getString("email"));
            e.setTelefoneContato(resultSet.getString("telefone_contato"));
            e.setResponsavel(resultSet.getString("responsavel"));
            e.setIdEnderecoEmpresa(resultSet.getLong("endereco_empresa"));
            e.setStatus(resultSet.getBoolean("status"));

            if (e.getIdEnderecoEmpresa() != 0) {

                var endereco = new EnderecoEmpresa();
                endereco.setId(resultSet.getLong("endereco_empresa"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumLogradouro(resultSet.getString("num_logradouro"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setUf(resultSet.getString("uf"));

                e.setEndereco(endereco);


            }
            empresas.add(e);
        }
        return empresas;
    }

        public void atualizar ( int id, boolean novoStatus) throws SQLException {
            var sqlUpdate = "update empresas set status=? where id =?";
            try (java.sql.PreparedStatement stmt = getConnection().prepareStatement(sqlUpdate)) {
                stmt.setBoolean(1, novoStatus);
                stmt.setInt(2, id);

                stmt.executeUpdate();
            }
        }

}
