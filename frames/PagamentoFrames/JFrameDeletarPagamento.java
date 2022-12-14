package frames.PagamentoFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import classes.Pagamento;

public class JFrameDeletarPagamento extends JFrame {
    private JLabel labelPagamento;
    private JComboBox<String> comboPagamento;

    private JButton buttonDeletar;
    private JButton buttonCancelar;

    private Container pane;
    private JPanel pane2;
    private JPanel pane3;

    public JFrameDeletarPagamento () throws SQLException {
        super("Deletar Pagamento");
        labelPagamento = new JLabel("Selecione o pagamento a deletar: ");
        comboPagamento = new JComboBox<>();

        if (Pagamento.ListaPagamentos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há pagamentos cadastrados!");
            new JFramePagamento();
            dispose();
        }

        Pagamento.ListaPagamentos().forEach((pagamento) -> {
            comboPagamento.addItem(pagamento.getDescricao());
        });

        buttonDeletar = new JButton("Deletar");
        buttonDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                    Pagamento.DeletaPagamento(
                        comboPagamento.getSelectedIndex()+1
                    );
                    JOptionPane.showMessageDialog(null, "Fornecedor Deletado com Sucesso!");
                    new JFramePagamento();
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao deletar Fornecedor: " + e.getMessage());
                }
            }
        });
        buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new JFramePagamento();
                dispose();
            }
        });

        pane = this.getContentPane();
        pane.setLayout(new GridLayout(2,1));

        pane2 = new JPanel(new GridLayout(2,1,5,5));
        pane3 = new JPanel();

        pane2.add(labelPagamento);
        pane2.add(comboPagamento);

        pane3.add(buttonDeletar);
        pane3.add(buttonCancelar);

        pane.add(pane2);
        pane.add(pane3);

        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            new JFrameDeletarPagamento();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
