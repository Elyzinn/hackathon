package userHackathon.gui;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import userHackathon.service.AlunoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;


public class AlunoGUI extends JFrame implements PainelDefault{
    private final AlunoService service;

    JPanel painel = new JPanel(null);

    private JTextField idField = new JTextField();
    private JTextField nomeField = new JTextField();
    private JTextField cpfField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField telefoneField = new JTextField();
    private JTextField cursoField = new JTextField();
    private JTextField periodoField = new JTextField();
    private JTextField dataNascimentoField = new JTextField();
    private JTextField idEnderecoAlunoField = new JTextField();

    private JLabel idLabel = new JLabel("ID");
    private JLabel nomeLabel = new JLabel("Nome");
    private JLabel cpfLabel = new JLabel("CPF");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel telefoneLabel = new JLabel("Telefone");
    private JLabel cursoLabel = new JLabel("Curso");
    private JLabel periodoLabel = new JLabel("Periodo");
    private JLabel dataNascimentoLabel = new JLabel("Data de Nascimento");
    private JLabel idEnderecoAlunoLabel = new JLabel("Endereço");

    private JButton btnIncluir = new JButton("Incluir");
    private JButton btnImportar = new JButton("Importar");
    private JButton btnExportarPdf = new JButton("Exportar PDF");
    private JButton btnExportarTxt = new JButton("Exportar TXT");
    private JButton btnExportarCsv = new JButton("Exportar CSV");



    private JTable tabela = new JTable();
    private JScrollPane scrollPane;


    public AlunoGUI() throws HeadlessException{

        this.service = new AlunoService();

        setTitle("Gerenciamento");
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        tabela.setModel(getTabelaModel());
        tabela.setDefaultEditor(Object.class,null);
        tabela.getSelectionModel().addListSelectionListener(this::selecionarAluno);
        scrollPane = new JScrollPane(tabela);

        scrollPane.setBounds(30,20,725,200);
        painel.add(scrollPane);

        idLabel.setBounds(30,240,50,20);
        idField.setBounds(30,260,50,30);

        nomeLabel.setBounds(100,240,300,20);
        nomeField.setBounds(100,260,300,30);

        cpfLabel.setBounds(460,240,100,20);
        cpfField.setBounds(460,260,160,30);

        emailLabel.setBounds(30,305,100,20);
        emailField.setBounds(30,325,240,30);

        telefoneLabel.setBounds(285,305,150,20);
        telefoneField.setBounds(285,325,220,30);

        cursoLabel.setBounds(520,305,150,20);
        cursoField.setBounds(520,325,150,30);

        periodoLabel.setBounds(30,370,100,20);
        periodoField.setBounds(30,390,100,30);

        dataNascimentoLabel.setBounds(520,370,100,20);
        dataNascimentoField.setBounds(520,390,100,30);

        idEnderecoAlunoLabel.setBounds(320,370,100,20);
        idEnderecoAlunoField.setBounds(320,390,100,30);

        btnExportarTxt.setBounds(370, 520, 150, 40);
        painel.add(btnExportarTxt);
        btnExportarTxt.addActionListener(e -> acaoExportarTxt());

        btnImportar.setBounds(100, 470, 150, 40);
        btnIncluir.setBounds(270, 470, 150, 40);
        btnExportarPdf.setBounds(440, 470, 150, 40);

        btnExportarCsv.setBounds(165, 525, 150, 40);
        btnExportarTxt.setBounds(335, 525, 150, 40);

        painel.add(btnExportarCsv);
        btnExportarCsv.addActionListener(e -> acaoExportarCsv());

        painel.add(idLabel);painel.add(idField);
        painel.add(nomeLabel);painel.add(nomeField);
        painel.add(cpfLabel);painel.add(cpfField);
        painel.add(emailLabel);painel.add(emailField);
        painel.add(telefoneLabel);painel.add(telefoneField);
        painel.add(cursoLabel);painel.add(cursoField);
        painel.add(periodoLabel);painel.add(periodoField);
        painel.add(dataNascimentoLabel);painel.add(dataNascimentoField);
        painel.add(idEnderecoAlunoLabel);painel.add(idEnderecoAlunoField);
        painel.add(btnIncluir);painel.add(btnImportar);
        painel.add(btnExportarPdf);

        btnImportar.addActionListener(e -> acaoImportarTxt());
        btnExportarPdf.addActionListener(e -> acaoExportarPdf());



        getContentPane().add(painel, BorderLayout.CENTER);
    }

    private DefaultTableModel getTabelaModel() {
        var tabelaModel = new DefaultTableModel();
        tabelaModel.addColumn("Id");
        tabelaModel.addColumn("Nome");
        tabelaModel.addColumn("CPF");

        service.listar().forEach(aluno -> {
            tabelaModel.addRow(new Object[]{
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getCpf()
            });
        });
        return tabelaModel;
    }

    private void selecionarAluno(ListSelectionEvent event) {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            java.util.List<userHackathon.model.Aluno> alunos = service.listar();
            userHackathon.model.Aluno aluno = alunos.get(selectedRow);

            idField.setText(aluno.getId().toString());
            nomeField.setText(aluno.getNome());
            cpfField.setText(aluno.getCpf());
            emailField.setText(aluno.getEmail());
            telefoneField.setText(aluno.getTelefone());
            cursoField.setText(aluno.getCurso());
            periodoField.setText(aluno.getPeriodo().toString());
            dataNascimentoField.setText(aluno.getDataNascimento());
            idEnderecoAlunoField.setText(aluno.getIdEnderecoAluno().toString());

        }
    }

    private void acaoImportarTxt(){
        JFileChooser fileChooser = new JFileChooser();
        int retorno = fileChooser.showOpenDialog(this);
        if(retorno == JFileChooser.APPROVE_OPTION){
            File arquivoSelecionado = fileChooser.getSelectedFile();
            try{
                service.importarTxt(arquivoSelecionado);
                JOptionPane.showMessageDialog(this, "Alunos importados com sucesso");
                tabela.setModel(getTabelaModel());
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }

    private void acaoExportarTxt() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("relatorio_alunos.txt"));
        int retorno = fileChooser.showSaveDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();

            if (!arquivoSelecionado.getName().endsWith(".txt")) {
                arquivoSelecionado = new File(arquivoSelecionado.getAbsolutePath() + ".txt");
            }

            try {
                gerarTxt(arquivoSelecionado);
                JOptionPane.showMessageDialog(this, "TXT exportado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao exportar: " + e.getMessage());
            }
        }
    }

    private void gerarTxt(File arquivo) throws Exception {
        java.util.List<userHackathon.model.Aluno> alunos = service.listar();

        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(arquivo))) {
            writer.write("ID | Nome | CPF");
            writer.newLine();
            writer.write("--------------------------------");
            writer.newLine();

            for (userHackathon.model.Aluno aluno : alunos) {
                writer.write(aluno.getId() + " | " + aluno.getNome() + " | " + aluno.getCpf());
                writer.newLine();
            }
        }
    }

    // NOVO
    private void acaoExportarPdf() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("relatorio_alunos.pdf"));
        int retorno = fileChooser.showSaveDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();

            if (!arquivoSelecionado.getName().endsWith(".pdf")) {
                arquivoSelecionado = new File(arquivoSelecionado.getAbsolutePath() + ".pdf");
            }

            try {
                gerarPdf(arquivoSelecionado);
                JOptionPane.showMessageDialog(this, "Relatório exportado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao exportar: " + e.getMessage());
            }
        }
    }

    private void gerarPdf(File arquivo) throws Exception {
        java.util.List<userHackathon.model.Aluno> alunos = service.listar();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(arquivo));
        document.open();

        Font fonteTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        document.add(new Paragraph("Relatório de Alunos", fonteTitulo));
        document.add(new Paragraph(" "));

        PdfPTable tabela = new PdfPTable(3);
        tabela.setWidthPercentage(100);

        tabela.addCell("ID");
        tabela.addCell("Nome");
        tabela.addCell("CPF");

        for (userHackathon.model.Aluno aluno : alunos) {
            tabela.addCell(aluno.getId().toString());
            tabela.addCell(aluno.getNome());
            tabela.addCell(aluno.getCpf());
        }

        document.add(tabela);
        document.close();
    }

    private void acaoExportarCsv() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("relatorio_alunos.csv"));
        int retorno = fileChooser.showSaveDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();

            if (!arquivoSelecionado.getName().endsWith(".csv")) {
                arquivoSelecionado = new File(arquivoSelecionado.getAbsolutePath() + ".csv");
            }

            try {
                gerarCsv(arquivoSelecionado);
                JOptionPane.showMessageDialog(this, "CSV exportado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao exportar: " + e.getMessage());
            }
        }
    }

    private void gerarCsv(File arquivo) throws Exception {
        java.util.List<userHackathon.model.Aluno> alunos = service.listar();

        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(arquivo))) {
            // Cabeçalho
            writer.write("ID,Nome,CPF");
            writer.newLine();

            // Linhas de dados
            for (userHackathon.model.Aluno aluno : alunos) {
                writer.write(aluno.getId() + "," + aluno.getNome() + "," + aluno.getCpf());
                writer.newLine();
            }
        }
    }

    private void limparCampos(){
        idField.setText("");
        nomeField.setText("");
        cpfField.setText("");
        emailField.setText("");
        telefoneField.setText("");
        cursoField.setText("");
        periodoField.setText("");
        dataNascimentoField.setText("");
        idEnderecoAlunoField.setText("");
    }

    public JButton getBtnExportarCsv() {
        return btnExportarCsv;
    }

    public void setBtnExportarCsv(JButton btnExportarCsv) {
        this.btnExportarCsv = btnExportarCsv;
    }
}
