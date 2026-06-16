package userHackathon.gui;

import userHackathon.service.RelatorioService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class RelatorioGUI extends JFrame implements PainelDefault{
    private final RelatoriosService service;

    private JTable tabela;
    private JTextField id_empresasField = new JTextField();
    private JTextField id_alunosField = new JTextField();
    private JTextField id_vagasField = new JTextField();
    private JTextField id_candidaturasField = new JTextField();
    private JTextField id_contratosField = new JTextField();

    private JLabel id_empresasJLabel = new JLabel("id_empresas");
    private JLabel id_alunosJLabel = new JLabel("id_alunos");
    private JLabel id_vagasJLabel = new JLabel("id_vagas");
    private JLabel id_candidaturasJLabel = new JLabel("id_candidaturas");
    private JLabel id_contratosJLabel = new JLabel("id_contratos");

    private JButton btnIncluir = new JButton("Incluir");
    private JButton btnImportar = new JButton("Importar");

    public void RelaroiosGUI() throws HeadlessException{

        this.service = new RelatorioService();

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

        idJLabel.setBounds(30,240,50,20);
        idField.setBounds(30,260,50,30);

        id_empresasJLabel.setBounds(100,240,300,20);
        id_empresasField.setBounds(100,260,300,30);

        id_alunosJLabel.setBounds(460,240,100,20);
        id_alunosField.setBounds(460,260,160,30);

        id_vagasJLabel.setBounds(30,305,100,20);
        id_vagasField.setBounds(30,325,240,30);

        id_candidaturasJLabel.setBounds(285,305,150,20);
        id_candidaturasField.setBounds(285,325,220,30);

        id_contratosJLabel.setBounds(520,305,150,20);
        id_contratosField.setBounds(520,325,150,30);

//        periodoLabel.setBounds(30,370,100,20);
//        periodoField.setBounds(30,390,100,30);
//
//        dataNascimentoLabel.setBounds(520,370,100,20);
//        dataNascimentoField.setBounds(520,390,100,30);
//
//        idEnderecoAlunoLabel.setBounds(320,370,100,20);
//        idEnderecoAlunoField.setBounds(320,390,100,30);

        btnImportar.setBounds(30,470,150,40);
        btnIncluir.setBounds(200,470,150,40);

        painel.add(idJLabel);painel.add(idField);
        painel.add(id_empresasJLabel);painel.add(id_empresasJLabel);
        painel.add(id_alunosJLabel);painel.add(id_alunosJLabel);
        painel.add(id_vagasJLabel);painel.add(id_vagasJLabel);
        painel.add(id_candidaturasJLabel);painel.add(id_candidaturasJLabel);
        painel.add(id_contratosJLabel);painel.add(id_contratosJLabel);
//        painel.add(periodoLabel);painel.add(periodoField);
//        painel.add(dataNascimentoLabel);painel.add(dataNascimentoField);
//        painel.add(idEnderecoAlunoLabel);painel.add(idEnderecoAlunoField);
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
            id_empresasField.setText(aluno.getNome());
            id_alunosField.setText(aluno.getCpf());
            id_vagasField.setText(aluno.getEmail());
            id_candidaturasField.setText(aluno.getTelefone());
            id_contratosField.setText(aluno.getCurso());
//            periodoField.setText(aluno.getPeriodo().toString());
//            dataNascimentoField.setText(aluno.getDataNascimento());
//            idEnderecoAlunoField.setText(aluno.getIdEnderecoAluno().toString());

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
        id_empresasField.setText("");
        id_alunosField.setText("");
        id_vagasField.setText("");
        id_candidaturasField.setText("");
        id_contratosField.setText("");
//        periodoField.setText("");
//        dataNascimentoField.setText("");
//        idEnderecoAlunoField.setText("");
    }

}
