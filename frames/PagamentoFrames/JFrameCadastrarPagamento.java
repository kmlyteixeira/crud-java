package frames.PagamentoFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import classes.Fornecedor;
import classes.Pagamento;
/*
 * TODO: 
 * Fazer os Testes de inserção de dados
 */

public class JFrameCadastrarPagamento extends JFrame {
    private JLabel labelDta;
    private JLabel labelDescricao;
    private JLabel labelValor;
    private JLabel labelFornecedor;
    private JLabel labelStatus;

    private JTextField textDta;
    private JTextField textDescricao;
    private JTextField textValor;
    private JComboBox<String> comboFornecedor;
    private JCheckBox pagoStatus;

    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private Container pane;
    private JPanel pane2;
    private JPanel pane3;
    private JPanel pane4;
    
    public JFrameCadastrarPagamento () throws SQLException {
        super("Cadastrar Pagamento");
        labelDta = new JLabel("Data Vencimento");
        labelDescricao = new JLabel("Descrição");
        labelValor = new JLabel("Valor R$");
        labelFornecedor = new JLabel("Fornecedor");
        labelStatus = new JLabel("Status");

        textDta = new JTextField(10);
        textDescricao = new JTextField(10);
        textValor = new JTextField(10);
        comboFornecedor = new JComboBox<>();
        pagoStatus = new JCheckBox("Pago");

        Fornecedor.ListaFornecedores().forEach((fornecedor) -> {
            comboFornecedor.addItem(fornecedor.getNome());
        });
        
        buttonSalvar = new JButton("Salvar");
        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                    boolean status = pagoStatus.isSelected();
                    Fornecedor forn = Fornecedor.getFornecedorById(comboFornecedor.getSelectedIndex()+1);
                    new Pagamento(
                        textDescricao.getText(),
                        textDta.getText(),
                        Double.parseDouble(textValor.getText()),
                        status,
                        forn
                    );
                    JOptionPane.showMessageDialog(null, "Pagamento Cadastrado com Sucesso!");
                    new JFramePagamento();
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar Pagamento: " + e.getMessage());
                    dispose();
                }
            }
        });

        buttonCancelar = new JButton("Cancelar");

        pane = this.getContentPane();
        pane.setLayout(new GridLayout(2,1));

        pane2 = new JPanel(new GridLayout(5,1,5,5));
        pane3 = new JPanel();
        pane4 = new JPanel(new GridLayout(5,1,5,5));

        pane2.add(labelDta);
        pane4.add(textDta);
        pane2.add(labelDescricao); 
        pane4.add(textDescricao);
        pane2.add(labelValor); 
        pane4.add(textValor);
        pane2.add(labelFornecedor);
        pane4.add(comboFornecedor);
        pane2.add(labelStatus);
        pane4.add(pagoStatus);

        pane3.add(buttonSalvar);
        pane3.add(buttonCancelar);

        pane.add(pane2);
        pane.add(pane4);
        pane.add(pane3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,220);
        this.setResizable(true);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new JFrameCadastrarPagamento();
    }
}
