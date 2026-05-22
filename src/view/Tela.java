package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class Tela extends JFrame {
    
    public JTabbedPane tabbedPane;

    // Itens de Menu Superior
    public JMenuItem menuAlunoSalvar, menuAlunoAlterar, menuAlunoConsultar, menuAlunoExcluir, menuAlunoSair;
    public JMenuItem menuNotasSalvar, menuNotasAlterar, menuNotasExcluir, menuNotasConsultar;
    public JMenuItem menuAjudaSobre;

    // Aba 1: Componentes de Dados Pessoais
    public JTextField txtRgm, txtNome, txtEmail, txtEndereco, txtMunicipio;
    public JFormattedTextField txtDataNascimento, txtCpf, txtCelular;
    public JComboBox<String> cbUf;
    public JButton btnTab1Salvar, btnTab1Alterar, btnTab1Consultar, btnTab1Excluir, btnTab1Sair;

    // Aba 2: Componentes de Curso
    public JComboBox<String> cbCurso, cbCampus;
    public JRadioButton rbMatutino, rbVespertino, rbNoturno;
    public ButtonGroup bgPeriodo;
    public JButton btnTab2Salvar, btnTab2Alterar, btnTab2Consultar, btnTab2Excluir, btnTab2Sair;

    // Aba 3: Componentes de Notas e Faltas
    public JTextField txtNotasRgm;
    public JLabel lblNotasNomeAluno, lblNotasCursoAluno;
    public JComboBox<String> cbNotasDisciplina, cbNotasSemestre, cbNotasNota;
    public JTextField txtNotasFaltas;
    public JButton btnTab3Salvar, btnTab3Alterar, btnTab3Consultar, btnTab3Excluir, btnTab3Sair;

    // Aba 4: Componentes Criativos do Boletim
    public JLabel lblBoletimRgm, lblBoletimNome, lblBoletimCurso;
    public JTable tableBoletim;
    public DefaultTableModel tableModelBoletim;
    public JButton btnBoletimGerar;

    public Tela() {
        setTitle("Sistema Acadêmico");
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarMenus();
        criarComponentes();
    }

    private void criarMenus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuAluno = new JMenu("Aluno");
        menuAlunoSalvar = new JMenuItem("Salvar");
        menuAlunoAlterar = new JMenuItem("Alterar");
        menuAlunoConsultar = new JMenuItem("Consultar");
        menuAlunoExcluir = new JMenuItem("Excluir");
        menuAlunoSair = new JMenuItem("Sair");
        menuAluno.add(menuAlunoSalvar);
        menuAluno.add(menuAlunoAlterar);
        menuAluno.add(menuAlunoConsultar);
        menuAluno.add(menuAlunoExcluir);
        menuAluno.addSeparator();
        menuAluno.add(menuAlunoSair);

        JMenu menuNotas = new JMenu("Notas e Faltas");
        menuNotasSalvar = new JMenuItem("Salvar");
        menuNotasAlterar = new JMenuItem("Alterar");
        menuNotasExcluir = new JMenuItem("Excluir");
        menuNotasConsultar = new JMenuItem("Consultar");
        menuNotas.add(menuNotasSalvar);
        menuNotas.add(menuNotasAlterar);
        menuNotas.add(menuNotasExcluir);
        menuNotas.add(menuNotasConsultar);

        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjudaSobre = new JMenuItem("Sobre");
        menuAjuda.add(menuAjudaSobre);

        menuBar.add(menuAluno);
        menuBar.add(menuNotas);
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar);
    }

    private void criarComponentes() {
        tabbedPane = new JTabbedPane();

        JPanel panelDadosPessoais = criarPanelDadosPessoais();
        JPanel panelCurso = criarPanelCurso();
        JPanel panelNotasFaltas = criarPanelNotasFaltas();
        JPanel panelBoletim = criarPanelBoletim();

        tabbedPane.addTab("Dados Pessoais", panelDadosPessoais);
        tabbedPane.addTab("Curso", panelCurso);
        tabbedPane.addTab("Notas e Faltas", panelNotasFaltas);
        tabbedPane.addTab("Boletim", panelBoletim);

        add(tabbedPane);
    }

    private JPanel criarPanelDadosPessoais() {
        JPanel panel = new JPanel(null);

        JLabel lblRgm = new JLabel("RGM"); lblRgm.setBounds(20, 20, 60, 25); panel.add(lblRgm);
        txtRgm = new JTextField(); txtRgm.setBounds(20, 45, 120, 25); panel.add(txtRgm);

        JLabel lblNome = new JLabel("Nome"); lblNome.setBounds(160, 20, 300, 25); panel.add(lblNome);
        txtNome = new JTextField(); txtNome.setBounds(160, 45, 540, 25); panel.add(txtNome);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento"); lblDataNascimento.setBounds(20, 80, 150, 25); panel.add(lblDataNascimento);
        try {
            txtDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) { txtDataNascimento = new JFormattedTextField(); }
        txtDataNascimento.setBounds(20, 105, 120, 25); panel.add(txtDataNascimento);

        JLabel lblCpf = new JLabel("CPF"); lblCpf.setBounds(160, 80, 150, 25); panel.add(lblCpf);
        try {
            txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException e) { txtCpf = new JFormattedTextField(); }
        txtCpf.setBounds(160, 105, 140, 25); panel.add(txtCpf);

        JLabel lblEmail = new JLabel("Email"); lblEmail.setBounds(20, 140, 100, 25); panel.add(lblEmail);
        txtEmail = new JTextField(); txtEmail.setBounds(20, 165, 680, 25); panel.add(txtEmail);

        JLabel lblEndereco = new JLabel("Endereço"); lblEndereco.setBounds(20, 200, 100, 25); panel.add(lblEndereco);
        txtEndereco = new JTextField(); txtEndereco.setBounds(20, 225, 680, 25); panel.add(txtEndereco);

        JLabel lblMunicipio = new JLabel("Município"); lblMunicipio.setBounds(20, 260, 100, 25); panel.add(lblMunicipio);
        txtMunicipio = new JTextField(); txtMunicipio.setBounds(20, 285, 300, 25); panel.add(txtMunicipio);

        JLabel lblUf = new JLabel("UF"); lblUf.setBounds(340, 260, 50, 25); panel.add(lblUf);
        String[] ufs = {"SP", "RJ", "MG", "ES", "PR", "SC", "RS", "BA", "PE", "CE", "DF"};
        cbUf = new JComboBox<>(ufs); cbUf.setBounds(340, 285, 70, 25); panel.add(cbUf);

        JLabel lblCelular = new JLabel("Celular"); lblCelular.setBounds(430, 260, 100, 25); panel.add(lblCelular);
        try {
            txtCelular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
        } catch (ParseException e) { txtCelular = new JFormattedTextField(); }
        txtCelular.setBounds(430, 285, 150, 25); panel.add(txtCelular);

        JPanel panelButtons = new JPanel(new GridLayout(1, 5, 10, 0));
        panelButtons.setBounds(20, 360, 680, 45);
        btnTab1Salvar = new JButton("Salvar"); panelButtons.add(btnTab1Salvar);
        btnTab1Alterar = new JButton("Alterar"); panelButtons.add(btnTab1Alterar);
        btnTab1Consultar = new JButton("Consultar"); panelButtons.add(btnTab1Consultar);
        btnTab1Excluir = new JButton("Excluir"); panelButtons.add(btnTab1Excluir);
        btnTab1Sair = new JButton("Sair"); panelButtons.add(btnTab1Sair);
        panel.add(panelButtons);

        return panel;
    }

    private JPanel criarPanelCurso() {
        JPanel panel = new JPanel(null);

        JLabel lblCurso = new JLabel("Curso"); lblCurso.setBounds(20, 20, 100, 25); panel.add(lblCurso);
        String[] cursos = {"Análise e Desenvolvimento de Sistemas", "Ciência da Computação", "Engenharia de Software", "Sistemas de Informação"};
        cbCurso = new JComboBox<>(cursos); cbCurso.setBounds(20, 45, 500, 25); panel.add(cbCurso);

        JLabel lblCampus = new JLabel("Campus"); lblCampus.setBounds(20, 90, 100, 25); panel.add(lblCampus);
        String[] campi = {"Tatuapé", "Anália Franco", "Pinheiros", "São Miguel"};
        cbCampus = new JComboBox<>(campi); cbCampus.setBounds(20, 115, 500, 25); panel.add(cbCampus);

        JLabel lblPeriodo = new JLabel("Período"); lblPeriodo.setBounds(20, 160, 100, 25); panel.add(lblPeriodo);
        rbMatutino = new JRadioButton("Matutino"); rbMatutino.setBounds(20, 185, 100, 25);
        rbVespertino = new JRadioButton("Vespertino"); rbVespertino.setBounds(130, 185, 100, 25);
        rbNoturno = new JRadioButton("Noturno"); rbNoturno.setSelected(true); rbNoturno.setBounds(240, 185, 100, 25);
        
        bgPeriodo = new ButtonGroup();
        bgPeriodo.add(rbMatutino);
        bgPeriodo.add(rbVespertino);
        bgPeriodo.add(rbNoturno);
        
        panel.add(rbMatutino);
        panel.add(rbVespertino);
        panel.add(rbNoturno);

        JPanel panelButtons = new JPanel(new GridLayout(1, 5, 10, 0));
        panelButtons.setBounds(20, 360, 680, 45);
        btnTab2Salvar = new JButton("Salvar"); panelButtons.add(btnTab2Salvar);
        btnTab2Alterar = new JButton("Alterar"); panelButtons.add(btnTab2Alterar);
        btnTab2Consultar = new JButton("Consultar"); panelButtons.add(btnTab2Consultar);
        btnTab2Excluir = new JButton("Excluir"); panelButtons.add(btnTab2Excluir);
        btnTab2Sair = new JButton("Sair"); panelButtons.add(btnTab2Sair);
        panel.add(panelButtons);

        return panel;
    }

    private JPanel criarPanelNotasFaltas() {
        JPanel panel = new JPanel(null);

        JLabel lblRgm = new JLabel("RGM"); lblRgm.setBounds(20, 20, 60, 25); panel.add(lblRgm);
        txtNotasRgm = new JTextField(); txtNotasRgm.setBounds(20, 45, 120, 25); panel.add(txtNotasRgm);

        lblNotasNomeAluno = new JLabel(" Informe o RGM para sincronizar o nome do aluno automaticamente");
        lblNotasNomeAluno.setBounds(160, 45, 540, 25);
        lblNotasNomeAluno.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblNotasNomeAluno.setOpaque(true);
        lblNotasNomeAluno.setBackground(new Color(255, 255, 204));
        panel.add(lblNotasNomeAluno);

        lblNotasCursoAluno = new JLabel(" Curso correspondente");
        lblNotasCursoAluno.setBounds(20, 90, 680, 25);
        lblNotasCursoAluno.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblNotasCursoAluno.setOpaque(true);
        lblNotasCursoAluno.setBackground(new Color(255, 255, 204));
        panel.add(lblNotasCursoAluno);

        JLabel lblDisciplina = new JLabel("Disciplina"); lblDisciplina.setBounds(20, 135, 100, 25); panel.add(lblDisciplina);
        String[] disc = {"Programação Orientada a Objetos", "Estrutura de Dados", "Banco de Dados", "Cálculo Diferencial"};
        cbNotasDisciplina = new JComboBox<>(disc); cbNotasDisciplina.setBounds(20, 160, 400, 25); panel.add(cbNotasDisciplina);

        JLabel lblSemestre = new JLabel("Semestre"); lblSemestre.setBounds(440, 135, 100, 25); panel.add(lblSemestre);
        String[] sem = {"2020-1", "2020-2", "2021-1", "2021-2", "2026-1"};
        cbNotasSemestre = new JComboBox<>(sem); cbNotasSemestre.setBounds(440, 160, 120, 25); panel.add(cbNotasSemestre);

        JLabel lblNota = new JLabel("Nota"); lblNota.setBounds(20, 205, 60, 25); panel.add(lblNota);
        String[] notasPossiveis = {"0.0", "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0"};
        cbNotasNota = new JComboBox<>(notasPossiveis); cbNotasNota.setBounds(20, 230, 80, 25); panel.add(cbNotasNota);

        JLabel lblFaltas = new JLabel("Faltas"); lblFaltas.setBounds(130, 205, 80, 25); panel.add(lblFaltas);
        txtNotasFaltas = new JTextField("0"); txtNotasFaltas.setBounds(130, 230, 80, 25); panel.add(txtNotasFaltas);

        JPanel panelButtons = new JPanel(new GridLayout(1, 5, 10, 0));
        panelButtons.setBounds(20, 360, 680, 45);
        btnTab3Salvar = new JButton("Salvar"); panelButtons.add(btnTab3Salvar);
        btnTab3Alterar = new JButton("Alterar"); panelButtons.add(btnTab3Alterar);
        btnTab3Consultar = new JButton("Consultar"); panelButtons.add(btnTab3Consultar);
        btnTab3Excluir = new JButton("Excluir"); panelButtons.add(btnTab3Excluir);
        btnTab3Sair = new JButton("Sair"); panelButtons.add(btnTab3Sair);
        panel.add(panelButtons);

        return panel;
    }

    private JPanel criarPanelBoletim() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelTop = new JPanel(new GridLayout(3, 1, 5, 5));
        lblBoletimRgm = new JLabel("RGM: - "); lblBoletimRgm.setFont(new Font("Arial", Font.BOLD, 13));
        lblBoletimNome = new JLabel("Nome: - "); lblBoletimNome.setFont(new Font("Arial", Font.BOLD, 13));
        lblBoletimCurso = new JLabel("Curso: - "); lblBoletimCurso.setFont(new Font("Arial", Font.BOLD, 13));
        panelTop.add(lblBoletimRgm);
        panelTop.add(lblBoletimNome);
        panelTop.add(lblBoletimCurso);
        panel.add(panelTop, BorderLayout.NORTH);

        String[] colunas = {"Disciplina", "Semestre", "Nota", "Faltas"};
        tableModelBoletim = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableBoletim = new JTable(tableModelBoletim);
        JScrollPane scrollPane = new JScrollPane(tableBoletim);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnBoletimGerar = new JButton("Atualizar e Exibir Boletim Completo");
        panel.add(btnBoletimGerar, BorderLayout.SOUTH);

        return panel;
    }
}