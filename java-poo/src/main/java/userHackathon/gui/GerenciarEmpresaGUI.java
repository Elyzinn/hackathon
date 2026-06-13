package userHackathon.gui;

import userHackathon.service.EmpresaService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GerenciarEmpresaGUI extends JFrame implements PainelDefault{

    private final EmpresaService service;

    private JPanel painel = new JPanel(new GridBagLayout());

    private JLabel idLabel = new JLabel("ID");
    private JTextField idField = new JTextField(20);

    private JLabel razaoSocialLabel = new JLabel("Razão Social");
    private JTextField razaoSocialField = new JTextField(20);

    private JLabel cnpjLabel = new JLabel("CNPJ");
    private JTextField cnpjField = new JTextField(20);

    private JLabel emailLabel = new JLabel("Email");
    private JTextField emailField = new JTextField(20);

    private JLabel telefoneContatoLabel = new JLabel("Telefone Contato");
    private JTextField telefoneContatoField = new JTextField(20);

    private JLabel responsavelLabel = new JLabel("Responsável");
    private JTextField responsavelField = new JTextField(20);

    private JLabel idEnderecoLabel = new JLabel("Endereço Empresa");
    private JTextField idEnderecoField = new JTextField(20);

    private JButton botaoAprovar = new JButton("Aprovar");
    private JButton botaoRejeitar = new JButton("Rejeitar");

    private JTable tabela = new JTable();

    public GerenciarEmpresaGUI() throws HeadlessException{
        this.service = new EmpresaService();

        setTitle("Gerenciamento");
        setSize(800,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        painelAdd(painel, idLabel,0,0);
        painelAdd(painel,idField,1,0);

        painelAdd(painel,razaoSocialLabel,0,1);
        painelAdd(painel,razaoSocialField,1,1);

        painelAdd(painel,cnpjLabel,0,2);
        painelAdd(painel,cnpjField,1,2);

        painelAdd(painel,emailLabel,0,3);
        painelAdd(painel,emailField,1,3);

        painelAdd(painel,telefoneContatoLabel,0,4);
        painelAdd(painel,telefoneContatoField,1,4);

        painelAdd(painel,responsavelLabel,0,5);
        painelAdd(painel,responsavelField,1,5);

        painelAdd(painel,idEnderecoLabel,0,6);
        painelAdd(painel,idEnderecoField,1,6);

        painelAdd(painel,botaoAprovar,0,7);
        painelAdd(painel,botaoRejeitar,2,7);

        getContentPane().add(painel, BorderLayout.CENTER);
        getContentPane().add(getTabelaPanel(),BorderLayout.NORTH);
    }

    //cria novas colunas que receberam os dados de cada get.
    private DefaultTableModel getTabelaModel(){
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

    private JPanel getTabelaPanel(){
        tabela.setModel(getTabelaModel());
        tabela.setDefaultEditor(Object.class,null);
        tabela.getSelectionModel().addListSelectionListener(this::selecionarEmpresa);

        tabela.setModel(getTabelaModel());
        var scrollPanel = new JScrollPane(tabela);

        var painelTabela = new JPanel(new BorderLayout());
        painelTabela.add(scrollPanel,BorderLayout.NORTH);

        return painelTabela;
    }

    private void selecionarEmpresa(ListSelectionEvent event){
        int selectedRow = tabela.getSelectedRow();
        if(selectedRow != -1){

            var id = (Long) tabela.getValueAt(selectedRow, 0);
            var razaoSocial = (String) tabela.getValueAt(selectedRow, 1);
            var cnpj = (String) tabela.getValueAt(selectedRow,2);

            idField.setText(id.toString());
            razaoSocialField.setText(razaoSocial.toString());
            cnpjField.setText(cnpj.toString());
        }
    }

}
