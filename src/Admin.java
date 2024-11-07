import java.io.*;
import java.util.ArrayList;

public class Admin extends Usuario {
    private static final String arquivoProdutos = "produtos.ser";

    public Admin(int idUsuario,String nome,String email,String senha ){
        super(idUsuario,nome,email,senha);
    }

    //Produto

    public void atualizarProduto(String nome, String descricao, double preco) {
        ArrayList<Produto> produtos = Dados.obterProdutos();
        boolean produtoEncontrado = false;

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(nome)) {

                produtos.set(i, new Produto(produtos.size(), nome, descricao, preco));
                produtoEncontrado = true;
                break;
            }
        }

        if (produtoEncontrado) {
            salvarProdutos(produtos); // Sobrescreve o arquivo com a lista atualizada
            System.out.println("Produto atualizado com sucesso: " + nome);
        } else {
            System.out.println("Produto não encontrado: " + nome);
        }
    }

    public void atualizarProduto(String nome, Produto prodAtualizado) {
        ArrayList<Produto> produtos = Dados.obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(nome)) {
                produtos.set(i, prodAtualizado);
                break;
            }
        }
        salvarProdutos(produtos);
    }

    public void atualizaPrecoProduto(String nome, double preco) {
        ArrayList<Produto> produtos = Dados.obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(nome)) {
                produtos.get(i).setPreco(preco);
                break;
            }
        }
        salvarProdutos(produtos);
    }

    private void salvarProdutos(ArrayList<Produto> produtos) {
        try (FileOutputStream fileOut = new FileOutputStream("produtos.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            for (Produto prod : produtos) {
                out.writeObject(prod);
            }
            System.out.println("Lista de produtos salva com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public  void deletarProduto(int id) {
        ArrayList<Produto> produtos = Dados.obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.remove(i);
            }
        }
        salvarProdutos(produtos);
    }

    public void salvarProduto(Produto produto) {
        ArrayList<Produto> produtos = Dados.obterProdutos();

        boolean produtoJaExiste = false;
        for (Produto prod : produtos) {
            if (prod.getNome().equals(produto.getNome())) {
                produtoJaExiste = true;
                break;
            }
        }

        if (!produtoJaExiste) {
            produtos.add(produto);
            salvarProdutos(produtos);

        }
    }

    public void salvarProduto(String nome, String descricao, double preco) {
        int id = Dados.obterProdutos().size()+1;
        salvarProduto(new Produto(id, nome, descricao, preco));
    }

    public Usuario logar(String email, String senha) throws UsuarioInvalido{
        Usuario u = Dados.obterUsuarioPorEmail(email);
            if(!(u instanceof Admin)) {
                throw new UsuarioInvalido("Adm invalido");
            }
        return u;
    }

}
