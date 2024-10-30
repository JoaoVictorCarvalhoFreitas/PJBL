public class Admin extends Usuario {

    public Admin(int idUsuario,String nome,String email,String senha ){
        super(idUsuario,nome,email,senha);
    }

    //Produto
    public void cadastrarProduto(String nome,String descricao,double preco){
        Dados.salvarProduto(nome,descricao,preco);
    }


    public void atualizaPrecoProduto(String nome, double preco){
        Produto precoAntigo = Dados.obterProdutoPorNome(nome);
        precoAntigo.setPreco(preco);
        Dados.atualizarProduto(nome, precoAntigo);
    }


    //Cliente
    public static void cadastrarCliente(String nome,String email,String senha){
        Dados.salvarCliente(nome,email,senha);
    }

    public void atualizaCliente(int idCliente, String senha){
        Cliente usuarioAntigo = Dados.obterclientePorId(idCliente);
        Cliente novoUsuario = new Cliente(idCliente,usuarioAntigo.getNome(),usuarioAntigo.getEmail(),senha);
        Dados.atualizarcliente(idCliente,novoUsuario);
    }

    public void atualizaCliente(int idCliente, String email, String senha){
        Cliente usuarioAntigo = Dados.obterclientePorId(idCliente);
        Cliente novoUsuario = new Cliente(idCliente,usuarioAntigo.getNome(),email,senha);
        Dados.atualizarcliente(idCliente,novoUsuario);
    }

    public void atualizaCliente(int idCliente, String nome, String email, String senha){
        Cliente usuarioAntigo = Dados.obterclientePorId(idCliente);
        Cliente novoUsuario = new Cliente(idCliente,nome,email,senha);
        Dados.atualizarcliente(idCliente,novoUsuario);

    }

}
