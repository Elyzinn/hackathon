package userHackathon.gui;

import userHackathon.util.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PrincipalGUI extends JFrame implements PainelDefault {
    private JMenuBar menuBar = new JMenuBar();

    public PrincipalGUI() throws HeadlessException {
        setTitle("Back Office - Unialfa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setSize(800, 600); // Aumentado o tamanho inicial padrão por segurança

        menuBar.add(menuEmpresa());
        menuBar.add(menuAluno());
        menuBar.add(menuRelatorio()); // ADICIONADO: Menu de relatórios integrado ao topo
        setJMenuBar(menuBar);
    }

    private JMenu menuEmpresa() {
        var menu = new JMenu("Empresas");
        menu.setFont(new Font("Arial", Font.PLAIN, 16));

        var miGerenciarEmpresa = new JMenuItem("Gerenciar Empresa");
        miGerenciarEmpresa.setFont(new Font("Arial", Font.PLAIN, 14));
        miGerenciarEmpresa.addActionListener(this::abrirGerenciar);

        menu.add(miGerenciarEmpresa);
        return menu;
    }

    private JMenu menuAluno() {
        var menu = new JMenu("Alunos");
        menu.setFont(new Font("Arial", Font.PLAIN, 16));

        var miAluno = new JMenuItem("Gerenciar Alunos");
        miAluno.setFont(new Font("Arial", Font.PLAIN, 14));
        miAluno.addActionListener(this::abrirAluno);

        menu.add(miAluno);
        return menu;
    }

    private JMenu menuRelatorio() {
        var menu = new JMenu("Relatórios");
        menu.setFont(new Font("Arial", Font.PLAIN, 16));

        var miRelatorio = new JMenuItem("Gerenciar Central de Relatórios");
        miRelatorio.setFont(new Font("Arial", Font.PLAIN, 14));
        miRelatorio.addActionListener(this::abrirRelatorio);

        menu.add(miRelatorio);
        return menu;
    }

    private void abrirGerenciar(ActionEvent actionEvent) {
        WindowManager.abrirJanela(GerenciarEmpresaGUI.class);
    }

    private void abrirAluno(ActionEvent actionEvent) {
        WindowManager.abrirJanela(AlunoGUI.class);
    }

    private void abrirRelatorio(ActionEvent actionEvent) {
        WindowManager.abrirJanela(RelatorioGUI.class);
    }
}