package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DAO;
import utils.DefineCampoUpdate;

public class Pagamento {

    private int id;
    private String descricao;
    private String data;
    private double valor;
    private boolean status;
    private Fornecedor fornecedor;

    public Pagamento(String descricao, String data, double valor, boolean status, Fornecedor fornecedor) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.fornecedor = fornecedor;
    }

    public Pagamento(int id, String descricao, String data, double valor, boolean status, Fornecedor fornecedor) throws SQLException {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.fornecedor = fornecedor;

        PreparedStatement ps = DAO.getConnection().prepareStatement("INSERT INTO pagamento (descricao, data, valor, status, fornecedor_id) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, descricao);
        ps.setString(2, data);
        ps.setDouble(3, valor);
        ps.setBoolean(4, status);
        ps.setInt(5, fornecedor.getId());
        ps.execute();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void getPagamentoById(int id) throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("SELECT * FROM pagamento WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public void AlteraPagamento(int tipo, String input) throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("UPDATE pagamento SET "+DefineCampoUpdate.defineCampoUpdate(tipo)+" = ? WHERE id = ?");
        ps.setString(1, input);
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }

    public void DeletaPagamento() throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("DELETE FROM pagamento WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public void ListaPagamentos() throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("SELECT * FROM pagamento");
        ps.execute();
        ps.close();

        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "Pagamento: ID: " + id + ", Descrição: " + descricao + ", Data: " + data + ", Valor: " + valor + ", Status: " + status + ", Fornecedor: " + fornecedor.getNome();
    }
}
