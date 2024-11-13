import javax.swing.*;
import java.awt.*;

public class Cardapio {
    public static void criarInterface() {
        // Cores customizadas
        Color MarromClaro = new Color(135,100,74);
        Color Creme = new Color(209,178,146);

        // Janela principal
        JFrame frame = new JFrame("Gestão de Produtoss");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(MarromClaro);

        // Painel para os cards dos Produtoss
        JPanel panelProdutoss = new JPanel();
        panelProdutoss.setLayout(new GridLayout(0, 2, 10, 10)); // Layout de grade com 2 colunas e espaço entre cards
        panelProdutoss.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelProdutoss.setOpaque(false);

        // Cria um card para cada Produtos
        for (Produto Produtos : Dados.obterProdutos()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setBackground(MarromClaro);
            card.setPreferredSize(new Dimension(250, 150));

            // Informações do Produtos
            JLabel nomeLabel = new JLabel("Nome: " + Produtos.getNome());
            JLabel descricaoLabel = new JLabel("<html><body style='width: 200px'>Descrição: " + Produtos.getDescricao() + "</body></html>");
            JLabel precoLabel = new JLabel("Preço: R$ " + Produtos.getPreco());

            // Botão de compra
            JButton comprarButton = new JButton("Comprar");
            comprarButton.setBorder(null);
            comprarButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Produtos comprado: " + Produtos.getNome()));

            // Painel para os detalhes do Produtos
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(nomeLabel);
            infoPanel.add(descricaoLabel);
            infoPanel.add(precoLabel);

            // Adiciona os componentes ao card
            card.add(infoPanel, BorderLayout.CENTER);
            card.add(comprarButton, BorderLayout.SOUTH);

            // Adiciona o card ao painel principal
            panelProdutoss.add(card);
        }

        // Adiciona o painel de Produtoss à janela com rolagem
        JScrollPane scrollPane = new JScrollPane(panelProdutoss);
        frame.add(scrollPane);
        frame.setVisible(!frame.isVisible());
    }

    public void MostrarTelaCardapio(){
        SwingUtilities.invokeLater(Cardapio::criarInterface);
    }
}

