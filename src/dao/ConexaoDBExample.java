package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDBExample {
    //Renomeiem este arquivo para ConexaoDB.java e coloquem suas senhas
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