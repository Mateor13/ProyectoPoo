package Forms;

import Clases.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

/**
 * Clase para el inicio de la aplicación.
 * Esta clase proporciona una interfaz gráfica para el inicio de la aplicación,
 * permitiendo al usuario seleccionar una cancha y una fecha para reservar.
 *
 * @extends JFrame sin definir el frame.
 */
public class inicio extends JFrame {
    private JPanel inicio;
    private JLabel bien;
    private JButton busBtn;
    private JButton cerrBtn;
    private JTable table1;
    private JLabel ver1;

    /**
     * Constructor de la clase inicio.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public inicio(){
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Inicio");
        setContentPane(inicio);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        // Mostrar mensaje de bienvenida con el nombre del usuario
        bien.setText("Bienvenid@ " + Logeo.getNombre());
        // Crear objeto de la clase cancha
        cancha canc = new cancha();
        // Mostrar las canchas en la tabla
        canc.mostrarCanchas(table1);

        busBtn.addActionListener(_ -> {
            // Tomar el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            // Verificar si se seleccionó una cancha
            if (table1.getSelectedRow() == -1){
                // Mostrar mensaje de error
                ver1.setText("Seleccione una cancha");
            }else{
                // Limpiar mensaje de error
                ver1.setText("");
                // Obtener el código y nombre de la cancha seleccionada
                    String codigo = model.getValueAt(table1.getSelectedRow(), 0).toString();
                    String nombre = model.getValueAt(table1.getSelectedRow(), 1).toString();
                    // Crear objeto de la clase Logeo
                    Logeo.setCodigo(codigo);
                    Logeo.setNombreCancha(nombre);
                    // Cerrar la ventana actual y abrir el formulario para seleccionar la fecha
                    new fecha();
                    dispose();
            }
        });

        // Acción para el botón "Cerrar Sesión"
        cerrBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir el formulario de login
            new login();
            dispose();
        });
    }

}
