package utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String estado = table.getModel().getValueAt(row, 3).toString();
        
        if (estado.equals("Pago")) {
            comp.setBackground(Color.GREEN);
        } else {
            comp.setBackground(Color.RED);
        }
        
        return comp;
    }
}