package userHackathon.util;

import javax.swing.*;

public class WindowManager {
    public static <T extends JFrame> void abrirJanela(Class<T> classeJanela){
        //unica e exclusivamente criado para reciclar código
            try {
                T gui = classeJanela.getDeclaredConstructor().newInstance();
                gui.setVisible(true);
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
    }
}
