package userHackathon.dao;

import userHackathon.model.EnderecoAluno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnderecoAlunoDao extends Dao{

    public List<EnderecoAluno> listar() throws SQLException{
        List<EnderecoAluno> enderecos = new ArrayList<>();

        var resultSet = getConnection()
                .prepareStatement("select * from endereco_aluno")
                .executeQuery();

        while (resultSet.next()){
            var ea = new userHackathon.model.EnderecoAluno();
            ea.setId(resultSet.getLong("id"));
            ea.setCep(resultSet.getString("cep"));
            ea.setBairro(resultSet.getString("bairro"));
            ea.setLogradouro(resultSet.getString("logradouro"));
            ea.setNumLogradouro(resultSet.getString("num_logradouro"));
            ea.setCidade(resultSet.getString("cidade"));
            ea.setUf(resultSet.getString("uf"));

            enderecos.add(ea);
        }
        return enderecos;
    }

    public long inserir(EnderecoAluno endereco) throws SQLException{
        var sqlInsert = "insert into endereco_aluno (logradouro,num_logradouro,bairro,cep,cidade,uf) values (?,?,?,?,?,?)";
        var ps = getConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1,endereco.getLogradouro());
        ps.setString(2, endereco.getNumLogradouro());
        ps.setString(3, endereco.getBairro());
        ps.setString(4,endereco.getCep());
        ps.setString(5, endereco.getCidade());
        ps.setString(6,endereco.getUf());

        ps.execute();

        try(ResultSet rs = ps.getGeneratedKeys()){
            if (rs.next()){
                return rs.getLong(1);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return 0;
    }

    public void atualizar(EnderecoAluno endereco) throws SQLException{
        var sqlUpdate = "update endereco_aluno set logradouro=?,num_logradouro=?,bairro=?,cep=?,cidade=?,uf=?" +
                "where id=?";
        var ps = getConnection().prepareStatement(sqlUpdate);

        ps.setString(1,endereco.getLogradouro());
        ps.setString(2,endereco.getNumLogradouro());
        ps.setString(3,endereco.getBairro());
        ps.setString(4,endereco.getCep());
        ps.setString(5,endereco.getCidade());
        ps.setString(6,endereco.getUf());
        ps.setLong(7,endereco.getId());

        ps.execute();
    }


}
