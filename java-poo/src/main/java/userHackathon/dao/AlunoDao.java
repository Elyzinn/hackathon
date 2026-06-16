package userHackathon.dao;

import userHackathon.model.Aluno;
import userHackathon.model.EnderecoAluno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao extends Dao{
    public List<Aluno> listar() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "select a.*, e.logradouro, e.num_logradouro,e.bairro,e.cep,e.cidade, e.uf From alunos a " +
                "left join endereco_aluno e ON a.endereco_aluno = e.id";

        var resultSet = getConnection()
                .prepareStatement(sql)
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
            a.setRa(resultSet.getInt("ra"));

            if (a.getIdEnderecoAluno() != 0) {

                var endereco = new EnderecoAluno();
                endereco.setId(resultSet.getLong("endereco_aluno"));
                endereco.setLogradouro(resultSet.getString("logradouro"));
                endereco.setNumLogradouro(resultSet.getString("num_logradouro"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setCidade(resultSet.getString("cidade"));
                endereco.setUf(resultSet.getString("uf"));

                a.setEndereco(endereco);

            }

            alunos.add(a);
        }

        return alunos;
    }

    public void inserir(Aluno aluno) throws SQLException{
        var sqlInsert = "insert into alunos(nome,cpf,email,ra,senha,telefone,curso,periodo,data_nasc,endereco_aluno) " +
                "values (?,?,?,?,?,?,?,?,?,?)";
        var ps = getConnection().prepareStatement(sqlInsert);
        ps.setString(1,aluno.getNome());
        ps.setString(2,aluno.getCpf());
        ps.setString(3,aluno.getEmail());

        int raFinal;
        if (aluno.getRa() != null) {
            raFinal = aluno.getRa();
        } else {
            // Gera o RA automático caso não exista
            raFinal = (int)(Math.random() * 900000) + 100000;
        }
        // Envia o RA definido para a posição 4
        ps.setInt(4, raFinal);

        // 2. Agora tratamos a senha na posição 5 de forma segura
        if (aluno.getSenha() != null && !aluno.getSenha().trim().isEmpty()) {
            ps.setString(5, aluno.getSenha());
        } else {
            // Se não tiver senha, usa o raFinal (convertido para String) com total segurança
            ps.setString(5, String.valueOf(raFinal));
        }

        ps.setString(6,aluno.getTelefone());
        ps.setString(7,aluno.getCurso());

        if (aluno.getPeriodo() != null) {
            ps.setInt(8, aluno.getPeriodo());
        } else {
            ps.setNull(8, java.sql.Types.INTEGER);
        }

        ps.setString(9,aluno.getDataNascimento());
        ps.setLong(10,aluno.getIdEnderecoAluno());

        ps.execute();
    }

    public void atualizar(Aluno aluno) throws SQLException{
        var sqlUpdate = "update alunos set nome=?, cpf=?,email=?,ra=?,senha=?,telefone=?,curso=?,periodo=?,data_nasc=?,endereco_aluno=? " +
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
        ps.setLong(9, aluno.getId());
        ps.setInt(10,aluno.getRa());
        ps.setString(11, aluno.getSenha());

        ps.execute();
    }
}
