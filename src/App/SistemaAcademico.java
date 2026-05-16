package App;

import view.Interface;
import javax.swing.SwingUtilities;

public class SistemaAcademico {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface tela = new Interface();
            tela.setVisible(true);
        });
    }
}