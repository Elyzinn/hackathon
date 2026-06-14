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
        idLabel.setBounds(30, 240, 60, 20);
        idField.setBounds(30, 260, 60, 30);

        razaoSocialLabel.setBounds(105, 240, 200, 20);
        razaoSocialField.setBounds(105, 260, 340, 30);

        cnpjLabel.setBounds(460, 240, 100, 20);
        cnpjField.setBounds(460, 260, 160, 30);

        //FILEIRA 2 (Email, Telefone, Endereço)
        emailLabel.setBounds(30, 305, 100, 20);
        emailField.setBounds(30, 325, 240, 30);

        telefoneContatoLabel.setBounds(285, 305, 150, 20);
        telefoneContatoField.setBounds(285, 325, 220, 30);

        idEnderecoLabel.setBounds(520, 305, 150, 20);
        idEnderecoField.setBounds(520, 325, 150, 30);

        //FILEIRA 3 (Responsável, Status)
        responsavelLabel.setBounds(30, 370, 100, 20);
        responsavelField.setBounds(30, 390, 250, 30);

        statusLabel.setBounds(520,370,100,20);
        statusField.setBounds(520,390,100,30);

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