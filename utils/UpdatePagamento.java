package utils;

public enum UpdatePagamento {
    ID, 
    DESCRICAO,
    DATA,
    VALOR,
    STATUS,
    FORNECEDOR_ID;

    public static String getValue(int value) {
        int ordinalValue = value - 1;
        return UpdatePagamento.values()[ordinalValue].toString().toLowerCase();
    }
}
