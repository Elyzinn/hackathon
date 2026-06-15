package userHackathon.gui;

import userHackathon.service.AlunoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class AlunoGUI extends JFrame implements PainelDefault{
    private final AlunoService service;

    private JTable tabela;
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

    public AlunoGUI() throws HeadlessException{

        this.service = new AlunoService();

        setTitle("Gerenciamento");
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(null);

        tabela = new JTable(getTabelaModel());
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(30,2220,725,200);
        painel.add((scrollPane));

        tabela.getSelectionModel().addListSelectionListener(this::selecionarAluno);

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

        btnImportar.setBounds(30,470,150,40);
        btnIncluir.setBounds(200,470,150,40);

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

        btnImportar.addActionListener(e -> acaoImportarTxt());

        getContentPane().add(painel,BorderLayout.CENTER);
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

}
