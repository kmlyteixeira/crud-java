package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DAO;

public class Fornecedor {
    private int id;
    private String nome;

    public Fornecedor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Fornecedor(String nome) throws SQLException {
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

    public static void AlteraFornecedor(int id, String nome) throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("UPDATE fornecedor SET nome = ? WHERE id = ?");
        ps.setString(1, nome);
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }

    public static void DeletaFornecedor(int id) throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("DELETE FROM fornecedor WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public static ArrayList<Fornecedor> ListaFornecedores() throws SQLException {

        ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

        PreparedStatement ps = DAO.getConnection().prepareStatement("SELECT * FROM fornecedor ORDER BY id");
    
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            fornecedores.add(new Fornecedor(rs.getInt("id"), rs.getString("nome")));
        }

        return fornecedores;
    }

    @Override
    public String toString() {
        return "Fornecedor: ID: " + id + ", Nome: " + nome;
    }
}
