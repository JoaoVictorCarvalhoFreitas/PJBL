import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Dados {
    static String  arquivoClientes = "clientes.ser" ;
    static String arquivoAdmin = "Adms.ser";
    static String arquivoProdutos = "produtos.ser";


    // Exemplo de método de login que retorna o usuário
    public static Usuario logar(String email, String senha) {
        Cliente cliente = obterClientePorEmail(email);
        if (cliente == null) {
            return null;
        }
        if (LogarUsuario(email,senha)) { // Ajuste para sua lógica de senha
            return cliente;
        }
        return null;
    }


    //ADMS
    public static void salvarAdm(String nome,String email,String senha){
        Admin adm = new Admin(obterAdms().size()+1,nome,email,senha);
        try (FileOutputStream fileOut = new FileOutputStream(arquivoAdmin, true);
             ObjectOutputStream out = new ObjectOutputStream(fileOut) {
                 protected void writeStreamHeader() throws IOException {
                     if (fileOut.getChannel().position() == 0) {
                         super.writeStreamHeader();
                     } else {
                         reset();
                     }
                 }
             }) {
            out.writeObject(adm);
            System.out.println("adm salvo com sucesso: " + adm.getNome());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o adm: " + e.getMessage());
        }
    }

    private static ArrayList<Admin> obterAdms() {
        ArrayList<Admin> adms = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoAdmin);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Admin adm = (Admin) in.readObject();
                    adms.add(adm);
                } catch (EOFException e) {
                    break;
                }
            }
        }  catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar adms: " + e.getMessage());
        }
        return adms;
    }

    public static boolean logarAdm(String email, String senha){
        Admin adm = obterAdminPorEmail(email);
        if(adm == null){
            return false;
        }
        if (adm.getSenha().equals(senha)) return true;
        return false;

    }

    public static Admin logaAdm(String email,String senha){
        Admin adm = obterAdminPorEmail(email);

        if(adm == null){
            return null;
        }
        if (adm.getSenha().equals(senha)) return adm;
        return null;
    }

    private static Admin obterAdminPorEmail(String email){
        ArrayList<Admin> adms = obterAdms();
        for (Admin adm : adms) {
            if (adm.getEmail().equals(email)) {
                return adm;
            }
        }
        return null;
    }

    // Clientes
    public static boolean LogarUsuario(String email, String senha){
        Cliente c = obterClientePorEmail(email);
        if(c == null){
            return false;
        }
        if (c.getSenha().equals(senha)) return true;
        return false;
    }

    private static Cliente obterClientePorEmail(String email){
        ArrayList<Cliente> clientes = obterclientes();
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }

    public static void salvarCliente(Cliente cliente) {
        boolean arquivoExiste = new File(arquivoClientes).exists();

        try (FileOutputStream fileOut = new FileOutputStream(arquivoClientes, true);
             ObjectOutputStream out = arquivoExiste ? new ObjectOutputStream(fileOut) {
                 protected void writeStreamHeader() throws IOException {
                     reset(); // Evita regravar o cabeçalho
                 }
             }
                     : new ObjectOutputStream(fileOut)) { // Escreve o cabeçalho se o arquivo é novo
            out.writeObject(cliente);
            System.out.println("Cliente salvo com sucesso: " + cliente.getNome());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cliente: " + e.getMessage());
        }
    }

    public static void salvarCliente(String nome,String email,String senha) {
        Cliente cliente = new Cliente(obterclientes().size()+1,nome,email,senha);
        salvarCliente(cliente);
    }

    private static void salvarclientes(ArrayList<Cliente> clientes) {
        try (FileOutputStream fileOut = new FileOutputStream(arquivoClientes);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(clientes);
            System.out.println("clientes atualizados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public static ArrayList<Cliente> obterclientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoClientes);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true){

                try{
                    Cliente cliente = (Cliente) in.readObject();
                    clientes.add(cliente);
                }catch (EOFException e) {
                    break;
                }
            }
        }  catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public static Cliente obterclientePorId(int Id) {
        ArrayList<Cliente> clientes = obterclientes();
        for (Cliente cliente : clientes) {
            if (cliente.getId()==Id) {
                return cliente;
            }
        }
        return null;
    }

    public static void atualizarcliente(int id, Cliente clienteAtualizado) {
        ArrayList<Cliente> clientes = obterclientes();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, clienteAtualizado);
                break;
            }
        }
        salvarclientes(clientes);
    }

    public static void deletarcliente(int Id) {
        ArrayList<Cliente> clientes = obterclientes();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId()==Id) {
                clientes.remove(i);
                System.out.println("Cliente: " +clientes.get(i).getNome() + "removido com sucesso.");
            }
        }
        salvarclientes(clientes);
    }

    //PRODUTOS

    public static void salvarProduto(Produto produto) {
        ArrayList<Produto> produtos = obterProdutos();

        boolean produtoJaExiste = false;
        for (Produto prod : produtos) {
            if (prod.getNome().equals(produto.getNome())) {
                produtoJaExiste = true;
                break;
            }
        }

        if (!produtoJaExiste) {
            produtos.add(produto);
            try (FileOutputStream fileOut = new FileOutputStream(arquivoProdutos);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                for (Produto prod : produtos) {
                    out.writeObject(prod); // Grava todos os produtos no arquivo
                }
                System.out.println("Produto salvo com sucesso: " + produto.getNome());
            } catch (IOException e) {
                System.out.println("Erro ao salvar o Produto: " + e.getMessage());
            }
        } else {
            System.out.println("Produto já existe: " + produto.getNome());
        }
    }


    private static void salvarProdutos(ArrayList<Produto> produtos) {
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

    public static void salvarProduto(String nome,String descricao,double preco){
        int id = Dados.obterProdutos().size();
        salvarProduto(new Produto(id,nome,descricao,preco));
    }

    public static ArrayList<Produto> obterProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoProdutos);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Produto produto = (Produto) in.readObject();
                    produtos.add(produto);
                }catch (EOFException e) {
                    break;
                }
            }

        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
        return produtos;
    }


    public static Produto obterProdutoPorId(int id) {
        ArrayList<Produto> produtos = obterProdutos();
        for (Produto prod : produtos) {
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public static void atualizarProduto(String nome, Produto prodAtualizado) {
        ArrayList<Produto> produtos = obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(nome)) {
                produtos.set(i, prodAtualizado);
                break;
            }
        }
        salvarProdutos(produtos);
    }


    public static Produto obterProdutoPorNome(String nome) {
        ArrayList<Produto> produtos = obterProdutos();
        for (Produto prod : produtos) {
            if (prod.getNome().equals(nome)) {
                return prod;
            }
        }
        return null;
    }

    public static void atualizarProduto(String nome,String descricao,double preco) {
        ArrayList<Produto> produtos = obterProdutos();
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

    public static void atualizaPrecoProduto(String nome,double preco){
        ArrayList<Produto> produtos = obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(nome)) {
                produtos.get(i).setPreco(preco);
                break;
            }
        }
        salvarProdutos(produtos);
    }

    public static void deletarProduto(int id) {
        ArrayList<Produto> produtos = obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.remove(i);
            }
        }
        salvarProdutos(produtos);
    }


//    CARRINHO
    public static Carrinho getCarrinho(int id){
        Cliente c = Dados.obterclientePorId(id);
        return c.getCarrinho();
    }

    public static void DeletarItemCarrinho(int idCliente,int idProduto){
        Cliente c = Dados.obterclientePorId(idCliente);
        c.getCarrinho().deletaItemCarrinho(Dados.obterProdutoPorId(idProduto));
        atualizarcliente(idCliente,c);

    }

}
