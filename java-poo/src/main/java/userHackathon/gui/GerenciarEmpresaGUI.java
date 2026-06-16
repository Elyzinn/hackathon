package userHackathon.gui;

import userHackathon.service.EmpresaService;
import userHackathon.util.ActionManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GerenciarEmpresaGUI extends JFrame implements PainelDefault {

    private final EmpresaService service;

    // Alterado para null layout para permitir posicionamento absoluto por pixel
    private JPanel painel = new JPanel(null);

    private JLabel idLabel = new JLabel("ID");
    private JTextField idField = new JTextField();

    private JLabel razaoSocialLabel = new JLabel("Razão Social");
    private JTextField razaoSocialField = new JTextField();

    private JLabel cnpjLabel = new JLabel("CNPJ");
    private JTextField cnpjField = new JTextField();

    private JLabel emailLabel = new JLabel("Email");
    private JTextField emailField = new JTextField();

    private JLabel telefoneContatoLabel = new JLabel("Telefone Contato");
    private JTextField telefoneContatoField = new JTextField();

    private JLabel responsavelLabel = new JLabel("Responsável");
    private JTextField responsavelField = new JTextField();

    private JLabel idEnderecoLabel = new JLabel("Endereço");
    private JTextField idEnderecoField = new JTextField();

    private JLabel statusLabel = new JLabel("Status");
    private JTextField statusField = new JTextField();

    private JButton botaoAprovar = new JButton("Aceitar");
    private JButton botaoRejeitar = new JButton("Bloquear");

    private JLabel logradouroLabel = new JLabel("Logradouro");
    private JLabel numLogradouroLabel = new JLabel("Nº");
    private JLabel bairroLabel = new JLabel("Bairro");
    private JLabel cepLabel = new JLabel("CEP");
    private JLabel cidadeLabel = new JLabel("Cidade");
    private JLabel ufLabel = new JLabel("UF");
    private JLabel raLabel = new JLabel("RA");

    private JTextField logradouroField = new JTextField();
    private JTextField numLogradouroField = new JTextField();
    private JTextField bairroField = new JTextField();
    private JTextField cepField = new JTextField();
    private JTextField cidadeField = new JTextField();
    private JTextField ufField = new JTextField();
    private JTextField raField = new JTextField();

    private JTable tabela = new JTable();
    private JScrollPane scrollPane;

    public GerenciarEmpresaGUI() throws HeadlessException {
        this.service = new EmpresaService();

        setTitle("Gerenciamento");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Evita que o usuário distorça o layout absoluto ao maximizar

        tabela.setModel(getTabelaModel());
        tabela.setDefaultEditor(Object.class, null);
        tabela.getSelectionModel().addListSelectionListener(this::selecionarEmpresa);
        scrollPane = new JScrollPane(tabela);

        scrollPane.setBounds(30, 20, 725, 200);
        painel.add(scrollPane);

        // 2. FILEIRA 1 (ID, Razão Social, CNPJ)
        idLabel.setBounds(30, 240, 30, 20);
        idField.setBounds(30, 260, 30, 30);

        razaoSocialLabel.setBounds(80, 240, 200, 20);
        razaoSocialField.setBounds(80, 260, 240, 30);

        cnpjLabel.setBounds(370, 240, 100, 20);
        cnpjField.setBounds(370, 260, 120, 30);

        //FILEIRA 2 (Email, Telefone, Endereço)
        emailLabel.setBounds(30, 305, 100, 20);
        emailField.setBounds(30, 325, 170, 30);

        telefoneContatoLabel.setBounds(220, 305, 150, 20);
        telefoneContatoField.setBounds(220, 325, 130, 30);

        idEnderecoLabel.setBounds(30, 370, 50, 20);
        idEnderecoField.setBounds(30, 390, 30, 30);

        //FILEIRA 3 (Responsável, Status)
        responsavelLabel.setBounds(370, 305, 100, 20);
        responsavelField.setBounds(370, 325, 250, 30);

        statusLabel.setBounds(510,240,100,20);
        statusField.setBounds(510,260,100,30);

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

        //FILEIRA 4 (Botões de Ação na parte inferior)
        botaoAprovar.setBounds(30, 470, 150, 40);
        botaoRejeitar.setBounds(200, 470, 150, 40);

        painel.add(idLabel); painel.add(idField);
        painel.add(razaoSocialLabel); painel.add(razaoSocialField);
        painel.add(cnpjLabel); painel.add(cnpjField);
        painel.add(emailLabel); painel.add(emailField);
        painel.add(telefoneContatoLabel); painel.add(telefoneContatoField);
        painel.add(idEnderecoLabel); painel.add(idEnderecoField);
        painel.add(responsavelLabel); painel.add(responsavelField);
        painel.add(statusLabel); painel.add(statusField);
        painel.add(logradouroLabel);painel.add(logradouroField);
        painel.add(numLogradouroLabel);painel.add(numLogradouroField);
        painel.add(cepLabel);painel.add(cepField);
        painel.add(bairroLabel);painel.add(bairroField);
        painel.add(cidadeLabel);painel.add(cidadeField);
        painel.add(ufLabel);painel.add(ufField);
        painel.add(botaoAprovar);
        painel.add(botaoRejeitar);

        ActionManager.configurarBotaoStatusEmpresa(botaoAprovar, idField, true, service, tabela, () -> {
            limparCampos();
            statusField.setText("Aprovado");
            tabela.setModel(getTabelaModel());
        });


        ActionManager.configurarBotaoStatusEmpresa(botaoRejeitar, idField, false, service, tabela, () -> {
            limparCampos();
            statusField.setText("Bloqueado");
            tabela.setModel(getTabelaModel());
        });

        getContentPane().add(painel, BorderLayout.CENTER);
    }

    private DefaultTableModel getTabelaModel() {
        var tabelaModel = new DefaultTableModel();
        tabelaModel.addColumn("Id");
        tabelaModel.addColumn("Razão Social");
        tabelaModel.addColumn("CNPJ");

        service.listar().forEach(empresa -> {
            tabelaModel.addRow(new Object[]{
                    empresa.getId(),
                    empresa.getRazaoSocial(),
                    empresa.getCnpj()
            });
        });
        return tabelaModel;
    }

    private void selecionarEmpresa(ListSelectionEvent event) {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {

                //Através da linha selecionada, pegamos diretamente na lista a empresa selecionada
                java.util.List<userHackathon.model.Empresa> empresas = service.listar();
                userHackathon.model.Empresa empresa = empresas.get(selectedRow);

                //Isso exclui a necessidade dessas linhas de código pois ele vai pegar direto da lista então é só setar
                //var id = (Long) tabela.getValueAt(selectedRow, 0);
                //var razaoSocial = (String) tabela.getValueAt(selectedRow, 1);
                //var cnpj = (String) tabela.getValueAt(selectedRow, 2);

                idField.setText(empresa.getId().toString());
                razaoSocialField.setText(empresa.getRazaoSocial());
                cnpjField.setText(empresa.getCnpj());
                emailField.setText(empresa.getEmail());
                telefoneContatoField.setText(empresa.getTelefoneContato());
                idEnderecoField.setText(empresa.getIdEnderecoEmpresa().toString());
                responsavelField.setText(empresa.getResponsavel());

                if(empresa.getStatus() == true){
                    statusField.setText("Aprovado");
                }else {
                    statusField.setText("Bloqueado");
                }

                if (empresa.getEndereco() != null) {

                    logradouroField.setText(empresa.getEndereco().getLogradouro());
                    numLogradouroField.setText(empresa.getEndereco().getNumLogradouro());
                    bairroField.setText(empresa.getEndereco().getBairro());
                    cepField.setText(empresa.getEndereco().getCep());
                    cidadeField.setText(empresa.getEndereco().getCidade());
                    ufField.setText(empresa.getEndereco().getUf());
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

    private void limparCampos(){
        idField.setText("");
        razaoSocialField.setText("");
        cnpjField.setText("");
        emailField.setText("");
        telefoneContatoField.setText("");
        idEnderecoField.setText("");
        responsavelField.setText("");
        statusField.setText("");
    }
}