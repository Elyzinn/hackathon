package userHackathon.gui;


import userHackathon.util.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class PrincipalGUI extends JFrame implements PainelDefault{
    private JMenuBar menuBar = new JMenuBar();

    public PrincipalGUI() throws HeadlessException{
        setTitle("Back Office - UniAlfa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(200,200);

        menuBar.add(menuGerenciarEmpresa());
        setJMenuBar(menuBar);

    }

    private JMenu menuGerenciarEmpresa(){
        var menu = new JMenu("Empresas");
        menu.setFont(new Font("Arial", Font.PLAIN, 16));

        var miGerenciar = new JMenuItem("Gerenciar");
        miGerenciar.addActionListener(this::abrirEmpresa);

        var miConsulta = new JMenuItem("Consultar");
        miConsulta.addActionListener(this::abrirEmpresa); //verificar se vai precisar criar outro JPanel para isso

        menu.add(miGerenciar);
        menu.add(miConsulta);
        return menu;
    }

    private void abrirEmpresa(ActionEvent actionEvent){
        WindowManager.abrirJanela(EmpresaGUI.class);
    }
}
