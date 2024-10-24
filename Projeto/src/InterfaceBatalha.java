import javax.swing.*;
import java.awt.*;

public class InterfaceBatalha {
    private JPanel BatalhaInterface;
    private JLabel PokeAdv;
    private JProgressBar vida_bar_poke_aliado;
    private JButton ataque1;
    private JButton ataque4;
    private JButton ataque2;
    private JButton ataques;
    private JButton ataque1Button;
    private JButton ataque2Button;
    private JButton ataque3Button;
    private JButton ataque4Button;
    private JProgressBar vida_bar_poke_rival;
    private JPanel AtaquesMensagens;
    private JPanel acoes;
    private JPanel hpAliado;
    private JLabel pokemonAliado;
    private JPanel hpPokemonRival;
    private JPanel ImagemPokemonAliado;
    private JProgressBar progressBar3;
    private JLabel label1;

    public InterfaceBatalha() {
        // Inicializando o frame principal
        JFrame frame = new JFrame("Batalha Pokémon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);

        // Configurando o layout principal
        BatalhaInterface = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Definindo espaçamento entre componentes
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento de 10px em todas as direções (cima, esquerda, baixo, direita)

        // Adicionando os componentes ao layout

        // Label "PokeAdv"
        PokeAdv = new JLabel("PokeAdv");
        gbc.gridx = 0;
        gbc.gridy = 0;
        BatalhaInterface.add(PokeAdv, gbc);

        // Barra de progresso 1
        vida_bar_poke_aliado = new JProgressBar();
        gbc.gridx = 1;
        gbc.gridy = 1;
        BatalhaInterface.add(vida_bar_poke_aliado, gbc);

        // Espaço vazio (vspacer) para ajustar o layout
        gbc.gridy = 2;
        BatalhaInterface.add(Box.createVerticalStrut(450), gbc);
        BatalhaInterface.add(Box.createHorizontalStrut(550), gbc);



        // Barra de progresso 3
        progressBar3 = new JProgressBar();
        gbc.gridx = 3;
        gbc.gridy = 5;
        BatalhaInterface.add(progressBar3, gbc);

        // Label
        label1 = new JLabel("Label");
        gbc.gridx = 1;
        gbc.gridy = 4;
        BatalhaInterface.add(label1, gbc);

        // Grid layout para os botões
        JPanel buttonGrid = new JPanel(new GridLayout(2, 2, 10, 10)); // Adicionando espaçamento de 10px entre os botões

        // Botão 1
        ataque1 = new JButton("Button 1");
        buttonGrid.add(ataque1);

        // Botão 2
        ataque4 = new JButton("Button 2");
        buttonGrid.add(ataque4);

        // Botão 3
        ataque2 = new JButton("Button 3");
        buttonGrid.add(ataque2);

        // Botão 5
        ataques = new JButton("Button 5");
        buttonGrid.add(ataques);

        gbc.gridx = 1;
        gbc.gridy = 6;
        BatalhaInterface.add(buttonGrid, gbc);

        // Configurando o frame
        frame.add(BatalhaInterface);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Inicializando a interface da batalha
        SwingUtilities.invokeLater(InterfaceBatalha::new);
    }

}
