import javax.swing.*;
import java.awt.*;

public class InterfaceBatalha {

    public InterfaceBatalha() {
        // Inicializando o frame principal
        JFrame frame = new JFrame("Batalha Pokémon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Inicializando a interface da batalha
        SwingUtilities.invokeLater(InterfaceBatalha::new);
    }

}
