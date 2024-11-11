import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.ArrayList;

public class PaginaCarrinho {
    private JPanel PainelCarrinho;
    private JLabel labelLogoProduto;
    private JTable tabelaProdutos;
    private JPanel painelLogo;
    private JButton BotaoVoltar;
    private JButton BotaoComprar;
    private JLabel LabelCarrinho;
    private JPanel PainelPrincipal1;
    private JPanel PainelPrincipal2;
    private JPanel PainelPrincipalInterno;
    private JPanel PainelBotaoVoltar;
    private JPanel PainelLabelPlusProdutos;
    private JPanel PainelLabelProdutos;
    private JScrollPane PainelTabelaProdutos;
    private JTable TabelaProdutos;


    public PaginaCarrinho() {
    }

    public JButton getBotaoVoltar() {
        return BotaoVoltar;
    }

    public JButton getBotaoComprar() {
        return BotaoComprar;
    }

    public JPanel getPainelCarrinho(Cliente cliente) {
        JTable tabelaProdutos =constroiTabelaProdutos(cliente);
        PainelTabelaProdutos.setViewportView(tabelaProdutos);
        PainelTabelaProdutos.revalidate();
        PainelTabelaProdutos.repaint();
        PainelCarrinho.revalidate();
        PainelCarrinho.repaint();
        return PainelCarrinho;
    }

    private JTable constroiTabelaProdutos(Cliente cliente) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "Quantidade", "Preço"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Apenas a coluna de Quantidade é editável
            }
        };

        Carrinho car = cliente.getCarrinho();
        ArrayList<Produto> produtos = car.getCarrinho();

        for (Produto produto : produtos) {
            model.addRow(new Object[]{produto.getNome(), 1, produto.getPreco()});
        }

        TabelaProdutos= new JTable(model);
        TabelaProdutos.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor()); // Configura a coluna de quantidade como Spinner

        model.addTableModelListener(e -> {
            if (e.getColumn() == 1) {
                int numLinha = e.getFirstRow();
                int quantidade = (int) TabelaProdutos.getValueAt(numLinha, 1);
                double preco = produtos.get(numLinha).getPreco() * quantidade;

                TabelaProdutos.setValueAt(String.format("%.2f", preco), numLinha, 2);
                try {
                car.setValorTotal(preco);
                Dados.atualizaUsuario(cliente.getId(),cliente);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(PainelCarrinho, "nao foi possivel adicionar o produto ao carrinho");
                }

            }
        });
        return TabelaProdutos;
    }

    static class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private final JSpinner spinner;

        public SpinnerEditor() {
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }
}
