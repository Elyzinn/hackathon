package userHackathon.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
    private Connection connection;

    public Dao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hackathon?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root",
                    "root"
            );
        } catch (Exception e) {
            System.out.println("Erro no dao " + e.getMessage());;
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
