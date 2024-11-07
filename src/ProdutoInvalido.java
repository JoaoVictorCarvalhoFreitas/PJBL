public class ProdutoInvalido extends Exception {
    public ProdutoInvalido() {
        super("Produto invalido");
    }
    public ProdutoInvalido(String msg){
        super(msg);
    }
    public ProdutoInvalido(String msg, Throwable e){
        super(msg, e);
    }
    public ProdutoInvalido(Throwable e){
        super(e);
    }
}
