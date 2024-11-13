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

    /**
     * <b>Cores</b> diferentes.
     */
    private Color MarromCarvalho = new Color(135,100,74);
    private Color Creme = new Color(209,178,146);
    private Color LeiteMarronzinho = new Color(214, 188, 170);
    private Color Cafe = new Color(88, 64, 54);

    public CardapioProdutos() {
    }

    public void carregaPainelPrincipal(Consumer<Produto> onComprarProduto) {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(LeiteMarronzinho);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBorder(null);
        botaoVoltar.setFocusable(false);
        botaoVoltar.setBackground(MarromCarvalho);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setPreferredSize(new Dimension(100, 25));
        topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanelLeft.add(botaoVoltar);
        topPanelLeft.setPreferredSize(new Dimension(100, 60));
        topPanelLeft.setOpaque(false);


        botaoCarrinho = new JButton("Carrinho");
        botaoCarrinho.setBorder(null);
        botaoCarrinho.setFocusable(false);
        botaoCarrinho.setBackground(MarromCarvalho);
        botaoCarrinho.setForeground(Color.WHITE);
        botaoCarrinho.setPreferredSize(new Dimension(100, 25));
        topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanelRight.add(botaoCarrinho);
        topPanelRight.setPreferredSize(new Dimension(100, 60));
        topPanelRight.setOpaque(false);

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(topPanelLeft, BorderLayout.WEST);
        topPanel.add(topPanelRight, BorderLayout.EAST);
        topPanel.setOpaque(false);
        panelPrincipal.add(topPanel, BorderLayout.NORTH);

        panelProdutos = new JPanel();
        panelProdutos.setLayout(new GridLayout(0, 2, 10, 10));
        panelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        recuperaProdutos();
        carregarProdutos(produtos, onComprarProduto);
        panelProdutos.setBackground(LeiteMarronzinho);

        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        scrollPane.setBackground(LeiteMarronzinho);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

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
            card.setBorder(null);

            JLabel nomeLabel = new JLabel("<html><body><h1>"+produto.getNome()+"</h1></body></html>");
            JLabel descricaoLabel = new JLabel("<html><body style='width: 200px'> Descrição: " + produto.getDescricao() + "</body></html>");
            JLabel precoLabel = new JLabel(String.format("Preço: R$ %.2f", produto.getPreco()));
            nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nomeLabel.setForeground(Color.WHITE);
            descricaoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            descricaoLabel.setForeground(Color.WHITE);
            precoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
            precoLabel.setForeground(Color.WHITE);

            JButton comprarButton = new JButton("Comprar");
            comprarButton.setBackground(Cafe);
            comprarButton.setBorder(null);
            comprarButton.setForeground(Color.WHITE);
            comprarButton.setFocusable(false);

            comprarButton.addActionListener(e -> {
                onComprarProduto.accept(produto);
                atualizarCardProduto(produto, card);
            });

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(nomeLabel);
            infoPanel.add(descricaoLabel);
            infoPanel.add(precoLabel);
            infoPanel.setBackground(MarromCarvalho);

            card.add(infoPanel, BorderLayout.CENTER);
            card.add(comprarButton, BorderLayout.SOUTH);
            panelProdutos.add(card);
        }

        panelProdutos.revalidate();
        panelProdutos.repaint();
    }

    private void recuperaProdutos() {
        produtos = Dados.obterProdutos();
    }

    public JTextField getCampoSaldo() {
        return campoSaldo;
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
