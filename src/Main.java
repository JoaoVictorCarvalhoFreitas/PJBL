import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       Interfaces interfaces = new Interfaces();
//        Login login = new Login();
//        Frame frame = new Frame();
//        frame.add(login.criarPainelLogin());
//        frame.setVisible(true);

        Admin Admin = new Admin(1,"AdmJoao","adm","adm");
//        Admin.salvarProduto("cafe preto","cafe brasileiro", 1.99);
//        Admin.salvarProduto("cafe branco","cafe branco", 0.99);
//        Admin.salvarProduto("cafe amarelo","cafe estranho", 10.99);
//        Admin.salvarProduto("cafe marrom","cafe com leite", 2.99);
//        Admin.salvarProduto("cafe preto1","cafe", 1.99);
//        Admin.salvarProduto("cafe preto11","cafe", 1.99);
//        Admin.salvarProduto("Javacafe","cafe depressivo", 999.9);
//        //
//        Dados.cadastraCliente("Joao","Joao@Joao","0");
//        Dados.cadastraCliente("Joao1","Joao@Joao1","1");
//        Dados.cadastraCliente("Joao2","Joao@Joao2","12");
//        Dados.cadastraCliente("Joao3","Joao@Joao3","123");
//        Dados.cadastraCliente("Joao4","Joao@Joao4","1234");
//        Dados.cadastraCliente("Joao5","Joao@Joao5","12345");
//        Dados.cadastraCliente("Joao6","Joao@Joao6","123456");

        Dados.cadastraNovoUsuarioCliente(Admin);


//        ArrayList<String> list = new ArrayList<>(10);
//
//        System.out.println(list.get(2));

//        Dados.cadastraNovoUsuarioCliente(Admin);
////        Dados.deletaUsuario("Joao@Joao");
//        Dados.mostraCarrinhoUsuarios();
//
//        PaginaCarrinho p = new PaginaCarrinho();
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.add(p.getPainelCarrinho((Cliente)Dados.obterUsuarioPorEmail("Joao@Joao")));
//        frame.setVisible(true);
//        Cliente c = (Cliente)Dados.obterUsuarioPorEmail("Joao@Joao");
//        c.getCarrinho().limpaCarrinho();
//        Dados.atualizaUsuario(c.getId(),c);


    }

}