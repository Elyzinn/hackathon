package userHackathon.gui;

import userHackathon.service.EmpresaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmpresaGUI extends JFrame implements PainelDefault{
    private final EmpresaService service;

    private JPanel painel = new JPanel(new GridBagLayout());

    //decidir quais labels vai ficar

    private JTable tabela = new JTable();

    public EmpresaGUI() throws HeadlessException{
        this.service = new EmpresaService();

        setTitle("Gerenciamento");
        setSize(800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //após decidir adicionar aqui com painelAdd()
    }

    private DefaultTableModel getTabelaModel(){
        var tabelaModel = new DefaultTableModel();
        return null;
    }
}
