import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CardapioProdutos extends JPanel {
    private JPanel panelProdutos;
    private JButton botaoVoltar;

    public CardapioProdutos(Consumer<Produto> onComprarProduto) {
        // Configuração do layout do CardapioProdutos
        ArrayList<Produto> produtos = Dados.obterProdutos();

        setLayout(new BorderLayout());

        // Botão de Voltar (sem ação associada)
        botaoVoltar = new JButton("Voltar");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alinha o botão à esquerda
        topPanel.add(botaoVoltar);
        add(topPanel, BorderLayout.NORTH);

        // Painel para os cards dos produtos
        panelProdutos = new JPanel();
        panelProdutos.setLayout(new GridLayout(0, 2, 10, 10)); // Grid com duas colunas e espaçamento entre os cards
        panelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Carrega os produtos em cards
        carregarProdutos(produtos, onComprarProduto);

        // Adiciona o painel de produtos a um JScrollPane e o insere no JPanel principal
        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JButton getBotaoVoltar(){
        return botaoVoltar;
    }



    public JButton getBotaoComprar(){
        return botaoVoltar;
    }

    public JPanel getPanelProdutos() {
        return panelProdutos;
    }

    private void carregarProdutos(ArrayList<Produto> produtos,Consumer<Produto> onComprarProduto) {
        for (Produto produto : produtos) {
            // Criação de cada card de produto
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setBackground(Color.WHITE);
            card.setPreferredSize(new Dimension(250, 150));

            // Informações do produto
            JLabel nomeLabel = new JLabel(produto.getNome());
            JLabel descricaoLabel = new JLabel("<html><body style='width: 200px'>" + produto.getDescricao() + "</body></html>");
            JLabel precoLabel = new JLabel(String.format("Preço: R$ %.2f", produto.getPreco()));

            // Botão de compra
            JButton comprarButton = new JButton("Comprar");

            comprarButton.addActionListener(e -> onComprarProduto.accept(produto));

            // Painel de informações do produto
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(nomeLabel);
            infoPanel.add(descricaoLabel);
            infoPanel.add(precoLabel);

            // Adiciona componentes ao card
            card.add(infoPanel, BorderLayout.CENTER);
            card.add(comprarButton, BorderLayout.SOUTH);

            // Adiciona o card ao painel principal
            panelProdutos.add(card);
        }
        panelProdutos.revalidate();
        panelProdutos.repaint();
    }
}
