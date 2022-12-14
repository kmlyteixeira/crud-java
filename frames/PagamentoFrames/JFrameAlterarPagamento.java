package frames.PagamentoFrames;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import classes.Pagamento;
/**
 * TODO:
 * Adicionar função aos botões
 * Setar valores nos campos de texto
 */
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

        Pagamento.ListaPagamentos().forEach((pagamento) -> {
            comboPagamento.addItem(pagamento.getDescricao());
        });

        labelDta = new JLabel("Data: ");    
        labelDescricao = new JLabel("Descrição: ");
        labelValor = new JLabel("Valor: ");

        textDta = new JTextField();
        textDescricao = new JTextField();
        textValor = new JTextField();

        buttonSalvar = new JButton("Alterar");
        buttonCancelar = new JButton("Cancelar");

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
        this.setSize(400,220);
        this.setResizable(true);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        JFrameAlterarPagamento janela = new JFrameAlterarPagamento();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
