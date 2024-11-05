import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import static java.lang.Double.isNaN;
import static java.lang.Double.parseDouble;

public class Interfaces {
    private final JPanel painelPrincipal;
    private Login paginaLogin = new Login();
    private CadastroPage paginaCadastro = new CadastroPage();
    private PaginaAdm paginaAdm = new PaginaAdm();
    private Cliente usuarioLogado;
    private Admin admin;
    private CardapioProdutos PaginaCardapioProdutos;
    private PaginaCarrinho PaginaCarrinho;
    private JPanel painelLogin;
    private JPanel painelCadastro;
    private JPanel painelCardapio;
    private JPanel painelAdm;
    private JPanel painelCarrinhoUsuario;

    public Interfaces() {

        painelPrincipal = new JPanel(new CardLayout());

        // Inicialização dos painéis
        painelLogin = paginaLogin.criarPainelLogin();
        painelCadastro = paginaCadastro.criarPainelCadastro();
        criaPainelProdutos();
        painelAdm = paginaAdm.getPainelAdm();

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona os painéis ao painel principal
        painelPrincipal.add(painelLogin, "Pagina de Login");
        painelPrincipal.add(painelCadastro, "Pagina de Cadastro");
        painelPrincipal.add(painelCardapio, "Pagina de Cardapio");
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
        paginaLogin.getBotaoEntrar().addActionListener(e -> {
            String email = paginaLogin.getEmail().getText();
            String senha = new String(paginaLogin.getSenha().getPassword());

            if (Dados.obterUsuarioPorEmail(email) == null) {
                JOptionPane.showMessageDialog(painelLogin, "Email ou senha incorretos");
                return;
            }

            try {
                if (Dados.obterUsuarioPorEmail(email) instanceof Admin) {
                    admin = (Admin) Dados.logar(email, senha);
                } else if (Dados.obterUsuarioPorEmail(email) instanceof Cliente) {
                    usuarioLogado = (Cliente) Dados.logar(email, senha);
                    JOptionPane.showMessageDialog(painelLogin, "Usuário " + usuarioLogado.getNome() + " logado");
                }
                painelCardapio.revalidate();
                painelCardapio.repaint();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painelLogin, ex.getMessage());
            }

            if (usuarioLogado != null) {
                JOptionPane.showMessageDialog(painelPrincipal, "Usuário logado com sucesso");
                TrocarParaPainel("Pagina de Cardapio");
                criaCarrinho();
                painelCardapio.revalidate();
                painelCardapio.repaint();
                painelPrincipal.add(painelCarrinhoUsuario, "Pagina de Carrinho");
            } else if (admin != null) {
                JOptionPane.showMessageDialog(painelPrincipal, "Admin logado com sucesso");
                TrocarParaPainel("Pagina de Adm");
            } else {
                JOptionPane.showMessageDialog(painelPrincipal, "Email ou senha incorretos", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }

        });

        paginaLogin.getBotaoCadastrar().addActionListener(e -> TrocarParaPainel("Pagina de Cadastro"));

        paginaCadastro.getBotaoLogin().addActionListener(e -> TrocarParaPainel("Pagina de Login"));

        paginaCadastro.getBotaoCadastrar().addActionListener(e -> {
            String nome = paginaCadastro.getNome().getText();
            String email = paginaCadastro.getEmail().getText();
            String senha1 = Arrays.toString(paginaCadastro.getSenha().getPassword());
            String senha2 = Arrays.toString(paginaCadastro.getSenha2().getPassword());

            if (!senha1.equals(senha2)) {
                JOptionPane.showMessageDialog(painelCadastro, "Senhas Diferentes");
            } else if (nome.isBlank() || email.isBlank() || senha2.isBlank()) {
                JOptionPane.showMessageDialog(painelCadastro, "Email, nome ou senha inválidos");
            } else {
                try {
                    Dados.cadastraCliente(nome, email, senha1);
                    JOptionPane.showMessageDialog(painelCadastro, "Usuário cadastrado com sucesso");
                    TrocarParaPainel("Pagina de Login");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painelCadastro, ex.getMessage());
                }
            }
        });

        paginaAdm.getBotaoCadastraProduto().addActionListener(e -> {
            String nome = paginaAdm.getNomeProdutoCadastro().getText();
            String descricao = paginaAdm.getDescricaoProdutoCadastro().getText();
            double preco = parseDouble(paginaAdm.getPrecoProdutoCadastro().getText());

            if (nome.isBlank() || descricao.isBlank()) {
                JOptionPane.showMessageDialog(painelAdm, "Todos os campos devem ser preenchidos");
            } else if (preco < 0 || isNaN(preco)) {
                JOptionPane.showMessageDialog(painelAdm, "Preço inválido. Por favor, insira um valor válido");
            } else {
                try {
                    admin.cadastrarProduto(nome, descricao, preco);
                    criaPainelProdutos();
                    JOptionPane.showMessageDialog(painelAdm, "Produto cadastrado com sucesso");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(painelAdm, ex.getMessage());
                }
            }
        });

        PaginaCardapioProdutos.getBotaoVoltar().addActionListener(e -> {
            TrocarParaPainel("Pagina de Login");
            if (usuarioLogado != null) usuarioLogado.getCarrinho().imprimeCarrinh();
            usuarioLogado = null;
        });

        PaginaCardapioProdutos.getBotaoCarrinho().addActionListener(e -> TrocarParaPainel("Pagina de Carrinho"));

        paginaAdm.getBotaoVoltar().addActionListener(
                e -> {
                    admin = null;
                    TrocarParaPainel("Pagina de Login");

                }

        );

    }

    public void criaPainelProdutos() {

        PaginaCardapioProdutos = new CardapioProdutos(produto -> {
            if (usuarioLogado == null) {
                JOptionPane.showMessageDialog(painelPrincipal, "Usuário não está logado.");
                return;
            }
            usuarioLogado.adicionaProdutoCarrinho(produto);
            Dados.atualizaUsuario(usuarioLogado.getId(), usuarioLogado);
            usuarioLogado = (Cliente) Dados.obterUsuarioPorEmail(usuarioLogado.getEmail());
            criaCarrinho();
            JOptionPane.showMessageDialog(painelPrincipal, "Produto " + produto.getNome() + " adicionado ao carrinho de " + usuarioLogado.getNome());
        });
        painelCardapio = PaginaCardapioProdutos.getPanel();
        painelCardapio.revalidate();
        painelCardapio.repaint();

    }

    public void criaCarrinho() {
        if (PaginaCarrinho == null && painelCarrinhoUsuario == null ) {
            PaginaCarrinho = new PaginaCarrinho();
            painelCarrinhoUsuario = new JPanel();
        }


        painelCarrinhoUsuario = PaginaCarrinho.getPainelCarrinho(usuarioLogado);
        painelCarrinhoUsuario.revalidate();
        painelCarrinhoUsuario.repaint();

        PaginaCarrinho.getBotaoVoltar().addActionListener(e -> TrocarParaPainel("Pagina de Cardapio"));
        PaginaCarrinho.getBotaoComprar().addActionListener(e -> {
            usuarioLogado.limpaCarrinho();
            Dados.atualizaUsuario(usuarioLogado.getId(), usuarioLogado);
            usuarioLogado = (Cliente) Dados.obterUsuarioPorEmail(usuarioLogado.getEmail());
            criaCarrinho();
            painelCarrinhoUsuario.revalidate();
            painelCarrinhoUsuario.repaint();

        });

    }
}
