import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {
    private Carrinho carrinho;

    public Cliente(int idUsuario, String nome, String email, String senha) {
        super(idUsuario, nome, email, senha);
        carrinho = new Carrinho();
    }

    public Carrinho getCarrinho(){
        return carrinho;
    }
    public void adicionaProdutoCarrinho(Produto prod){
        carrinho.adicionarProduto(prod);
    }

}