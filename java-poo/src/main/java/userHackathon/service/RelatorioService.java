package userHackathon.service;

import userHackathon.dao.RelatoriosDao;
import userHackathon.model.Relatorios;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class RelatorioService {

    private final RelatoriosDao relatoriosDao;

    public RelatorioService() {
        this.relatoriosDao = new RelatoriosDao();
    }

    public boolean salvarRelatorio(Relatorios relatorio) {
        if (relatorio == null) {
            System.out.println("[Service] Relatório inválido (nulo).");
            return false;
        }
        try {
            return relatoriosDao.salvar(relatorio); // Chama o salvar do seu DAO
        } catch (SQLException e) {
            System.out.println("[Erro SQL ao Salvar] " + e.getMessage());
            return false;
        }
    }

    public List<Relatorios> listar() {
        try {
            return relatoriosDao.listar();
        } catch (SQLException e) {
            System.out.println("[Erro SQL ao Listar] " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean importarTxt(File arquivo) {
        if (arquivo == null || !arquivo.exists()) {
            System.out.println("[Service] Arquivo TXT inválido ou não encontrado.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    String[] dados = linha.split(";");

                    if (dados.length >= 5) {
                        Relatorios novoRelatorio = new Relatorios();

                        novoRelatorio.setId_empresas(Long.parseLong(dados[0].trim()));
                        novoRelatorio.setId_alunos(Long.parseLong(dados[1].trim()));
                        novoRelatorio.setId_vagas(Long.parseLong(dados[2].trim()));
                        novoRelatorio.setId_candidaturas(Long.parseLong(dados[3].trim()));
                        novoRelatorio.setId_contratos(Long.parseLong(dados[4].trim()));

                        this.salvarRelatorio(novoRelatorio);
                    }
                }
            }
            System.out.println("[Service] Arquivo de dados importado com sucesso!");
            return true;

        } catch (IOException e) {
            System.out.println("[Service] Falha ao ler o arquivo TXT: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("[Service] Erro: O arquivo TXT contém caracteres onde deveriam ser apenas IDs numéricos.");
            return false;
        }
    }
}
