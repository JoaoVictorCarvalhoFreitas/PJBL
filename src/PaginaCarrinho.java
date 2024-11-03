import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

import static java.lang.Double.parseDouble;

public class PaginaCarrinho {
    private JPanel PainelCarrinho;
    private JButton Voltar;
    private JButton Comprar;
    private JLabel LabelCarrrinho;
    private JPanel painelProdutosOut;
    private JSpinner spinner1;


    public PaginaCarrinho() {

    }

    public JButton getBotaoVoltar(){
        return Voltar;
    }
    public JButton getBotaoComprar(){
        return Comprar;
    }
    public JPanel getPainelCarrinho(Cliente c){
        return constroiTabelaProdutos(c);
    }
    public void atualizaCarrinho(Carrinho carrinho){
        ArrayList<Produto> produtos = carrinho.getCarrinho();
        for (Produto produto : produtos){

        }

    }

    public JPanel constroiTabelaProdutos(Cliente c) {
        JPanel painelProdutosOut = new JPanel();
        painelProdutosOut.setLayout(new BoxLayout(painelProdutosOut, BoxLayout.Y_AXIS));

        Carrinho car = c.getCarrinho();
        ArrayList<Produto> produtos = car.getCarrinho();

        for (Produto produto : produtos) {
            JPanel painelProdutosIntern = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Painel de uma linha para um produto

            JLabel nomeProdutoLabel = new JLabel(produto.getNome());
            JSpinner quantidadeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            JLabel precoLabel = new JLabel(String.valueOf(produto.getPreco()));

            quantidadeSpinner.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    double quantidade = (int) quantidadeSpinner.getValue();
                    precoLabel.setText(String.format("%.2f", quantidade * produto.getPreco()));
                    painelProdutosOut.repaint();
                }
            });

            painelProdutosIntern.add(nomeProdutoLabel);
            painelProdutosIntern.add(quantidadeSpinner);
            painelProdutosIntern.add(precoLabel);

            painelProdutosOut.add(painelProdutosIntern);
        }

        return painelProdutosOut;
    }
}
