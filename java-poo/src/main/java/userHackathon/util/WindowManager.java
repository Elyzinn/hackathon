package userHackathon.util;

import javax.swing.*;
<<<<<<< HEAD

public class WindowManager {
    public static <T extends JFrame> void abrirJanela(Class<T> classeJanela){
            try {
                T gui = classeJanela.getDeclaredConstructor().newInstance();
                gui.setVisible(true);
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
    }
=======
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

>>>>>>> 35322039fe1fe84890552c566c313f0ebba4bed5
}
