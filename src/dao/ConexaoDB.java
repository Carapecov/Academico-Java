package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    public static Connection conectar() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_academico", "root", "");
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
            return null;
        }
    }
}