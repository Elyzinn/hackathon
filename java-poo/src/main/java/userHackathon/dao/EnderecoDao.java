package userHackathon.dao;

import userHackathon.model.EnderecoAluno;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDao extends Dao{
    public long inserir(EnderecoAluno endereco) throws SQLException{
        var sqlInsert = "insert into endereco_aluno (logradouro,numLogradouro,bairro,cep,cidade,uf) values (?,?,?,?,?,?)";
        var ps = getConnection().prepareStatement(sqlInsert);
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
}
