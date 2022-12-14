package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DAO;
import utils.DefineCampoUpdate;

public class Pagamento {

    private int id;
    private String descricao;
    private String data;
    private double valor;
    private boolean status;
    private Fornecedor fornecedor;

    public Pagamento(String descricao, String data, double valor, boolean status, Fornecedor fornecedor) throws SQLException {
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

    public Pagamento(int id, String descricao, String data, double valor, boolean status, Fornecedor fornecedor) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.fornecedor = fornecedor;
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

    public static void DeletaPagamento(int id) throws SQLException {
        PreparedStatement ps = DAO.getConnection().prepareStatement("DELETE FROM pagamento WHERE id = ?");
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public static ArrayList<Pagamento> ListaPagamentos() throws SQLException {

        ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();

        PreparedStatement ps = DAO.getConnection().prepareStatement("SELECT * FROM pagamento");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pagamentos.add(
                    new Pagamento(
                            rs.getInt("id"),
                            rs.getString("descricao"),
                            rs.getString("data"),
                            rs.getDouble("valor"),
                            rs.getBoolean("status"),
                            Fornecedor.getFornecedorById(rs.getInt("fornecedor_id"))
                    )
            );
        }

        return pagamentos;
    }

    @Override
    public String toString() {
        return "Pagamento: ID: " + id + ", Descrição: " + descricao + ", Data: " + data + ", Valor: " + valor + ", Status: " + status + ", Fornecedor: " + fornecedor.getNome();
    }
}
