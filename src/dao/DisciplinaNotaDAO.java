package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DisciplinaNota;

public class DisciplinaNotaDAO {
    
    public void salvar(DisciplinaNota nf) throws SQLException {
        String sql = "INSERT INTO nota_falta (rgm_aluno, disciplina, semestre, nota, faltas) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nf.getRgmAluno());
            stmt.setString(2, nf.getDisciplina());
            stmt.setString(3, nf.getSemestre());
            stmt.setDouble(4, nf.getNota());
            stmt.setInt(5, nf.getFaltas());
            stmt.executeUpdate();
        }
    }

    public void alterar(DisciplinaNota nf) throws SQLException {
        String sql = "UPDATE nota_falta SET nota=?, faltas=? WHERE rgm_aluno=? AND disciplina=? AND semestre=?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nf.getNota());
            stmt.setInt(2, nf.getFaltas());
            stmt.setString(3, nf.getRgmAluno());
            stmt.setString(4, nf.getDisciplina());
            stmt.setString(5, nf.getSemestre());
            stmt.executeUpdate();
        }
    }

    public void excluir(String rgm, String disciplina, String semestre) throws SQLException {
        String sql = "DELETE FROM nota_falta WHERE rgm_aluno=? AND disciplina=? AND semestre=?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rgm);
            stmt.setString(2, disciplina);
            stmt.setString(3, semestre);
            stmt.executeUpdate();
        }
    }

    public List<DisciplinaNota> listarPorAluno(String rgm) throws SQLException {
        List<DisciplinaNota> lista = new ArrayList<>();
        String sql = "SELECT * FROM nota_falta WHERE rgm_aluno = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rgm);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new DisciplinaNota(
                        rs.getInt("id"),
                        rs.getString("rgm_aluno"),
                        rs.getString("disciplina"),
                        rs.getString("semestre"),
                        rs.getDouble("nota"),
                        rs.getInt("faltas")
                    ));
                }
            }
        }
        return lista;
    }
    
    public DisciplinaNota buscarDisciplinaNota(String rgm, String disciplina, String semestre) throws SQLException {
        String sql = "SELECT * FROM nota_falta WHERE rgm_aluno = ? AND disciplina = ? AND semestre = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rgm);
            stmt.setString(2, disciplina);
            stmt.setString(3, semestre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DisciplinaNota(
                        rs.getInt("id"),
                        rs.getString("rgm_aluno"),
                        rs.getString("disciplina"),
                        rs.getString("semestre"),
                        rs.getDouble("nota"),
                        rs.getInt("faltas")
                    );
                }
            }
        }
        return null;
    }
}