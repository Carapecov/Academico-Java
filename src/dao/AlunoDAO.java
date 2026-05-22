package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Aluno;

public class AlunoDAO {
    
    public void salvar(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (rgm, nome, data_nascimento, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getRgm());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getEmail());
            stmt.setString(6, aluno.getEndereco());
            stmt.setString(7, aluno.getMunicipio());
            stmt.setString(8, aluno.getUf());
            stmt.setString(9, aluno.getCelular());
            stmt.setString(10, aluno.getCurso());
            stmt.setString(11, aluno.getCampus());
            stmt.setString(12, aluno.getPeriodo());
            stmt.executeUpdate();
        }
    }

    public void alterar(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome=?, data_nascimento=?, cpf=?, email=?, endereco=?, municipio=?, uf=?, celular=?, curso=?, campus=?, periodo=? WHERE rgm=?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getDataNascimento());
            stmt.setString(3, aluno.getCpf());
            stmt.setString(4, aluno.getEmail());
            stmt.setString(5, aluno.getEndereco());
            stmt.setString(6, aluno.getMunicipio());
            stmt.setString(7, aluno.getUf());
            stmt.setString(8, aluno.getCelular());
            stmt.setString(9, aluno.getCurso());
            stmt.setString(10, aluno.getCampus());
            stmt.setString(11, aluno.getPeriodo());
            stmt.setString(12, aluno.getRgm());
            stmt.executeUpdate();
        }
    }

    public void excluir(String rgm) throws SQLException {
        String sql = "DELETE FROM aluno WHERE rgm = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rgm);
            stmt.executeUpdate();
        }
    }

    public Aluno consultar(String rgm) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE rgm = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rgm);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                        rs.getString("rgm"),
                        rs.getString("nome"),
                        rs.getString("data_nascimento"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getString("municipio"),
                        rs.getString("uf"),
                        rs.getString("celular"),
                        rs.getString("curso"),
                        rs.getString("campus"),
                        rs.getString("periodo")
                    );
                }
            }
        }
        return null;
    }
}