package App;

import controller.MainController;
import view.Tela;
import javax.swing.*;

public class SistemaAcademico {
    public static void main(String[] args) {
        try {
            // Sincroniza a interface Swing com as janelas padrão do SO (Windows, Linux, Mac)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            Tela view = new Tela();
            new MainController(view);
            view.setVisible(true);
        });
    }
}