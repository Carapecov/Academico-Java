package model;

public class DisciplinaNota {
    private int id;
    private String rgmAluno;
    private String disciplina;
    private String semestre;
    private double nota;
    private int faltas;

    public DisciplinaNota() {}

    public DisciplinaNota(int id, String rgmAluno, String disciplina, String semestre, double nota, int faltas) {
        this.id = id;
        this.rgmAluno = rgmAluno;
        this.disciplina = disciplina;
        this.semestre = semestre;
        this.nota = nota;
        this.faltas = faltas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRgmAluno() { return rgmAluno; }
    public void setRgmAluno(String rgmAluno) { this.rgmAluno = rgmAluno; }

    public String getDisciplina() { return disciplina; }
    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public int getFaltas() { return faltas; }
    public void setFaltas(int faltas) { this.faltas = faltas; }
}