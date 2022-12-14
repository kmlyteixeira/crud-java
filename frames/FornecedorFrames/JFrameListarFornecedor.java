package frames.FornecedorFrames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.SQLException;

import classes.Fornecedor;

public class JFrameListarFornecedor extends JFrame {
    private Container pane;

    public JFrameListarFornecedor() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");

        Fornecedor.ListaFornecedores().forEach((fornecedor) -> {
            model.addRow(new Object[] {
                fornecedor.getId(),
                fornecedor.getNome()
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
        this.setSize(310,220);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new JFrameListarFornecedor();
    }
}
