package frames.PagamentoFrames;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import classes.Pagamento;

public class JFrameAlterarPagamento extends JFrame {
    private JLabel labelDta;
    private JLabel labelDescricao;
    private JLabel labelValor;

    private JTextField textDta;
    private JTextField textDescricao;
    private JTextField textValor;

    private JLabel labelPagamento;
    private JComboBox<String> comboPagamento;

    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private Container pane;
    private JPanel pane2;
    private JPanel pane3;

    public JFrameAlterarPagamento () throws SQLException {
        super("Alterar Pagamento");
        labelPagamento = new JLabel("Selecione o pagamento a alterar: ");
        comboPagamento = new JComboBox<>();

        if (Pagamento.ListaPagamentos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há pagamentos cadastrados!");
            new JFramePagamento();
            dispose();
        }

        Pagamento.ListaPagamentos().forEach((pagamento) -> {
            comboPagamento.addItem(pagamento.getDescricao());
        });

        Pagamento pagamento = Pagamento.getPagamentoById(comboPagamento.getSelectedIndex()+1);

        labelDta = new JLabel("Data: ");    
        labelDescricao = new JLabel("Descrição: ");
        labelValor = new JLabel("Valor: ");

        textDta = new JTextField(pagamento.getData() + "");
        textDescricao = new JTextField(pagamento.getDescricao() + "");
        textValor = new JTextField(pagamento.getValor() + "");

        buttonSalvar = new JButton("Alterar");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Pagamento pagamento = Pagamento.getPagamentoById(comboPagamento.getSelectedIndex()+1);
                    pagamento.AlteraPagamento(
                        comboPagamento.getSelectedIndex()+1,
                        textDta.getText(),
                        textDescricao.getText(),
                        Double.parseDouble(textValor.getText())
                    );
                    JOptionPane.showMessageDialog(null, "Pagamento Alterado com Sucesso!");
                    new JFramePagamento();
                    dispose();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar Pagamento: " + e.getMessage());
                    new JFramePagamento();
                    dispose();
                }
            }
        });
        buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        pane = this.getContentPane();
        pane.setLayout(new GridLayout(2,1));

        pane2 = new JPanel(new GridLayout(5,1,5,5));
        pane3 = new JPanel();

        pane2.add(labelPagamento);
        pane2.add(comboPagamento);
        pane2.add(labelDta);
        pane2.add(textDta);
        pane2.add(labelDescricao);
        pane2.add(textDescricao);
        pane2.add(labelValor);
        pane2.add(textValor);

        pane3.add(buttonSalvar);
        pane3.add(buttonCancelar);

        pane.add(pane2);
        pane.add(pane3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,320);
        this.setResizable(true);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        JFrameAlterarPagamento janela = new JFrameAlterarPagamento();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
