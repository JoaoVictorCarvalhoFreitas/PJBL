public class Cliente extends Usuario{
    private Carrinho carrinho = new Carrinho();

    public Cliente(int idUsuario, String nome, String email, String senha) {
        super(idUsuario, nome, email, senha);
    }

    public Carrinho getCarrinho(){
        return carrinho;
    }

}