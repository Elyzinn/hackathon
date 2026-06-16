package userHackathon.service;

import userHackathon.dao.AlunoDao;
import userHackathon.model.Aluno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AlunoService {

    public List<Aluno> listar() {
        try {
            var dao = new AlunoDao();
            return dao.listar();
        } catch (Exception e) {
            System.out.println("[Service] Erro ao listar alunos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean salvarAluno(Aluno aluno) {
        if (aluno == null) {
            System.out.println("[Service] Aluno inválido (nulo).");
            return false;
        }
        try {
            var dao = new AlunoDao();

            if (aluno.getPeriodo() == null) {
                aluno.setPeriodo(1);
            }

            if (aluno.getId() == null) {
                dao.inserir(aluno);
            } else {
                dao.atualizar(aluno);
            }
            return true;

        } catch (Exception e) {
            System.out.println("[Service] Erro ao salvar aluno: " + e.getMessage());
            return false;
        }
    }

    public void importarTxt(File arquivo) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; // Pula linhas vazias

                String[] dados = linha.split(";");
                if (dados.length >= 7) {
                    Aluno novo = new Aluno();
                    novo.setNome(dados[0].trim());
                    novo.setCpf(dados[1].trim());
                    novo.setEmail(dados[2].trim());
                    novo.setTelefone(dados[3].trim());
                    novo.setCurso(dados[4].trim());
                    // novo.setPeriodo(dados[5].trim()); // Se for usar futuramente
                    novo.setDataNascimento(dados[6].trim());

                    this.salvarAluno(novo);
                }
            }
        }
    }
}