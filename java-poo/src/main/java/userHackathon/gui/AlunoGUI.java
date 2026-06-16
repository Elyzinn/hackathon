package userHackathon.gui;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import userHackathon.service.AlunoService;
import userHackathon.util.ActionManager;

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
    private JTextField logradouroField = new JTextField();
    private JTextField numLogradouroField = new JTextField();
    private JTextField bairroField = new JTextField();
    private JTextField cepField = new JTextField();
    private JTextField cidadeField = new JTextField();
    private JTextField ufField = new JTextField();
    private JTextField raField = new JTextField();

    private JLabel idLabel = new JLabel("ID");
    private JLabel nomeLabel = new JLabel("Nome");
    private JLabel cpfLabel = new JLabel("CPF");
    private JLabel emailLabel = new JLabel("Email");
    private JLabel telefoneLabel = new JLabel("Telefone");
    private JLabel cursoLabel = new JLabel("Curso");
    private JLabel periodoLabel = new JLabel("Periodo");
    private JLabel dataNascimentoLabel = new JLabel("Data de Nascimento");
    private JLabel idEnderecoAlunoLabel = new JLabel("Endereço");
    private JLabel logradouroLabel = new JLabel("Logradouro");
    private JLabel numLogradouroLabel = new JLabel("Nº");
    private JLabel bairroLabel = new JLabel("Bairro");
    private JLabel cepLabel = new JLabel("CEP");
    private JLabel cidadeLabel = new JLabel("Cidade");
    private JLabel ufLabel = new JLabel("UF");
    private JLabel raLabel = new JLabel("RA");

    private JButton btnIncluir = new JButton("Incluir");
    private JButton btnImportar = new JButton("Importar");

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
        nomeField.setBounds(100,260,200,30);

        cpfLabel.setBounds(320,240,100,20);
        cpfField.setBounds(320,260,100,30);

        emailLabel.setBounds(30,305,100,20);
        emailField.setBounds(30,325,170,30);

        telefoneLabel.setBounds(220,305,150,20);
        telefoneField.setBounds(220,325,100,30);

        cursoLabel.setBounds(350,305,150,20);
        cursoField.setBounds(350,325,150,30);

        periodoLabel.setBounds(520,305,100,20);
        periodoField.setBounds(520,325,50,30);

        dataNascimentoLabel.setBounds(440,240,100,20);
        dataNascimentoField.setBounds(440,260,100,30);

        raLabel.setBounds(560,240,50,20);
        raField.setBounds(560,260,50,30);

        idEnderecoAlunoLabel.setBounds(30,370,50,20);
        idEnderecoAlunoField.setBounds(30,390,30,30);

        logradouroLabel.setBounds(90,370,150,20);
        logradouroField.setBounds(90,390,150,30);

        numLogradouroLabel.setBounds(260,370,30,20);
        numLogradouroField.setBounds(260,390,50,30);

        bairroLabel.setBounds(320,370,120,20);
        bairroField.setBounds(320,390,120,30);

        cepLabel.setBounds(450,370,100,20);
        cepField.setBounds(450,390,100,30);

        ufLabel.setBounds(570,370,30,20);
        ufField.setBounds(570,390,30,30);

        btnImportar.setBounds(100, 470, 150, 40);
        btnIncluir.setBounds(450, 470, 150, 40);

        painel.add(idLabel);painel.add(idField);
        painel.add(nomeLabel);painel.add(nomeField);
        painel.add(cpfLabel);painel.add(cpfField);
        painel.add(emailLabel);painel.add(emailField);
        painel.add(telefoneLabel);painel.add(telefoneField);
        painel.add(cursoLabel);painel.add(cursoField);
        painel.add(periodoLabel);painel.add(periodoField);
        painel.add(dataNascimentoLabel);painel.add(dataNascimentoField);
        painel.add(raLabel);painel.add(raField);
        painel.add(idEnderecoAlunoLabel);painel.add(idEnderecoAlunoField);
        painel.add(logradouroLabel);painel.add(logradouroField);
        painel.add(numLogradouroLabel);painel.add(numLogradouroField);
        painel.add(cepLabel);painel.add(cepField);
        painel.add(bairroLabel);painel.add(bairroField);
        painel.add(cidadeLabel);painel.add(cidadeField);
        painel.add(ufLabel);painel.add(ufField);

        painel.add(btnIncluir);painel.add(btnImportar);

        btnImportar.addActionListener(e -> acaoImportarTxt());

        ActionManager.configurarBotaoIncluirAluno(
                btnIncluir,
                nomeField, cpfField, emailField, telefoneField, cursoField, periodoField, dataNascimentoField,
                logradouroField, numLogradouroField, bairroField, cepField, cidadeField, ufField,
                this.service,
                () -> {
                    limparCampos();
                    tabela.setModel(getTabelaModel());
                }
        );

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

            if (aluno.getEndereco() != null) {

                logradouroField.setText(aluno.getEndereco().getLogradouro());
                numLogradouroField.setText(aluno.getEndereco().getNumLogradouro());
                bairroField.setText(aluno.getEndereco().getBairro());
                cepField.setText(aluno.getEndereco().getCep());
                cidadeField.setText(aluno.getEndereco().getCidade());
                ufField.setText(aluno.getEndereco().getUf());
            } else {

                logradouroField.setText("");
                numLogradouroField.setText("");
                bairroField.setText("");
                cepField.setText("");
                cidadeField.setText("");
                ufField.setText("");
            }

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

        logradouroField.setText("");
        numLogradouroField.setText("");
        bairroField.setText("");
        cepField.setText("");
        cidadeField.setText("");
        ufField.setText("");
    }

}
