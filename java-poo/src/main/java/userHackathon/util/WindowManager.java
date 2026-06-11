package userHackathon.util;

import javax.swing.*;
import java.lang.Class;

public class WindowManager {
    public static <T extends JFrame> void abrirJanela(Class<T> classeJanela){
        try{
            T gui = classeJanela.getDeclaredConstructor().newInstance();

            gui.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }

}
