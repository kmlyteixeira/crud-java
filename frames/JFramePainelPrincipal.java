package frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import classes.Pagamento;
import frames.FornecedorFrames.JFrameFornecedor;
import frames.PagamentoFrames.JFramePagamento;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JFramePainelPrincipal extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuPrincipal;

    private JMenuItem menuItemFornecedor;
    private JMenuItem menuItemPagamento;
    private JMenuItem menuItemSair;

    private JLabel labelNome;
    private JLabel labelNome2;
    private JLabel labelAux;
    private Container pane;
    private JButton btnPagar;

    public JFramePainelPrincipal() throws SQLException {
        
        labelNome = new JLabel("Profª Cláudia - BEM VINDA AO SISTEMA DE GERENCIAMENTO DE CONTAS A PAGAR");

        menuPrincipal = new JMenu("Menu");

        menuItemFornecedor = new JMenuItem("Fornecedor");
        menuItemPagamento = new JMenuItem("Pagamento");
        menuItemSair = new JMenuItem("Sair");

        menuPrincipal.add(menuItemFornecedor);
        menuPrincipal.add(menuItemPagamento);
        menuPrincipal.add(menuItemSair);

        menuItemFornecedor.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    new JFrameFornecedor();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemPagamento.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new JFramePagamento();
            }
        });

        menuItemSair.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Saindo do Sistema! Até mais! :D");
                System.exit(0);
            }
        });

        menuBar = new JMenuBar();
        menuBar.add(menuPrincipal);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Data");
        model.addColumn("Descrição");
        model.addColumn("Valor");
        model.addColumn("Fornecedor");
        model.addColumn("Status");
        model.addColumn("Ações");

        Pagamento.ListaPagamentos().forEach((pagamento) -> {
            model.addRow(new Object[] {
                pagamento.getId(),
                pagamento.getData(),
                pagamento.getDescricao(),
                pagamento.getValor(),
                pagamento.getFornecedor().getNome(),
                pagamento.isStatus() ? "Pago" : "Não pago",
                btnPagar = new JButton()
            });
        });

        btnPagar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "Pagamento atualizado com sucesso!");
            }
        });

        JTable tableView = new JTable(model);
        
        tableView.setPreferredScrollableViewportSize(new Dimension(400,100));

        pane = this.getContentPane();
        pane.setLayout(new FlowLayout());
        pane.add(labelNome);

        if (Pagamento.ListaPagamentos().isEmpty()) {
            labelNome2 = new JLabel("Nenhum pagamento cadastrado!");
            labelAux = new JLabel("Cadastre um pagamento para visualizar aqui!");
            pane.add(labelNome2);
            pane.add(labelAux);
            tableView.setVisible(false);
        } else {
            labelNome2 = new JLabel("Pagamentos cadastrados:");
            pane.add(tableView);
            tableView.setVisible(true);
            JScrollPane scrollPane = new JScrollPane(tableView);
            pane.add(scrollPane);
        }

        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,320);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new JFramePainelPrincipal();
    }
}
