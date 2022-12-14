package frames.PagamentoFrames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JFramePagamento extends JFrame {
    private JLabel labelNome;
    private Container pane;
    private JPanel pane2;
    private JPanel pane3;

    private void setVisivel(boolean b) {
        this.setVisible(b);
    }

    public JFramePagamento () {
        super("Pagamento");

        labelNome = new JLabel("Defina o tipo de operação");
        JButton btnCadastro = new JButton("Cadastrar");
        btnCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisivel(false);
                try {
                    new JFrameCadastrarPagamento();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisivel(false);
                try {
                    new JFrameAlterarPagamento();
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
                    new JFrameDeletarPagamento();
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
                    new JFrameListarPagamento();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        pane = this.getContentPane();
        pane.setLayout(new GridLayout(3, 1));
        pane2 = new JPanel();
        pane3 = new JPanel();
        
        pane2.add(labelNome);
        pane3.add(btnCadastro);
        pane3.add(btnAlterar);
        pane3.add(btnDeletar);
        pane3.add(btnListar);

        pane.add(pane2);
        pane.add(pane3);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450,150);
        this.setResizable(false);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new JFramePagamento();
    }
}
