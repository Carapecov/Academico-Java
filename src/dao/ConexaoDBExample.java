package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDBExample {
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/sistema_academico";
            String user = "root";
            String password = "SUA_SENHA_AQUI"; 
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            return null;
        }
    }
}