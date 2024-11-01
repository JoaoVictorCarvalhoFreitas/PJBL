import java.io.*;
import java.util.ArrayList;

public abstract class Dados {
    static String arquivoUsuario = "Usuarios.ser";
    static String arquivoProdutos = "produtos.ser";




    public static Usuario logar(String email, String senha) {
        Usuario usuario = obterUsuarioPorEmail(email);

        if (usuario == null) {
            return null;
        }
        if (usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }

    //ADMS
//    public static void salvarAdm(String nome, String email, String senha) {
//        Admin adm = new Admin(obterAdms().size() + 1, nome, email, senha);
//        try (FileOutputStream fileOut = new FileOutputStream(arquivoAdmin, true);
//             ObjectOutputStream out = new ObjectOutputStream(fileOut) {
//                 protected void writeStreamHeader() throws IOException {
//                     if (fileOut.getChannel().position() == 0) {
//                         super.writeStreamHeader();
//                     } else {
//                         reset();
//                     }
//                 }
//             }) {
//            out.writeObject(adm);
//            System.out.println("adm salvo com sucesso: " + adm.getNome());
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar o adm: " + e.getMessage());
//        }
//    }
//
//    private static ArrayList<Admin> obterAdms() {
//        ArrayList<Admin> adms = new ArrayList<>();
//        try (FileInputStream fileIn = new FileInputStream(arquivoAdmin);
//             ObjectInputStream in = new ObjectInputStream(fileIn)) {
//            while (true) {
//                try {
//                    Admin adm = (Admin) in.readObject();
//                    adms.add(adm);
//                } catch (EOFException e) {
//                    break;
//                }
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Erro ao carregar adms: " + e.getMessage());
//        }
//        return adms;
//    }
//
//    private static boolean logarAdm(String email, String senha) {
//        Admin adm = obterAdminPorEmail(email);
//        if (adm == null) {
//            return false;
//        }
//        if (adm.getSenha().equals(senha)) return true;
//        return false;
//
//    }
//
//    public static Admin logaAdm(String email, String senha) {
//        Admin adm = obterAdminPorEmail(email);
//
//        if (adm == null) {
//            return null;
//        }
//        if (adm.getSenha().equals(senha)) return adm;
//        return null;
//    }
//
//    private static Admin obterAdminPorEmail(String email) {
//        ArrayList<Admin> adms = obterAdms();
//        for (Admin adm : adms) {
//            if (adm.getEmail().equals(email)) {
//                return adm;
//            }
//        }
//        return null;
//    }

    // Clientes


    //PRODUTOS

    //ADM
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

    //ADM
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

    //ADM
    public static void salvarProduto(String nome, String descricao, double preco) {
        int id = Dados.obterProdutos().size();
        salvarProduto(new Produto(id, nome, descricao, preco));
    }

    public static ArrayList<Produto> obterProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoProdutos);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Produto produto = (Produto) in.readObject();
                    produtos.add(produto);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
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

    //ADM
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

    //ADM
    public static void atualizarProduto(String nome, String descricao, double preco) {
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

    //ADM
    public static void atualizaPrecoProduto(String nome, double preco) {
        ArrayList<Produto> produtos = obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(nome)) {
                produtos.get(i).setPreco(preco);
                break;
            }
        }
        salvarProdutos(produtos);
    }

    //ADM
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
    public static Carrinho getCarrinho(int id) {
        Cliente c = Dados.obterClientePorId(id);
        return c.getCarrinho();
    }

    public static void DeletarItemCarrinho(int idCliente, int idProduto) {
        Cliente c = Dados.obterClientePorId(idCliente);
        c.getCarrinho().deletaItemCarrinho(Dados.obterProdutoPorId(idProduto));
        atualizaUsuario(idCliente, c);

    }



    //Usuarios


//    public static ArrayList<Cliente> obterUsuariosCliente() {
//        ArrayList<Cliente> clientes = new ArrayList<>();
//
//        try (FileInputStream fileIn = new FileInputStream(arquivoUsuario);
//             ObjectInputStream in = new ObjectInputStream(fileIn)) {
//
//            while (true) {
//                try {
//                    if(in.readObject() instanceof Cliente u){
//                        clientes.add(u);
//                    }
//
//
//                } catch (EOFException e) {
//                    break;
//                }
//            }
//
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Erro ao carregar clientes: " + e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return clientes;
//
//    }
//
//    public static ArrayList<Admin> obterUsuariosAdmins() {
//        ArrayList<Admin> admins = new ArrayList<>();
//
//        try (FileInputStream fileIn = new FileInputStream(arquivoUsuario);
//             ObjectInputStream in = new ObjectInputStream(fileIn)) {
//
//            while (true) {
//                try {
//                    if(in.readObject() instanceof Admin u){
//                        admins.add(u);
//                    }
//
//
//                } catch (EOFException e) {
//                    break;
//                }
//            }
//
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Erro ao carregar clientes: " + e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return admins;
//
//    }

    public static ArrayList<Usuario> obterUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoUsuario);
        ObjectInputStream in = new ObjectInputStream(fileIn)) {

            while (true) {
                try {
                        usuarios.add((Usuario)in.readObject());
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usuarios;

    }

    public static Usuario obterUsuarioPorEmail(String email) {
        ArrayList<Usuario>Clientes = obterUsuarios();
        for (Usuario c : Clientes) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public static Cliente obterClientePorId(int id) {
         ArrayList<Usuario> usuarios = obterUsuarios();
         for (Usuario c : usuarios) {
             if(c instanceof Cliente && c.getId() == id){
                 return (Cliente)c;
             }
             break;
         }
         return null;
    }

    public static void cadastraNovoUsuarioCliente(Usuario usuario){

        ArrayList<Usuario> usuarios = obterUsuarios();
        int id = usuarios.size()+1;

        boolean clienteExiste = false;
        for (Usuario u: usuarios) {
            if (u.getNome().equals(usuario.getNome())) {
                clienteExiste = true;
                break;
            }
        }

        if (!clienteExiste) {
            usuarios.add(usuario);
            try (FileOutputStream fileOut = new FileOutputStream(arquivoUsuario);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                for (Usuario u1 : usuarios) {
                    out.writeObject(u1);
                }
                System.out.println("Cliente salvo com sucesso: " + usuario.getNome());
            } catch (IOException e) {
                System.out.println("Erro ao salvar o cliente: " + e.getMessage());
            }
        } else {
            System.out.println("cliente já existe: " + usuario.getNome());
        }


    }

    public static void cadastraCliente(String nome,String email,String senha){

        int id = obterUsuarios().size()+1;
        cadastraNovoUsuarioCliente(new Cliente(id,nome,email,senha));

    }

    public static void salvaUsuarioCliente(int id,Cliente cliente){
        ArrayList<Usuario> clientes = obterUsuarios();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, cliente);
                break;
            }

        }
        salvaListaUsuarios(clientes);
    }

    public static void salvaListaUsuarios(ArrayList<Usuario> clientes){
        ArrayList<Usuario> usuarios = obterUsuarios();

        try (FileOutputStream fileOut = new FileOutputStream(arquivoUsuario);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            for (Usuario c : clientes) {
                out.writeObject(c);

            }
            System.out.println("usuarios atualizados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuarios: " + e.getMessage());
        }
    }

    public static void atualizaUsuario(int id, Usuario u){
            ArrayList<Usuario> usuarios = obterUsuarios();
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getId() == id) {
                    usuarios.set(i, u);
                }
            }
            salvaListaUsuarios(usuarios);
    }

    public static void mostraUsuarios(){
        for(Usuario u : obterUsuarios()){
            System.out.println(u instanceof Cliente);
        }
    }

}

