package Clases;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Clase para renderizar imágenes en una tabla.
 * Esta clase permite renderizar imágenes en una tabla de Java.
 *
 * @extends DefaultTableCellRenderer sin definir el renderizador por defecto.
 */
class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    // Método para renderizar la celda de la tabla
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Verificar si el valor es una imagen
        if (value instanceof ImageIcon) {
            // Devolver un JLabel con la imagen
            return new JLabel((ImageIcon) value);
        }
        // Devolver el valor por defecto
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
