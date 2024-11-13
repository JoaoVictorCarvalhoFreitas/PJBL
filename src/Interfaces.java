import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.Double.isNaN;
import static java.lang.Double.parseDouble;

public class Interfaces {
    private final JPanel painelPrincipal;
    private final Login paginaLogin = new Login();
    private final CadastroPage paginaCadastro = new CadastroPage();
    private final PaginaAdm paginaAdm = new PaginaAdm();
    private Cliente usuarioLogado;
    private Admin admin;
    private final CardapioProdutos PaginaCardapioProdutos = new CardapioProdutos();
    private PaginaCarrinho PaginaCarrinho = new PaginaCarrinho();
    private final JPanel painelLogin;
    private final JPanel painelCadastro;
    private JPanel painelCardapio;
    private final JPanel painelAdm;
    private JPanel painelCarrinhoUsuario = new JPanel();

    public Interfaces() {

        painelPrincipal = new JPanel(new CardLayout());

        // Inicialização dos painéis
        painelLogin = paginaLogin.criarPainelLogin();
        painelCadastro = paginaCadastro.criarPainelCadastro();
        painelAdm = paginaAdm.getPainelAdm();

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona os painéis ao painel principal
        painelPrincipal.add(painelLogin, "Pagina de Login");
        painelPrincipal.add(painelCadastro, "Pagina de Cadastro");

        painelPrincipal.add(painelAdm, "Pagina de Adm");

        frame.add(painelPrincipal);
        frame.setVisible(true);

        AtribuiBotoes();
    }

    public void TrocarParaPainel(String NomePainel) {
        CardLayout card = (CardLayout) painelPrincipal.getLayout();
        card.show(painelPrincipal, NomePainel);
    }

    public void AtribuiBotoes() {

        paginaLogin.getBotaoEntrar().addActionListener(_ -> {
            String email = paginaLogin.getEmail().getText();
            String senha = new String(paginaLogin.getSenha().getPassword());


            try {
                   Usuario u =  Usuario.logarUsuario(email,senha);
                   if(u instanceof Cliente ){
                       usuarioLogado = (Cliente) u;
                   }else if(u instanceof Admin ){
                       admin = (Admin) u;
                   }

            } catch (UsuarioInvalido ex) {
                JOptionPane.showMessageDialog(painelLogin, ex);
            }

            if (usuarioLogado != null) {

                JOptionPane.showMessageDialog(painelPrincipal, "Usuário logado com sucesso");
                CarregaCarrinho();
                painelCardapio = criaPainelProdutos();
                painelCardapio.repaint();
                painelPrincipal.add(painelCardapio, "Pagina de Cardapio");
                painelPrincipal.add(painelCarrinhoUsuario, "Pagina de Carrinho");
                carregaBotoesCardapio();
                TrocarParaPainel("Pagina de Cardapio");
            } else if (admin != null) {
                JOptionPane.showMessageDialog(painelPrincipal, "Admin logado com sucesso");
                TrocarParaPainel("Pagina de Adm");
            }

        });

        paginaLogin.getBotaoCadastrar().addActionListener(_ -> TrocarParaPainel("Pagina de Cadastro"));

        paginaCadastro.getBotaoLogin().addActionListener(_ -> TrocarParaPainel("Pagina de Login"));

        paginaCadastro.getBotaoCadastrar().addActionListener(_ -> {
            String nome = paginaCadastro.getNome().getText();
            String email = paginaCadastro.getEmail().getText();
            String senha1 = new String(paginaCadastro.getSenha().getPassword());
            System.out.println(senha1);
            String senha2 = new String(paginaCadastro.getSenha2().getPassword());
            System.out.println(senha2);

            if (!senha1.equals(senha2)) {
                JOptionPane.showMessageDialog(painelCadastro, "Senhas Diferentes");
                return;
            } else if (nome.isBlank() || email.isBlank() || senha2.isBlank()) {
                JOptionPane.showMessageDialog(painelCadastro, "Email, nome e senhas devem ser todos preenchidos");
                return;
            } else if (Dados.usuarioExiste(email)){
                JOptionPane.showMessageDialog(painelCadastro,"Email ja cadastrado no sistema tente com outro email");
                return;
            }
            try {
                Dados.cadastraCliente(nome, email, senha1);
                JOptionPane.showMessageDialog(painelCadastro, "Usuário cadastrado com sucesso");
                TrocarParaPainel("Pagina de Login");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painelCadastro, ex.getMessage());
            }

        });

        paginaAdm.getBotaoCadastraProduto().addActionListener(_ -> {
            String nome = paginaAdm.getNomeProdutoCadastro().getText();
            String descricao = paginaAdm.getDescricaoProdutoCadastro().getText();
            double preco = parseDouble(paginaAdm.getPrecoProdutoCadastro().getText());

            if (nome.isBlank() || descricao.isBlank()) {
                JOptionPane.showMessageDialog(painelAdm, "Todos os campos devem ser preenchidos");
            } else if (preco < 0 || isNaN(preco)) {
                JOptionPane.showMessageDialog(painelAdm, "Preço inválido. Por favor, insira um valor válido");
            } else {
                try {
                    admin.salvarProduto(nome, descricao, preco);
                    JOptionPane.showMessageDialog(painelAdm, "Produto cadastrado com sucesso");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painelAdm, ex.getMessage());
                }
            }
        });

        paginaAdm.getBotaoAtualizaProduto().addActionListener(_ ->{
            String novoNome = paginaAdm.getNomeProdutoAtualiza().getText();
            String novaDescricao = paginaAdm.getDescricaoProdutoAtualiza().getText();
            double novoPreco = parseDouble(paginaAdm.getPrecoProdutoAtualiza().getText());
            if (novoNome.isBlank() || novaDescricao.isBlank() || novoPreco < 0 || isNaN(novoPreco)) {
                JOptionPane.showMessageDialog(painelAdm,"Os campos precisam estar preenchidos corretamente");
                return;
            }
            try {
                admin.atualizarProduto(novoNome, novaDescricao, novoPreco);
            }catch (Exception ex){
                JOptionPane.showMessageDialog(painelAdm, "Erro ao atualizar produto");
            }
        });

        paginaAdm.getBotaoVoltar().addActionListener(_ -> {
                    admin = null;
                    TrocarParaPainel("Pagina de Login");
                });


    }

    private JPanel criaPainelProdutos() {
        PaginaCardapioProdutos.getCampoSaldo().setText(usuarioLogado.getSaldo() + "");
        PaginaCardapioProdutos.carregaPainelPrincipal(produto -> {
            if (usuarioLogado == null) {
                JOptionPane.showMessageDialog(painelPrincipal, "Usuário não está logado.");
                return;
            }
            try {
                usuarioLogado.adicionaProdutoCarrinho(produto);
                usuarioLogado.getCarrinho().adicionaValor(produto.getPreco());
                Dados.atualizaUsuario(usuarioLogado.getId(), usuarioLogado);
                usuarioLogado = (Cliente)Cliente.logarUsuario(usuarioLogado.getEmail(), usuarioLogado.getSenha());
                CarregaCarrinho();

                JOptionPane.showMessageDialog(painelPrincipal, "Produto " + produto.getNome() + " adicionado ao carrinho de " + usuarioLogado.getNome());
            }catch (UsuarioInvalido ex){
                JOptionPane.showMessageDialog(painelPrincipal, ex.getMessage());
            }
        });
        return PaginaCardapioProdutos.getPanel();

    }

    private void CarregaCarrinho() {
        painelCarrinhoUsuario = PaginaCarrinho.getPainelCarrinho(usuarioLogado);
        carregaBotoesCarrinho();
        painelCarrinhoUsuario.revalidate();
        painelCarrinhoUsuario.repaint();
    }

    private void carregaBotoesCardapio(){
        for (ActionListener al : PaginaCardapioProdutos.getBotaoVoltar().getActionListeners()) {
            PaginaCardapioProdutos.getBotaoVoltar().removeActionListener(al);
        }
        for (ActionListener al : PaginaCardapioProdutos.getBotaoCarrinho().getActionListeners()) {
            PaginaCardapioProdutos.getBotaoCarrinho().removeActionListener(al);
        }

        PaginaCardapioProdutos.getBotaoVoltar().addActionListener(_ -> {
            TrocarParaPainel("Pagina de Login");
            if (usuarioLogado != null) usuarioLogado.getCarrinho().imprimeCarrinh();
            usuarioLogado = null;
        });

        PaginaCardapioProdutos.getBotaoCarrinho().addActionListener(_ -> TrocarParaPainel("Pagina de Carrinho"));

    }

    private void carregaBotoesCarrinho(){


        for (ActionListener al : PaginaCarrinho.getBotaoVoltar().getActionListeners()) {
            PaginaCarrinho.getBotaoVoltar().removeActionListener(al);
        }
        for (ActionListener al : PaginaCarrinho.getBotaoComprar().getActionListeners()) {
            PaginaCarrinho.getBotaoComprar().removeActionListener(al);
        }
        PaginaCarrinho.getBotaoVoltar().addActionListener(_ -> TrocarParaPainel("Pagina de Cardapio"));

        PaginaCarrinho.getBotaoComprar().addActionListener(_ -> {
            if(usuarioLogado.getSaldo() <= usuarioLogado.getCarrinho().getValorTotal()){
                JOptionPane.showMessageDialog(painelPrincipal,"Saldo insuficiente");
                return;
            }
            try {
                usuarioLogado.paga(usuarioLogado.getCarrinho().getValorTotal());
                usuarioLogado.limpaCarrinho();
                PaginaCardapioProdutos.getCampoSaldo().setText(usuarioLogado.getSaldo() + "");

                Dados.atualizaUsuario(usuarioLogado.getId(), usuarioLogado);
                usuarioLogado = (Cliente)Cliente.logarUsuario(usuarioLogado.getEmail(), usuarioLogado.getSenha());

            }catch (UsuarioInvalido ex){
                JOptionPane.showMessageDialog(painelCarrinhoUsuario, "nao foi possivel realizar a compra");
                return;
            }
            CarregaCarrinho();
            JOptionPane.showMessageDialog(painelPrincipal,"Sua compra foi realizada com sucesso 1 ");

        });

    }
}
