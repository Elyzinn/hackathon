package userHackathon.gui;

import userHackathon.model.Relatorios;
import userHackathon.service.RelatorioService;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

public class RelatorioGUI extends JFrame implements PainelDefault {
    private final RelatorioService service;

    private JTable tabela;
    private JTextField id_empresasField = new JTextField();
    private JTextField id_alunosField = new JTextField();
    private JTextField id_vagasField = new JTextField();
    private JTextField id_candidaturasField = new JTextField();
    private JTextField id_contratosField = new JTextField();

    private JLabel id_empresasJLabel = new JLabel("Empresa");
    private JLabel id_alunosJLabel = new JLabel("Aluno");
    private JLabel id_vagasJLabel = new JLabel("Vaga");
    private JLabel id_candidaturasJLabel = new JLabel("Candidatura");
    private JLabel id_contratosJLabel = new JLabel("Contrato");

    private JButton btnIncluir = new JButton("Incluir");
    private JButton btnImportar = new JButton("Importar");
    private JButton btnExportarTxt = new JButton("Exportar TXT");
    private JButton btnExportarPdf = new JButton("Exportar PDF");
    private JButton btnExportarCsv = new JButton("Exportar CSV");

    public RelatorioGUI() throws HeadlessException{

        this.service = new RelatorioService();

        setTitle("Gerenciamento de Vagas e Relatórios");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(null);

       // tabela = new JTable(getTabelaModel());
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(30, 20, 725, 200);
        painel.add(scrollPane);

        id_empresasJLabel.setBounds(100,240,300,20);
        id_empresasField.setBounds(100,260,300,30);

        id_alunosJLabel.setBounds(460,240,100,20);
        id_alunosField.setBounds(460,260,160,30);

        id_vagasJLabel.setBounds(30,305,100,20);
        id_vagasField.setBounds(30,325,240,30);

        id_candidaturasJLabel.setBounds(285,305,150,20);
        id_candidaturasField.setBounds(285,325,220,30);

        id_alunosJLabel.setBounds(400, 240, 100, 20);
        id_alunosField.setBounds(400, 260, 355, 30);

        btnImportar.setBounds(30,470,150,40);
        btnIncluir.setBounds(200,470,150,40);

        painel.add(id_empresasJLabel);painel.add(id_empresasJLabel);
        painel.add(id_alunosJLabel);painel.add(id_alunosJLabel);
        painel.add(id_vagasJLabel);painel.add(id_vagasJLabel);
        painel.add(id_candidaturasJLabel);painel.add(id_candidaturasJLabel);
        painel.add(id_contratosJLabel);painel.add(id_contratosJLabel);
//        painel.add(periodoLabel);painel.add(periodoField);
//        painel.add(dataNascimentoLabel);painel.add(dataNascimentoField);
//        painel.add(idEnderecoAlunoLabel);painel.add(idEnderecoAlunoField);
        painel.add(btnIncluir);painel.add(btnImportar);

        btnImportar.setBounds(30, 470, 130, 40);
        btnIncluir.setBounds(175, 470, 130, 40);

        btnExportarTxt.setBounds(340, 470, 130, 40);
        btnExportarPdf.setBounds(485, 470, 130, 40);
        btnExportarCsv.setBounds(630, 470, 125, 40);

        painel.add(id_empresasJLabel); painel.add(id_empresasField);
        painel.add(id_alunosJLabel); painel.add(id_alunosField);
        painel.add(id_vagasJLabel); painel.add(id_vagasField);
        painel.add(id_candidaturasJLabel); painel.add(id_candidaturasField);
        painel.add(id_contratosJLabel); painel.add(id_contratosField);

        painel.add(btnIncluir); painel.add(btnImportar);
        painel.add(btnExportarTxt); painel.add(btnExportarPdf); painel.add(btnExportarCsv);

        btnImportar.addActionListener(e -> acaoImportarTxt());
        btnExportarTxt.addActionListener(e -> acaoExportarTxt());
        btnExportarPdf.addActionListener(e -> acaoExportarPdf());
        btnExportarCsv.addActionListener(e -> acaoExportarCsv());

        getContentPane().add(painel, BorderLayout.CENTER);
    }

    private DefaultTableModel getTabelaModel() {
        var tabelaModel = new DefaultTableModel();
        // COLUNAS CORRIGIDAS: Removido o "ID " do cabeçalho da tabela
        tabelaModel.addColumn("Aluno");
        tabelaModel.addColumn("Empresa");
        tabelaModel.addColumn("Vaga");
        tabelaModel.addColumn("Candidatura");
        tabelaModel.addColumn("Contrato");

        var lista = service.listar();
        if (lista != null) {
            lista.forEach(relatorios -> {
                tabelaModel.addRow(new Object[]{
                        relatorios.getId_alunos(),
                        relatorios.getId_empresas(),
                        relatorios.getId_vagas(),
                        relatorios.getId_candidaturas(),
                        relatorios.getId_contratos()
                });
            });
        }
        return tabelaModel;
    }

    private void selecionarAluno(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                java.util.List<Relatorios> listarRelatorios = service.listar();
                if (listarRelatorios != null && selectedRow < listarRelatorios.size()) {
                    Relatorios relatorioSelecionado = listarRelatorios.get(selectedRow);

                    id_empresasField.setText(String.valueOf(relatorioSelecionado.getId_empresas()));
                    id_alunosField.setText(String.valueOf(relatorioSelecionado.getId_alunos()));
                    id_vagasField.setText(String.valueOf(relatorioSelecionado.getId_vagas()));
                    id_candidaturasField.setText(String.valueOf(relatorioSelecionado.getId_candidaturas()));
                    id_contratosField.setText(String.valueOf(relatorioSelecionado.getId_contratos()));
                }
            }
        }
    }

    private void acaoImportarTxt() {
        JFileChooser fileChooser = new JFileChooser();
        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            try {
                service.importarTxt(arquivoSelecionado);
                JOptionPane.showMessageDialog(this, "Relatórios importados com sucesso!");
                tabela.setModel(getTabelaModel());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void acaoExportarTxt() {
        java.util.List<Relatorios> lista = service.listar();
        if (lista == null || lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há dados para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_vagas.txt"))) {
            writer.println("=== RELATÓRIO DE VAGAS E CANDIDATURAS ===");
            writer.println("Aluno | Empresa | Vaga | Candidatura | Contrato");
            writer.println("------------------------------------------------------------");
            for (Relatorios r : lista) {
                writer.printf("%d | %d | %d | %d | %d\n",
                        r.getId_alunos(), r.getId_empresas(), r.getId_vagas(), r.getId_candidaturas(), r.getId_contratos());
            }
            JOptionPane.showMessageDialog(this, "Dados exportados para 'relatorio_vagas.txt' com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar TXT: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void acaoExportarPdf() {
        java.util.List<Relatorios> lista = service.listar();
        if (lista == null || lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há dados para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("relatorio_vagas.pdf"));
            documento.open();
            documento.add(new Paragraph("=== RELATÓRIO DE VAGAS E CANDIDATURAS ==="));
            documento.add(new Paragraph(" "));

            for (Relatorios r : lista) {
                String linha = String.format("Aluno: %d | Empresa: %d | Vaga: %d | Candidatura: %d | Contrato: %d",
                        r.getId_alunos(), r.getId_empresas(), r.getId_vagas(), r.getId_candidaturas(), r.getId_contratos());
                documento.add(new Paragraph(linha));
            }

            JOptionPane.showMessageDialog(this, "Dados exportados para 'relatorio_vagas.pdf' com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar PDF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }

    private void acaoExportarCsv() {
        java.util.List<Relatorios> lista = service.listar();
        if (lista == null || lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há dados para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_vagas.csv"))) {
            writer.println("Aluno;Empresa;Vaga;Candidatura;Contrato");
            for (Relatorios r : lista) {
                writer.printf("%d;%d;%d;%d;%d\n",
                        r.getId_alunos(), r.getId_empresas(), r.getId_vagas(), r.getId_candidaturas(), r.getId_contratos());
            }
            JOptionPane.showMessageDialog(this, "Dados exportados para 'relatorio_vagas.csv' com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar CSV: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        id_empresasField.setText("");
        id_alunosField.setText("");
        id_vagasField.setText("");
        id_candidaturasField.setText("");
        id_contratosField.setText("");
    }
}