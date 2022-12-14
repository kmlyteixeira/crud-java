package frames.FornecedorFrames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JFrameFornecedor extends JFrame {
    private JLabel labelNome;
    private Container pane;
    private JPanel pane2;
    private JPanel pane3;

    private void setVisivel(boolean b) {
        this.setVisible(b);
    }

    public JFrameFornecedor() throws SQLException {
        super("Fornecedor");
        
        labelNome = new JLabel("Defina o tipo de operação");
        JButton btnCadastro = new JButton("Cadastrar");
        btnCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisivel(false);
                new JFrameCadastrarFornecedor();
            }
        });
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisivel(false);
                try {
                    new JFrameAlterarFornecedor();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisivel(false);
                try {
                    new JFrameDeletarFornecedor();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisivel(false);
                try {
                    new JFrameListarFornecedor();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        pane = this.getContentPane();
        pane2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pane3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pane.setLayout(new GridLayout(2,1));

        pane2.add(labelNome);
        pane3.add(btnCadastro);
        pane3.add(btnAlterar);
        pane3.add(btnDeletar);
        pane3.add(btnListar);

        pane.add(pane2);
        pane.add(pane3);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(410,120);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new JFrameFornecedor();
    }
}
