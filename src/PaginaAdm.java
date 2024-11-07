import javax.swing.*;

public class PaginaAdm {
    private JPanel PainelAdm;
    private JButton botaoVoltar;
    private JLabel CadastrarProduto;
    private JTextField NomeProdutoCadastro;
    private JTextField DescricaoProdutoCadastro;
    private JTextField PrecoProdutoCadastro;
    private JTextField NomeProdutoAtualiza;
    private JTextField DescricaoProdutoAtualiza;
    private JTextField PrecoProdutoAtualiza;
    private JButton botaoCadastraProduto;
    private JButton botaoAtualizaProduto;
    private JPanel PainelSecundario;
    private JLabel AtualizarProduto;
    private JLabel logo;

    PaginaAdm() {
        botaoVoltar.setBorder(null);
        botaoCadastraProduto.setBorder(null);
        botaoAtualizaProduto.setBorder(null);
        NomeProdutoCadastro.setBorder(null);
        DescricaoProdutoCadastro.setBorder(null);
        PrecoProdutoCadastro.setBorder(null);
        NomeProdutoAtualiza.setBorder(null);
        DescricaoProdutoAtualiza.setBorder(null);
        PrecoProdutoAtualiza.setBorder(null);
    }

    public JPanel getPainelAdm(){
        return PainelAdm;
    }

    public JButton getBotaoVoltar(){
        return botaoVoltar;
    }

    public JTextField getNomeProdutoCadastro(){
        return NomeProdutoCadastro;
    }
    public JTextField getDescricaoProdutoCadastro(){
        return DescricaoProdutoCadastro;
    }
    public JTextField getPrecoProdutoCadastro(){
        return PrecoProdutoCadastro;
    }
    public JTextField getNomeProdutoAtualiza(){
        return NomeProdutoAtualiza;
    }
    public JTextField getDescricaoProdutoAtualiza(){
        return DescricaoProdutoAtualiza;
    }
    public JTextField getPrecoProdutoAtualiza(){
        return PrecoProdutoAtualiza;
    }

    public JButton getBotaoCadastraProduto(){
        return botaoCadastraProduto;
    }
    public JButton getBotaoAtualizaProduto(){
        return botaoAtualizaProduto;
    }

}
