package userHackathon;

import userHackathon.gui.UserGui;

import javax.swing.*;
import java.security.Principal;

public class Main {
    static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(Main::executar);
    }

    private static void executar(){
        var gui = new UserGui();
        gui.setVisible(true);
    }
}
