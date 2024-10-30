import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Produto> carrinho = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        carrinho.add(produto);
    }

    public void deletaItemCarrinho(Produto produto) {
        carrinho.remove(produto);
    }



}
