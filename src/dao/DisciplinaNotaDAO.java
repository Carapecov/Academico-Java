package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DisciplinaNota;

public class DisciplinaNotaDAO {
    
    // Método para a aba "Notas e Faltas" (Salvar)
    public void salvar(DisciplinaNota dn) throws SQLException {
        String sql = "INSERT INTO disciplina_nota (rgm, disciplina, semestre, nota, faltas) VALUES (?,?,?,?,?)";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, dn.getRgm());
            stmt.setString(2, dn.getDisciplina());
            stmt.setString(3, dn.getSemestre());
            stmt.setDouble(4, dn.getNota());
            stmt.setInt(5, dn.getFaltas());
            
            stmt.executeUpdate();
        }
    }

    // Método para a aba "Boletim" (Lista todo o histórico do aluno)
    public List<DisciplinaNota> listarBoletim(String rgm) throws SQLException {
        List<DisciplinaNota> lista = new ArrayList<>();
        String sql = "SELECT * FROM disciplina_nota WHERE rgm = ?";
        
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, rgm);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DisciplinaNota dn = new DisciplinaNota();
                dn.setIdRegistro(rs.getInt("id_registro"));
                dn.setRgm(rs.getString("rgm"));
                dn.setDisciplina(rs.getString("disciplina"));
                dn.setSemestre(rs.getString("semestre"));
                dn.setNota(rs.getDouble("nota"));
                dn.setFaltas(rs.getInt("faltas"));
                
                lista.add(dn);
            }
        }
        return lista; // Devolve a lista cheia para a interface gráfica montar a tabela (JTable)
    }
}