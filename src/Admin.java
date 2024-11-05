import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Admin extends Usuario {
    private Dados bd;

    public Admin(int idUsuario,String nome,String email,String senha ){
        super(idUsuario,nome,email,senha);
    }

    //Produto
    public static void cadastrarProduto(String nome,String descricao,double preco){
        Dados.salvarProduto(nome,descricao,preco);
    }

    public void atualizaPrecoProduto(String nome, double preco){
        Produto precoAntigo = Dados.obterProdutoPorNome(nome);
        precoAntigo.setPreco(preco);
        Dados.atualizarProduto(nome, precoAntigo);
    }

    public void cadastraAdm(String nome,String email,String senha){

    }
    //Cliente
    public static void cadastrarCliente(String nome,String email,String senha){
        Dados.cadastraCliente(nome,email,senha);
    }

    public void atualizaCliente(int idCliente, String senha){
        Cliente usuarioAntigo = Dados.obterClientePorId(idCliente);
        if (usuarioAntigo == null){
            System.out.println("Cliente nao encontrado");
            return;
        }
        Cliente novoUsuario = new Cliente(idCliente,usuarioAntigo.getNome(),usuarioAntigo.getEmail(),senha);
        Dados.atualizaUsuario(idCliente,novoUsuario);
    }

    public void atualizaCliente(int idCliente, String email, String senha){
        Cliente usuarioAntigo = Dados.obterClientePorId(idCliente);
        if (usuarioAntigo == null){
            return;
        }
        Cliente novoUsuario = new Cliente(idCliente,usuarioAntigo.getNome(),email,senha);
        Dados.atualizaUsuario(idCliente,novoUsuario);
    }

    public void atualizaCliente(int idCliente, String nome, String email, String senha){
        Cliente usuarioAntigo = Dados.obterClientePorId(idCliente);
        if (usuarioAntigo == null){
            return;
        }
        Cliente novoUsuario = new Cliente(idCliente,nome,email,senha);
        Dados.atualizaUsuario(idCliente,novoUsuario);

    }



}
