package userHackathon.service;

import userHackathon.dao.AlunoDao;
import userHackathon.dao.EmpresaDao;
import userHackathon.model.Aluno;
import userHackathon.model.Empresa;

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
            return null;
        }
    }

    public void incluir(Aluno aluno){
        try{
            var dao = new AlunoDao();

            if(aluno.getId() == null){
                dao.inserir(aluno);
            }else{
                dao.atualizar(aluno);
            }
        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    public void importarTxt(File arquivo) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(";");
                if (dados.length >= 3){
                    Aluno novo = new Aluno();
                    novo.setNome(dados[0].trim());
                    novo.setCpf(dados[1].trim());
                    novo.setEmail(dados[2].trim());
                    novo.setTelefone(dados[3].trim());
                    novo.setCurso(dados[4].trim());
                    //novo.setPeriodo(dados[5].trim());
                    novo.setDataNascimento(dados[6].trim());
                    //novo.setIdEnderecoAluno();
                    incluir(novo);
                }
            }
        }
    }
}
