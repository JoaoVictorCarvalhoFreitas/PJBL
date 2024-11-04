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
    private JPanel PainelTabelProdutos;


    public PaginaCarrinho() {

    }

    public JButton getBotaoVoltar(){
        return Voltar;
    }
    public JButton getBotaoComprar(){
        return Comprar;
    }
    public JPanel getPainelCarrinho(Cliente client){

        constroiTabelaProdutos(client);
        return PainelCarrinho;
    }
    public void atualizaCarrinho(Cliente c){
        ArrayList<Produto> produtos = c.getCarrinho().getCarrinho();
        for (Produto produto : produtos){

        }
    }

    public void constroiTabelaProdutos(Cliente cliente) {

        try {
            PainelTabelProdutos.removeAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        PainelTabelProdutos.setLayout(new BoxLayout(PainelTabelProdutos, BoxLayout.Y_AXIS));

        Carrinho car = cliente.getCarrinho();
        ArrayList<Produto> produtos = car.getCarrinho();

        for (Produto produto : produtos) {
            JPanel painelProdutosIntern = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel nomeProdutoLabel = new JLabel(produto.getNome());
            JSpinner quantidadeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            JLabel precoLabel = new JLabel(String.valueOf(produto.getPreco()));

            quantidadeSpinner.addChangeListener(e -> {
                double quantidade = (int) quantidadeSpinner.getValue();
                precoLabel.setText(String.format("%.2f", quantidade * produto.getPreco()));
                PainelTabelProdutos.repaint();
            });

            painelProdutosIntern.add(nomeProdutoLabel);
            painelProdutosIntern.add(quantidadeSpinner);
            painelProdutosIntern.add(precoLabel);

            PainelTabelProdutos.add(painelProdutosIntern);
        }

    }


}
