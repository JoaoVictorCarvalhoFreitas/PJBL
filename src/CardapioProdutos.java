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

        // Configuração do botão "Voltar" no painel superior
        botaoVoltar = new JButton("Voltar");
        topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanelLeft.add(botaoVoltar);
        topPanelLeft.setPreferredSize(new Dimension(100, 60));

        // Configuração do botão "Carrinho" no painel superior
        botaoCarrinho = new JButton("Carrinho");
        topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanelRight.add(botaoCarrinho);
        topPanelRight.setPreferredSize(new Dimension(100, 60));

        // Painel superior com "Voltar" e "Carrinho"
        topPanel = new JPanel(new BorderLayout());
        topPanel.add(topPanelLeft, BorderLayout.WEST);
        topPanel.add(topPanelRight, BorderLayout.EAST);
        panelPrincipal.add(topPanel, BorderLayout.NORTH);

        // Configuração do painel de produtos
        panelProdutos = new JPanel();
        panelProdutos.setLayout(new GridLayout(0, 2, 10, 10)); // Grid de duas colunas
        panelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        recuperaProdutos();
        carregarProdutos(produtos, onComprarProduto);

        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
    }

    private void recuperaProdutos() {
        produtos = Dados.obterProdutos(); // Assume-se que Dados.obterProdutos() retorne a lista de produtos atualizada
    }

    // Nova função para atualizar um card específico em vez de toda a lista
    private void atualizarCardProduto(Produto produto, JPanel card) {
        JLabel precoLabel = new JLabel(String.format("Preço: R$ %.2f", produto.getPreco()));
        card.add(precoLabel, BorderLayout.CENTER);
        card.revalidate();
        card.repaint();
    }

    // Função revisada para personalizar a mensagem de adição ao carrinho e evitar recriar todos os cards
    private void carregarProdutos(ArrayList<Produto> produtos, Consumer<Produto> onComprarProduto) {
        panelProdutos.removeAll(); // Limpa o painel de produtos

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
                JOptionPane.showMessageDialog(panelPrincipal, "Produto " + produto.getNome() + " adicionado ao carrinho com sucesso.");
                atualizarCardProduto(produto, card); // Atualiza apenas o card do produto comprado
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
