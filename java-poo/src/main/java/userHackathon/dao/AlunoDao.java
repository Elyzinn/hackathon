package userHackathon.dao;

import userHackathon.model.Aluno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao extends Dao{
    public List<Aluno> listar() throws SQLException {

        List<Aluno> alunos = new ArrayList<>();

        var resultSet = getConnection()
                .prepareStatement("select * from alunos")
                .executeQuery();

        while (resultSet.next()){
            var a = new Aluno();
            a.setId(resultSet.getLong("id"));
            a.setNome(resultSet.getString("nome"));
            a.setCpf(resultSet.getString("cpf"));
            a.setEmail(resultSet.getString("email"));
            a.setTelefone(resultSet.getString("telefone"));
            a.setCurso(resultSet.getString("curso"));
            a.setPeriodo(resultSet.getInt("periodo"));
            a.setDataNascimento(resultSet.getString("data_nasc"));
            a.setIdEnderecoAluno(resultSet.getLong("endereco_aluno"));

            alunos.add(a);
        }

        return alunos;
    }

    public void inserir(Aluno aluno) throws SQLException{
        var sqlInsert = "insert into alunos(nome,cpf,email,telefone,curso,periodo,data_nasc,endereco_aluno) " +
                "values (?,?,?,?,?,?,?,?)";
        var ps = getConnection().prepareStatement(sqlInsert);
        ps.setString(1,aluno.getNome());
        ps.setString(2,aluno.getCpf());
        ps.setString(3,aluno.getEmail());
        ps.setString(4,aluno.getTelefone());
        ps.setString(5,aluno.getCurso());
        ps.setInt(6,aluno.getPeriodo());
        ps.setString(7,aluno.getDataNascimento());
        ps.setLong(8,aluno.getIdEnderecoAluno());

        ps.execute();
    }

    public void atualizar(Aluno aluno) throws SQLException{
        var sqlUpdate = "update alunos set nome=?, cpf=?,email=?,telefone=?,curso=?,periodo=?,data_nasc=?,endereco_aluno=?" +
                "where id=?";
        var ps = getConnection().prepareStatement(sqlUpdate);

        ps.setString(1,aluno.getNome());
        ps.setString(2,aluno.getCpf());
        ps.setString(3,aluno.getEmail());
        ps.setString(4,aluno.getTelefone());
        ps.setString(5,aluno.getCurso());
        ps.setInt(6,aluno.getPeriodo());
        ps.setString(7,aluno.getDataNascimento());
        ps.setLong(8,aluno.getIdEnderecoAluno());

        ps.execute();
    }
}
