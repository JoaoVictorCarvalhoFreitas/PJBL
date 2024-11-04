import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Carrinho implements Serializable {
    private ArrayList<Produto> carrinho = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        carrinho.add(produto);
    }

    public void deletaItemCarrinho(Produto produto) {
        carrinho.remove(produto);
    }

    public void imprimeCarrinh(){
        for (Produto produto : carrinho) {
            System.out.println("Produto: "+ produto.getNome());
        }
    }

    public ArrayList<Produto> getCarrinho() {
        return carrinho;
    }

    public ArrayList<String> getListaNomes() {
        ArrayList<String> lista = new ArrayList<>();
        for (Produto produto : carrinho) {
            lista.add(produto.getNome());
        }
        return lista;
    }





}

