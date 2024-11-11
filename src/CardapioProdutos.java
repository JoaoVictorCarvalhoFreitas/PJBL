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
    private JTextField campoSaldo = new JTextField();

    public CardapioProdutos() {
    }

    public void carregaPainelPrincipal(Consumer<Produto> onComprarProduto) {
        panelPrincipal = new JPanel(new BorderLayout());

        botaoVoltar = new JButton("Voltar");
        topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanelLeft.add(botaoVoltar);
        topPanelLeft.setPreferredSize(new Dimension(100, 60));


        campoSaldo.setEditable(false);
        campoSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        campoSaldo.setColumns(10);
        topPanelLeft.add(campoSaldo);

        botaoCarrinho = new JButton("Carrinho");
        topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanelRight.add(botaoCarrinho);
        topPanelRight.setPreferredSize(new Dimension(100, 60));

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(topPanelLeft, BorderLayout.WEST);
        topPanel.add(topPanelRight, BorderLayout.EAST);
        panelPrincipal.add(topPanel, BorderLayout.NORTH);

        panelProdutos = new JPanel();
        panelProdutos.setLayout(new GridLayout(0, 2, 10, 10));
        panelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        recuperaProdutos();
        carregarProdutos(produtos, onComprarProduto);

        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

    }

    private void recuperaProdutos() {
        produtos = Dados.obterProdutos();
    }

    public JTextField getCampoSaldo() {
        return campoSaldo;
    }
    private void atualizarCardProduto(Produto produto, JPanel card) {
        JLabel precoLabel = new JLabel(String.format("Preço: R$ %.2f", produto.getPreco()));
        card.add(precoLabel, BorderLayout.CENTER);
        card.revalidate();
        card.repaint();
    }

    private void carregarProdutos(ArrayList<Produto> produtos, Consumer<Produto> onComprarProduto) {

        panelProdutos.removeAll();

        for (Produto produto : produtos) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setPreferredSize(new Dimension(250, 150));

            JLabel nomeLabel = new JLabel(produto.getNome());
            JLabel descricaoLabel = new JLabel("<html><body style='width: 200px'>" + produto.getDescricao() + "</body></html>");
            JLabel precoLabel = new JLabel(String.format("Preço: R$ %.2f", produto.getPreco()));

            JButton comprarButton = new JButton("Comprar");
            comprarButton.addActionListener(e -> {
                onComprarProduto.accept(produto);
                atualizarCardProduto(produto, card);
            });

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(nomeLabel);
            infoPanel.add(descricaoLabel);
            infoPanel.add(precoLabel);

            card.add(infoPanel, BorderLayout.CENTER);
            card.add(comprarButton, BorderLayout.SOUTH);
            panelProdutos.add(card);
        }

        panelProdutos.revalidate();
        panelProdutos.repaint();
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }

    public JButton getBotaoCarrinho() {
        return botaoCarrinho;
    }


}
