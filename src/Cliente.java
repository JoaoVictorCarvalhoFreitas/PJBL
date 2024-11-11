import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {
    private Carrinho carrinho;
    private double saldo;

    public Cliente(int idUsuario, String nome, String email, String senha) {
        super(idUsuario, nome, email, senha);
        carrinho = new Carrinho();
        saldo = 1000;
    }

    public Carrinho getCarrinho(){
        return carrinho;
    }

    public void adicionaProdutoCarrinho(Produto prod){
        carrinho.adicionarProduto(prod);
    }

    public void limpaCarrinho(){
        this.carrinho = new Carrinho();
    }

    public void adicionaSaldo(double valor){
        saldo += valor;
    }
    public double getSaldo(){
        return saldo;
    }
    public void paga(double valor){
        saldo -= valor;
    }

}