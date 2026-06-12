package userHackathon.gui;

<<<<<<< HEAD

=======
>>>>>>> 35322039fe1fe84890552c566c313f0ebba4bed5
import userHackathon.util.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
<<<<<<< HEAD

=======
import java.awt.event.ActionListener;
>>>>>>> 35322039fe1fe84890552c566c313f0ebba4bed5

public class PrincipalGUI extends JFrame implements PainelDefault{
    private JMenuBar menuBar = new JMenuBar();

<<<<<<< HEAD
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
=======
    //HeadlessException é lançada quando determinada função/método precisa de teclado, mouse essas coisas
    public PrincipalGUI() throws HeadlessException{
        setTitle("Back Office - Unialfa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(200, 200);

        menuBar.add(menuEmpresa());
        setJMenuBar(menuBar);
    }

    private JMenu menuEmpresa(){
        var menu = new JMenu("Empresa");
        menu.setFont(new Font("Arial", Font.PLAIN, 16));

        var miGerenciarEmpresa = new JMenuItem("Gerenciar Empresa");
        miGerenciarEmpresa.setFont(new Font("Arial", Font.PLAIN, 14));
        miGerenciarEmpresa.addActionListener(this::abrirGerenciar);

        menu.add(miGerenciarEmpresa);
        return menu;
    }

    private void abrirGerenciar(ActionEvent actionEvent){
        WindowManager.abrirJanela(GerenciarEmpresaGUI.class);
    }




>>>>>>> 35322039fe1fe84890552c566c313f0ebba4bed5
}
