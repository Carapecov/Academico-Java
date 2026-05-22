package controller;

import model.Aluno;
import dao.AlunoDAO;
import model.DisciplinaNota;
import dao.DisciplinaNotaDAO;
import view.Tela;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MainController {
    private Tela view;
    private AlunoDAO alunoDAO;
    private DisciplinaNotaDAO notaFaltaDAO;

    public MainController(Tela view) {
        this.view = view;
        this.alunoDAO = new AlunoDAO();
        this.notaFaltaDAO = new DisciplinaNotaDAO();
        
        vincularEventos();
    }

    private void vincularEventos() {
        // Eventos Menu Aluno
        view.menuAlunoSalvar.addActionListener(e -> acaoSalvarAluno());
        view.menuAlunoAlterar.addActionListener(e -> acaoAlterarAluno());
        view.menuAlunoConsultar.addActionListener(e -> acaoConsultarAluno());
        view.menuAlunoExcluir.addActionListener(e -> acaoExcluirAluno());
        view.menuAlunoSair.addActionListener(e -> acaoSair());

        // Eventos Menu Notas
        view.menuNotasSalvar.addActionListener(e -> acaoSalvarNotaFalta());
        view.menuNotasAlterar.addActionListener(e -> acaoAlterarNotaFalta());
        view.menuNotasConsultar.addActionListener(e -> acaoConsultarNotaFalta());
        view.menuNotasExcluir.addActionListener(e -> acaoExcluirNotaFalta());

        // Evento Sobre
        view.menuAjudaSobre.addActionListener(e -> JOptionPane.showMessageDialog(view, 
                "Sistema Acadêmico v1.0\n", 
                "Sobre", JOptionPane.INFORMATION_MESSAGE));

        // Eventos Botões Aba 1
        view.btnTab1Salvar.addActionListener(e -> acaoSalvarAluno());
        view.btnTab1Alterar.addActionListener(e -> acaoAlterarAluno());
        view.btnTab1Consultar.addActionListener(e -> acaoConsultarAluno());
        view.btnTab1Excluir.addActionListener(e -> acaoExcluirAluno());
        view.btnTab1Sair.addActionListener(e -> acaoSair());

        // Eventos Botões Aba 2
        view.btnTab2Salvar.addActionListener(e -> acaoSalvarAluno());
        view.btnTab2Alterar.addActionListener(e -> acaoAlterarAluno());
        view.btnTab2Consultar.addActionListener(e -> acaoConsultarAluno());
        view.btnTab2Excluir.addActionListener(e -> acaoExcluirAluno());
        view.btnTab2Sair.addActionListener(e -> acaoSair());

        // Eventos Botões Aba 3
        view.btnTab3Salvar.addActionListener(e -> acaoSalvarNotaFalta());
        view.btnTab3Alterar.addActionListener(e -> acaoAlterarNotaFalta());
        view.btnTab3Consultar.addActionListener(e -> acaoConsultarNotaFalta());
        view.btnTab3Excluir.addActionListener(e -> acaoExcluirNotaFalta());
        view.btnTab3Sair.addActionListener(e -> acaoSair());

        // Evento Atualizar Boletim
        view.btnBoletimGerar.addActionListener(e -> acaoAtualizarBoletim());
    }

    private void acaoSair() {
        int confirm = JOptionPane.showConfirmDialog(view, "Deseja encerrar a aplicação?", "Sair", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) System.exit(0);
    }

    private Aluno capturarAlunoDaView() {
        String rgm = view.txtRgm.getText().trim();
        String nome = view.txtNome.getText().trim();
        String dataNasc = view.txtDataNascimento.getText().trim();
        String cpf = view.txtCpf.getText().trim();
        String email = view.txtEmail.getText().trim();
        String endereco = view.txtEndereco.getText().trim();
        String municipio = view.txtMunicipio.getText().trim();
        String uf = (String) view.cbUf.getSelectedItem();
        String celular = view.txtCelular.getText().trim();
        String curso = (String) view.cbCurso.getSelectedItem();
        String campus = (String) view.cbCampus.getSelectedItem();
        
        String periodo = "Noturno";
        if (view.rbMatutino.isSelected()) periodo = "Matutino";
        else if (view.rbVespertino.isSelected()) periodo = "Vespertino";

        if (rgm.isEmpty() || nome.isEmpty()) return null;
        return new Aluno(rgm, nome, dataNasc, cpf, email, endereco, municipio, uf, celular, curso, campus, periodo);
    }

    private void preencherAlunoNaView(Aluno aluno) {
        if (aluno == null) return;
        view.txtRgm.setText(aluno.getRgm());
        view.txtNome.setText(aluno.getNome());
        view.txtDataNascimento.setText(aluno.getDataNascimento());
        view.txtCpf.setText(aluno.getCpf());
        view.txtEmail.setText(aluno.getEmail());
        view.txtEndereco.setText(aluno.getEndereco());
        view.txtMunicipio.setText(aluno.getMunicipio());
        view.cbUf.setSelectedItem(aluno.getUf());
        view.txtCelular.setText(aluno.getCelular());
        view.cbCurso.setSelectedItem(aluno.getCurso());
        view.cbCampus.setSelectedItem(aluno.getCampus());
        
        if ("Matutino".equals(aluno.getPeriodo())) view.rbMatutino.setSelected(true);
        else if ("Vespertino".equals(aluno.getPeriodo())) view.rbVespertino.setSelected(true);
        else view.rbNoturno.setSelected(true);

        view.txtNotasRgm.setText(aluno.getRgm());
        view.lblNotasNomeAluno.setText(" " + aluno.getNome());
        view.lblNotasCursoAluno.setText(" " + aluno.getCurso());
    }

    private void limparFormularioAluno() {
        view.txtRgm.setText("");
        view.txtNome.setText("");
        view.txtDataNascimento.setValue(null);
        view.txtCpf.setValue(null);
        view.txtEmail.setText("");
        view.txtEndereco.setText("");
        view.txtMunicipio.setText("");
        view.cbUf.setSelectedIndex(0);
        view.txtCelular.setValue(null);
        view.rbNoturno.setSelected(true);
    }

    private void acaoSalvarAluno() {
        Aluno aluno = capturarAlunoDaView();
        if (aluno == null) {
            JOptionPane.showMessageDialog(view, "RGM e Nome são campos mandatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            if (alunoDAO.consultar(aluno.getRgm()) != null) {
                JOptionPane.showMessageDialog(view, "Este RGM já está cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            alunoDAO.salvar(aluno);
            JOptionPane.showMessageDialog(view, "Aluno incluído com sucesso!");
            acaoAtualizarBoletim();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void acaoAlterarAluno() {
        Aluno aluno = capturarAlunoDaView();
        if (aluno == null) return;
        try {
            alunoDAO.alterar(aluno);
            JOptionPane.showMessageDialog(view, "Cadastro atualizado com sucesso!");
            acaoAtualizarBoletim();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro ao alterar: " + ex.getMessage());
        }
    }

    private void acaoConsultarAluno() {
        String rgm = JOptionPane.showInputDialog(view, "Insira o RGM de busca:");
        if (rgm == null || rgm.trim().isEmpty()) return;
        try {
            Aluno aluno = alunoDAO.consultar(rgm.trim());
            if (aluno != null) {
                preencherAlunoNaView(aluno);
                acaoAtualizarBoletim();
            } else {
                JOptionPane.showMessageDialog(view, "Aluno não localizado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro ao buscar: " + ex.getMessage());
        }
    }

    private void acaoExcluirAluno() {
        String rgm = view.txtRgm.getText().trim();
        String nome = view.txtNome.getText().trim();
        if (rgm.isEmpty()) {
            rgm = JOptionPane.showInputDialog(view, "RGM para exclusão:");
        }
        if (rgm == null || rgm.trim().isEmpty()) return;

        int confirm = JOptionPane.showConfirmDialog(view, "Excluir o aluno " + nome + " Contendo o RGM: " + rgm + "? (Irá deletar notas/faltas)", "Aviso", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                alunoDAO.excluir(rgm.trim());
                limparFormularioAluno();
                view.tableModelBoletim.setRowCount(0);
                JOptionPane.showMessageDialog(view, "Aluno removido.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Erro ao deletar: " + ex.getMessage());
            }
        }
    }

    private void acaoSalvarNotaFalta() {
        String rgm = view.txtNotasRgm.getText().trim();
        if (rgm.isEmpty()) return;
        try {
            Aluno al = alunoDAO.consultar(rgm);
            if (al == null) {
                JOptionPane.showMessageDialog(view, "Aluno não cadastrado.");
                return;
            }
            view.lblNotasNomeAluno.setText(" " + al.getNome());
            view.lblNotasCursoAluno.setText(" " + al.getCurso());

            String disciplina = (String) view.cbNotasDisciplina.getSelectedItem();
            String semestre = (String) view.cbNotasSemestre.getSelectedItem();
            double nota = Double.parseDouble((String) view.cbNotasNota.getSelectedItem());
            int faltas = Integer.parseInt(view.txtNotasFaltas.getText().trim());

            if (notaFaltaDAO.buscarDisciplinaNota(rgm, disciplina, semestre) != null) {
                JOptionPane.showMessageDialog(view, "Registro existente. Use Alterar.");
                return;
            }

            DisciplinaNota nf = new DisciplinaNota(0, rgm, disciplina, semestre, nota, faltas);
            notaFaltaDAO.salvar(nf);
            JOptionPane.showMessageDialog(view, "Nota inserida!");
            acaoAtualizarBoletim();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erro: " + ex.getMessage());
        }
    }

    private void acaoAlterarNotaFalta() {
        String rgm = view.txtNotasRgm.getText().trim();
        String disciplina = (String) view.cbNotasDisciplina.getSelectedItem();
        String semestre = (String) view.cbNotasSemestre.getSelectedItem();
        double nota = Double.parseDouble((String) view.cbNotasNota.getSelectedItem());
        int faltas = Integer.parseInt(view.txtNotasFaltas.getText().trim());

        try {
        	DisciplinaNota nf = new DisciplinaNota(0, rgm, disciplina, semestre, nota, faltas);
            notaFaltaDAO.alterar(nf);
            JOptionPane.showMessageDialog(view, "Nota alterada!");
            acaoAtualizarBoletim();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro: " + ex.getMessage());
        }
    }

    private void acaoConsultarNotaFalta() {
        String rgm = view.txtNotasRgm.getText().trim();
        String disciplina = (String) view.cbNotasDisciplina.getSelectedItem();
        String semestre = (String) view.cbNotasSemestre.getSelectedItem();

        try {
        	DisciplinaNota nf = notaFaltaDAO.buscarDisciplinaNota(rgm, disciplina, semestre);
            if (nf != null) {
                view.cbNotasNota.setSelectedItem(String.valueOf(nf.getNota()));
                view.txtNotasFaltas.setText(String.valueOf(nf.getFaltas()));
            } else {
                JOptionPane.showMessageDialog(view, "Nenhum registro correspondente encontrado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro: " + ex.getMessage());
        }
    }

    private void acaoExcluirNotaFalta() {
        String rgm = view.txtNotasRgm.getText().trim();
        String disciplina = (String) view.cbNotasDisciplina.getSelectedItem();
        String semestre = (String) view.cbNotasSemestre.getSelectedItem();

        try {
            notaFaltaDAO.excluir(rgm, disciplina, semestre);
            JOptionPane.showMessageDialog(view, "Nota excluída.");
            acaoAtualizarBoletim();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Erro: " + ex.getMessage());
        }
    }

    private void acaoAtualizarBoletim() {
        String rgm = view.txtRgm.getText().trim();
        if (rgm.isEmpty()) rgm = view.txtNotasRgm.getText().trim();
        if (rgm.isEmpty()) return;

        try {
            Aluno aluno = alunoDAO.consultar(rgm);
            if (aluno == null) return;

            view.lblBoletimRgm.setText("RGM: " + aluno.getRgm());
            view.lblBoletimNome.setText("Nome: " + aluno.getNome());
            view.lblBoletimCurso.setText("Curso: " + aluno.getCurso() + " | Campus: " + aluno.getCampus());

            view.tableModelBoletim.setRowCount(0);
            List<DisciplinaNota> notas = notaFaltaDAO.listarPorAluno(rgm);
            for (DisciplinaNota nf : notas) {
                view.tableModelBoletim.addRow(new Object[]{
                    nf.getDisciplina(), nf.getSemestre(), nf.getNota(), nf.getFaltas()
                });
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar dados para boletim.");
        }
    }
}