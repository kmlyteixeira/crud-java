package frames.PagamentoFrames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import classes.Pagamento;

import java.awt.*;
import java.sql.SQLException;
/**
 * TODO:
 * Adicionar botão de Voltar
 */

public class JFrameListarPagamento extends JFrame {
    private Container pane;

    public JFrameListarPagamento () throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Data");
        model.addColumn("Descrição");
        model.addColumn("Valor");
        model.addColumn("Fornecedor");
        model.addColumn("Status");

        if (Pagamento.ListaPagamentos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há pagamentos cadastrados!");
            new JFramePagamento();
            dispose();
        }

        Pagamento.ListaPagamentos().forEach((pagamento) -> {
            model.addRow(new Object[] {
                pagamento.getId(),
                pagamento.getData(),
                pagamento.getDescricao(),
                pagamento.getValor(),
                pagamento.getFornecedor().getNome(),
                pagamento.isStatus() ? "Pago" : "Não pago"
            });
        });

        JTable tableView = new JTable(model);
        
        tableView.setPreferredScrollableViewportSize(new Dimension(400,100));

        pane = this.getContentPane();
        pane.add(tableView);

        JScrollPane scrollPane = new JScrollPane(tableView);
        pane.add(scrollPane);
        tableView.setVisible(true); 

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,320);
        this.setResizable(true);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            new JFrameListarPagamento();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
