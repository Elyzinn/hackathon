package userHackathon.gui;

import userHackathon.model.User;
import userHackathon.service.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserGui extends JFrame implements PainelDefault {
    // descomentar ao finalizar o service
    private UserService service;
    private JPanel painel = new JPanel(new GridBagLayout());
    private JLabel idLabel = new JLabel("Id");
    private JTextField idField = new JTextField(20);
    private JLabel nameLabel = new JLabel("Id");
    private JTextField nameField = new JTextField(20);
    private JLabel emailLabel = new JLabel("Id");
    private JTextField emailField = new JTextField(20);
    private JLabel passwordLabel = new JLabel("Id");
    private JTextField passwordField = new JTextField(20);
    private JButton botaoEnviar = new JButton("Enviar");
    private JButton botaoDeletar = new JButton("Deletar");
    private JTable table = new JTable();

    public UserGui() throws HeadlessException{
        // descomentar ao finalizar o service
        this.service = new UserService();

        setTitle("Usuarios");
        setSize(800,500);
        setLocationRelativeTo(null);
        painelAdd(painel, idLabel, 0,0);
        painelAdd(painel, idField, 1, 0);

        painelAdd(painel, nameLabel, 0,1);
        painelAdd(painel, nameField, 1, 1);

        painelAdd(painel, emailLabel, 0,2);
        painelAdd(painel, emailField, 1, 2);

        painelAdd(painel, passwordLabel, 0,3);
        painelAdd(painel, passwordField, 1, 3);

        painelAdd(painel, botaoEnviar, 0, 4);
        painelAdd(painel, botaoEnviar, 1, 4);

        botaoEnviar.addActionListener(this::enviar);
        botaoDeletar.addActionListener(this::deletar);
        getContentPane().add(painel, BorderLayout.NORTH);
        getContentPane().add(getTablePanel(), BorderLayout.CENTER);
    }

    private DefaultTableModel getTableModel(){
        var tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        //retirar retornos de senha para hackathon
        tableModel.addColumn("Senha");
        service.listar().forEach(user -> {
            tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword()
            });
        });

        return tableModel;
    }

    private void selecionarUsers(ListSelectionEvent listSelectionEvent){
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1){
            var id = (Long) table.getValueAt(selectedRow,0);
            var name = (String) table.getValueAt(selectedRow,1);
            var email = (String) table.getValueAt(selectedRow,2);
            var password = (String) table.getValueAt(selectedRow,3);

            idField.setText(id.toString());
            nameField.setText(name);
            emailField.setText(email);
            passwordField.setText(password);
        }
    }

    private JPanel getTablePanel(){
        table.setModel(getTableModel());
        table.setDefaultEditor(Object.class, null);
        table.getSelectionModel().addListSelectionListener(this::selecionarUsers);
        var scrollPnael = new JScrollPane(table);
        var tablePainel =new JPanel(new BorderLayout());

        tablePainel.add(scrollPnael, BorderLayout.CENTER);
        return tablePainel;
    }

    private void enviar(ActionEvent event) {
        var user = new User();
        if(!idField.getText().equals("")){
            var id = Long.valueOf(idField.getText());
            user.setId(id);
        }
        user.setName(nameField.getText());
        user.setEmail(emailField.getText());
        user.setPassword(passwordField.getText());

        service.salvar(user);
        limparcampos();
        table.setModel(getTableModel());
    }

    private void deletar(ActionEvent event) {
        var user = new User();
        if (!idField.getText().equals("")){
            var id = Long.valueOf(idField.getText());
            user.setId(id);
        }

        service.deletar(user);
        limparcampos();
        table.setModel(getTableModel());
    }

    private void limparcampos() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }

}
