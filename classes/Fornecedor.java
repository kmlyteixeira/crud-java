package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DAO;

public class Fornecedor {
    private int id;
    private String nome;

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public Fornecedor(int id, String nome) throws SQLException {
        this.id = id;
        this.nome = nome;

        PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO fornecedor (nome) VALUES (?)");
        ps.setString(1, nome);
        ps.execute();
        ps.close();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void getFornecedorById(int id) throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public void AlteraFornecedor() throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("UPDATE fornecedor SET nome = ? WHERE id = ?");
        ps.setString(1, nome);
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }

    public void DeletaFornecedor() throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("DELETE FROM fornecedor WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public void ListaFornecedores() throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("SELECT * FROM fornecedor");
        ps.setInt(1, id);
        ps.execute();
        ps.close();

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "Fornecedor: ID: " + id + ", Nome: " + nome;
    }
}
