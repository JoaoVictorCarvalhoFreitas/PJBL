import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CardapioProdutos {
    private JPanel panelPrincipal;
    private JPanel panelProdutos;
    private JButton botaoVoltar;
    private JPanel topPanelLeft;
    private JPanel topPanelRight;
    private ArrayList<Produto> produtos;
    private JButton botaoCarrinho;
    private JPanel topPanel;

    public CardapioProdutos(Consumer<Produto> onComprarProduto) {
        carregaPainelPrincipal(onComprarProduto);
    }

    private void carregaPainelPrincipal(Consumer<Produto> onComprarProduto) {
        panelPrincipal = new JPanel(new BorderLayout());


        botaoVoltar = new JButton("Voltar");
        // Configuração do painel do botão "Voltar"
        topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alinha o botão à esquerda
        topPanelLeft.add(botaoVoltar);
        topPanelLeft.setPreferredSize(new Dimension(100, 60));

        botaoCarrinho = new JButton("Carrinho");
        topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanelRight.add(botaoCarrinho);
        topPanelRight.setPreferredSize(new Dimension(100, 60));

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(topPanelLeft, BorderLayout.WEST);
        topPanel.add(topPanelRight, BorderLayout.EAST);


        panelPrincipal.add(topPanel, BorderLayout.NORTH);

        panelProdutos = new JPanel();
        panelProdutos.setLayout(new GridLayout(0, 2, 10, 10)); // Grid com duas colunas e espaçamento entre os cards
        panelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        recuperaProdutos();
        carregarProdutos(produtos, onComprarProduto);

        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
    }

    public void atualizarProdutos() {
        produtos = Dados.obterProdutos();

        panelProdutos.removeAll();

        carregarProdutos(produtos, produto -> {
            JOptionPane.showMessageDialog(panelPrincipal, "Produto " + produto.getNome() + " adicionado ao carrinho.");
        });

        panelProdutos.revalidate();
        panelProdutos.repaint();

    }

    private void recuperaProdutos() {
        produtos = Dados.obterProdutos();
    }

    private void carregarProdutos(ArrayList<Produto> produtos, Consumer<Produto> onComprarProduto) {
        for (Produto produto : produtos) {
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setPreferredSize(new Dimension(250, 150));

            JLabel nomeLabel = new JLabel(produto.getNome());
            JLabel descricaoLabel = new JLabel("<html><body style='width: 200px'>" + produto.getDescricao() + "</body></html>");
            JLabel precoLabel = new JLabel(String.format("Preço: R$ %.2f", produto.getPreco()));

            JButton comprarButton = new JButton("Comprar");
            comprarButton.addActionListener(e ->{
                onComprarProduto.accept(produto);
                atualizarProdutos();
                    }
            );

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(nomeLabel);
            infoPanel.add(descricaoLabel);
            infoPanel.add(precoLabel);

            card.add(infoPanel, BorderLayout.CENTER);
            card.add(comprarButton, BorderLayout.SOUTH);

            panelProdutos.add(card);
        }
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }

    public JButton getBotaoCarrinho(){return botaoCarrinho;}
}
