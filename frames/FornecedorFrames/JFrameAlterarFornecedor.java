package frames.FornecedorFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import classes.Fornecedor;

public class JFrameAlterarFornecedor extends JFrame{
    private JLabel labelNome;
    private JTextField textNome;
    private JComboBox<String> comboFornecedor;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private Container pane;
    private JPanel pane2;
    private JPanel pane3;

    public JFrameAlterarFornecedor() throws SQLException {
        labelNome = new JLabel("Selecione o Fornecedor");
        JLabel labelNome2 = new JLabel("Informe o novo nome");
        textNome = new JTextField(25);
        comboFornecedor = new JComboBox<>();

        if (Fornecedor.ListaFornecedores().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há Fornecedores cadastrados!");
            new JFrameFornecedor();
            dispose();
        }

        Fornecedor.ListaFornecedores().forEach((fornecedor) -> {
            comboFornecedor.addItem(fornecedor.getNome());
        });

        buttonSalvar = new JButton("Salvar");
        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                    Fornecedor.AlteraFornecedor(
                        comboFornecedor.getSelectedIndex()+1,
                        textNome.getText()
                    );
                    JOptionPane.showMessageDialog(null, "Fornecedor Alterado com Sucesso!");
                    new JFrameFornecedor();
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar Fornecedor: " + e.getMessage());
                    dispose();
                }
            }
        });
        buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    new JFrameFornecedor();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dispose();
            }
        });

        pane = this.getContentPane();
        pane.setLayout(new GridLayout(2,1));

        pane2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pane3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pane2.add(labelNome);
        pane2.add(comboFornecedor);
        pane2.add(labelNome2);
        pane2.add(textNome);
        pane3.add(buttonSalvar);
        pane3.add(buttonCancelar);

        pane.add(pane2);
        pane.add(pane3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(310,220);
        this.setResizable(false);
        this.setVisible(true);
    }

    
    public static void main(String[] args) throws SQLException {
        new JFrameAlterarFornecedor();
    }
}

