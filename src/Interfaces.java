import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static java.lang.Double.isNaN;
import static java.lang.Double.parseDouble;

public class Interfaces {
    private final JPanel painelPrincipal;
    private Login paginaLogin = new Login();
    private CadastroPage paginaCadastro = new CadastroPage();

    private PaginaAdm paginaAdm = new PaginaAdm();
    private JPanel painelLogin;
    private JPanel painelCadastro;
    private JPanel painelCardapio;
    private JPanel painelAdm;
    private Cliente usuarioLogado;
    private Admin admin;
    CardapioProdutos PaginaCardapioProdutos ;




    public Interfaces(){
        painelPrincipal = new JPanel(new CardLayout());
        criaPainelProdutos();

        painelLogin = paginaLogin.criarPainelLogin();
        painelCadastro = paginaCadastro.criarPainelCadastro();
        painelCardapio = PaginaCardapioProdutos.getPanel();
        painelAdm = paginaAdm.getPainelAdm();

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelPrincipal.add(painelLogin,"Pagina de Login");
        painelPrincipal.add(painelCadastro,"Pagina de Cadastro");
        painelPrincipal.add(painelCardapio,"Pagina de Cardapio");
        painelPrincipal.add(painelAdm,"Pagina de Adm");

        frame.add(painelPrincipal);
        frame.setVisible(true);

        AtribuiBotoes();
    }


    public void TrocarParaPainel(String NomePainel){
        CardLayout card = (CardLayout)painelPrincipal.getLayout();
        card.show(painelPrincipal, NomePainel);
    }


    public void AtribuiBotoes(){

        paginaLogin.getBotaoEntrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = paginaLogin.getEmail().getText();
                String senha = new String(paginaLogin.getSenha().getPassword());

                if(Dados.obterUsuarioPorEmail(email) == null){
                    return;
                }

                try {
                    if( Dados.obterUsuarioPorEmail(email) instanceof Admin){
                        admin = (Admin)Dados.logar(email,senha);
                    } else if (Dados.obterUsuarioPorEmail(email) instanceof Cliente) {
                        usuarioLogado = (Cliente)Dados.logar(email,senha);
                        JOptionPane.showMessageDialog(painelLogin,"Usuario "+ usuarioLogado.getNome()+ " logado " );
                    }
                    PaginaCardapioProdutos = new CardapioProdutos(produto -> {
                        if (usuarioLogado != null) {
                            usuarioLogado.getCarrinho().adicionarProduto(produto);
                            JOptionPane.showMessageDialog(painelPrincipal, "Produto adicionado ao carrinho de : " + usuarioLogado.getNome());
                        } else {
                            JOptionPane.showMessageDialog(painelPrincipal, "Usuário não está logado.");
                        }});
                }catch (Exception ex){
                        JOptionPane.showMessageDialog(painelLogin,ex.getMessage());
                }

                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Usuário logado com sucesso");
                    TrocarParaPainel("Pagina de Cardapio");
                } else if(admin != null) {
                    JOptionPane.showMessageDialog(painelPrincipal, "Admin logado com sucesso");
                    TrocarParaPainel("Pagina de Adm");
                }else{
                    JOptionPane.showMessageDialog(painelPrincipal, "Email ou senha incorretos", "Erro de Login", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        paginaLogin.getBotaoCadastrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TrocarParaPainel("Pagina de Cadastro");
            }
        });

        paginaCadastro.getBotaoLogin().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TrocarParaPainel("Pagina de Login");
            }
        });

        paginaCadastro.getBotaoCadastrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = paginaCadastro.getNome().getText();
                String email = paginaCadastro.getEmail().getText();
                String senha1 = Arrays.toString(paginaCadastro.getSenha().getPassword());
                String senha2 = Arrays.toString(paginaCadastro.getSenha2().getPassword());
                if (!senha1.equals(senha2)){
                    JOptionPane.showMessageDialog(painelCadastro,"Senhas Diferentes");
                } else if (nome.isBlank()||email.isBlank()||senha2.isBlank()) {
                    JOptionPane.showMessageDialog(painelCadastro,"Email, nome ou senha invalidos");
                }else{
                    try {
                    Dados.cadastraCliente(nome,email,senha1);
                    JOptionPane.showMessageDialog(painelCadastro,"Usuario cadastrado com sucesso");
                    TrocarParaPainel("Pagina de Login");
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(painelCadastro,ex.getMessage());
                    }
                }

            }
        });

        PaginaCardapioProdutos.getBotaoVoltar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TrocarParaPainel("Pagina de Login");
                Dados.atualizaUsuario(usuarioLogado.getId(),usuarioLogado);
                usuarioLogado.getCarrinho().imprimeCarrinh();
            }
        });

        paginaAdm.getBotaoCadastraProduto().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                    String nome = paginaAdm.getNomeProdutoCadastro().getText();
                    String descricao = paginaAdm.getDescricaoProdutoCadastro().getText();
                    double preco =  parseDouble(paginaAdm.getPrecoProdutoCadastro().getText());

                    if(nome.isBlank()||descricao.isBlank()){
                        JOptionPane.showMessageDialog(painelAdm,"Todos os campos devem ser preenchidos");
                    }else if(preco<0 || isNaN(preco)){
                        JOptionPane.showMessageDialog(painelAdm,"preco invalido por favor digite um valor valido");
                    }else {
                        try {
                        admin.cadastrarProduto(nome,descricao,preco);
                        JOptionPane.showMessageDialog(painelAdm,"Produto cadastrado com sucesso");
                        PaginaCardapioProdutos.atualizarProdutos();
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(painelAdm,ex.getMessage());
                        }
                    }



            }
        });

        paginaAdm.getBotaoVoltar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TrocarParaPainel("Pagina de Login");
            }
        });

        paginaAdm.getBotaoAtualizaProduto().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = paginaAdm.getNomeProdutoAtualiza().getText();
                String descricao = paginaAdm.getDescricaoProdutoAtualiza().getText();
                double preco = 0;
                if(!paginaAdm.getPrecoProdutoAtualiza().getText().isBlank()){
                    try{
                        preco = parseDouble(paginaAdm.getPrecoProdutoAtualiza().getText());
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(painelAdm,"preco invalido digite um preco valido");
                        return;
                    }
                }

                if(nome.isBlank()){
                    return;
                }
                if(preco<=0 || isNaN(preco)){
                    JOptionPane.showMessageDialog(painelAdm,"preco invalido digite um preco valido");
                }
                if(descricao.isBlank()){
                    Dados.atualizaPrecoProduto(nome,preco);
                }else{
                Dados.atualizarProduto(nome,descricao,preco);
                JOptionPane.showMessageDialog(painelAdm, "Produto atualizado com sucesso");
                PaginaCardapioProdutos.atualizarProdutos();
                }
            }

        });

    }

    public void criaPainelProdutos(){
        PaginaCardapioProdutos = new CardapioProdutos(produto -> {
            if (usuarioLogado != null) {
                usuarioLogado.adicionaProdutoCarrinho(produto);
                Dados.atualizaUsuario(usuarioLogado.getId(),usuarioLogado);
                usuarioLogado = (Cliente)Dados.obterUsuarioPorEmail(usuarioLogado.getEmail());
                JOptionPane.showMessageDialog(painelPrincipal, "Produto "+ produto.getNome() + " adicionado ao carrinho de : " + usuarioLogado.getNome());
                JOptionPane.showMessageDialog(painelPrincipal,usuarioLogado.getCarrinho().getListaNomes());
            } else {
                JOptionPane.showMessageDialog(painelPrincipal, "Usuário não está logado.");
            }
        });

    }

}
