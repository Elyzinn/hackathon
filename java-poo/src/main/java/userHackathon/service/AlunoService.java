package userHackathon.service;

import userHackathon.dao.AlunoDao;
import userHackathon.dao.EnderecoAlunoDao;
import userHackathon.model.Aluno;
import userHackathon.model.EnderecoAluno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class AlunoService {

    public List<Aluno> listar() {
        try {
            var dao = new AlunoDao();

            return dao.listar();
        } catch (Exception e) {
            System.out.println("ERRO" + e.getMessage());
            e.getStackTrace();
            return null;
        }
    }

    public void incluir(Aluno aluno, EnderecoAluno endereco){
        try{
            var dao = new AlunoDao();
            var enderecoDao = new EnderecoAlunoDao();

            if(aluno.getId() == null){

                if (endereco != null){

                    long idEnderecoGerado = enderecoDao.inserir(endereco);

                    endereco.setId(idEnderecoGerado);
                    aluno.setIdEnderecoAluno(idEnderecoGerado);

                }

                dao.inserir(aluno);
            }else{
                dao.atualizar(aluno);

                if (aluno.getIdEnderecoAluno() != null){
                    enderecoDao.atualizar(endereco);
                }
            }
        }catch (java.sql.SQLException ex){
            System.out.println(ex.getMessage());
        }
        catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }




//pedir para rever se está certo
    public void importarTxt(File arquivo) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");

                if (dados.length >= 13){
                    Aluno novo = new Aluno();

                    novo.setNome(dados[0].trim());
                    novo.setCpf(dados[1].trim());
                    novo.setEmail(dados[2].trim());
                    novo.setTelefone(dados[3].trim());
                    novo.setCurso(dados[4].trim());

                    if(!dados[5].trim().isEmpty()){
                        novo.setPeriodo(Integer.parseInt(dados[5].trim()));
                    }

                    novo.setDataNascimento(dados[6].trim());

                    EnderecoAluno enderecoNovo = new EnderecoAluno();
                    enderecoNovo.setLogradouro(dados[7].trim());
                    enderecoNovo.setNumLogradouro(dados[8].trim());
                    enderecoNovo.setBairro(dados[9].trim());
                    enderecoNovo.setCep(dados[10].trim());
                    enderecoNovo.setCidade(dados[11].trim());
                    enderecoNovo.setUf(dados[12].trim());

                    incluir(novo, enderecoNovo);
                }
            }
        }
    }

}
