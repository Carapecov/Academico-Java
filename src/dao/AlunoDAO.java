package dao;

import model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

    public boolean salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (rgm, nome, data_nascimento, cpf, email, curso) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getRgm());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getEmail());
            stmt.setString(6, aluno.getCurso());
            
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar aluno: " + e.getMessage());
            return false;
        }
    }

    public boolean rgmExiste(String rgm) {
        String sql = "SELECT rgm_aluno FROM aluno WHERE rgm_aluno = ?";
        
        // Usando try-with-resources para fechar a conexão automaticamente
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, rgm);
            ResultSet rs = stmt.executeQuery();
            
            // O pulo do gato está aqui: rs.next() só é true se ele achou alguém
            return rs.next(); 
            
        } catch (SQLException e) {
            System.err.println("Erro ao verificar RGM: " + e.getMessage());
            return false; // Em caso de erro de conexão, você pode decidir como tratar
        }
    }
            
}