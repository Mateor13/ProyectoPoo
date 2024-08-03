package Forms;

import Clases.Logeo;
import Clases.Reservas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clase para gestionar las reservas.
 * Esta clase proporciona una interfaz gráfica para la gestión de reservas,
 * permitiendo al usuario editar y eliminar reservas en una tabla.
 *
 * @extends JFrame sin definir el frame.
 */
public class gestionReservas extends JFrame {
    // Componentes de la ventana.
    private JTable tabl;
    private JButton editBtn;
    private JButton elimBtn;
    private JButton regBtn;
    private JPanel pane;
    private JLabel ver;

    /**
     * Constructor de la clase gestionReservas.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public gestionReservas() {
        // Configuración de la ventana
        setTitle("Gestion de Reservas");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        // Crear objeto de la clase Reservas
        Reservas res = new Reservas();
        // Recargar la tabla con las reservas
        recargarTabla(res);

        // Acción para el botón "Editar"
        editBtn.addActionListener(_ -> {
            // Verificar si se seleccionó una reserva
            if (res.seleccionarReserva(tabl)) {
                // Mostrar mensaje de error
                ver.setText("Seleccione una Reserva");
            } else {
                // Limpiar mensaje de error
                ver.setText("");
                // Obtener el código de la reserva seleccionada
                DefaultTableModel model = (DefaultTableModel) tabl.getModel();
                // Obtener el código de la reserva seleccionada, se lo castea a entero
                int codigo = Integer.parseInt((String) model.getValueAt(tabl.getSelectedRow(), 0));
                // Establecer los datos de la reserva seleccionada
                Logeo.setNumReserva(codigo);
                Logeo.setNombreCancha((String) model.getValueAt(tabl.getSelectedRow(), 3));
                Logeo.setHorario((String) model.getValueAt(tabl.getSelectedRow(), 5));
                Logeo.setFecha((String) model.getValueAt(tabl.getSelectedRow(), 6));
                // Cerrar la ventana actual y abrir el formulario para editar la reserva
                new editarReservas();
                dispose();
            }
        });

        // Acción para el botón "Eliminar"
        elimBtn.addActionListener(_ -> {
            // Crear el modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) tabl.getModel();
            // Verificar si se seleccionó una reserva en la tabla
            if(res.seleccionarReserva(tabl)){
                // Mostrar mensaje de error
                ver.setText("Seleccione una Reserva");
            }else{
                // Limpiar mensaje de error
                ver.setText("");
                // Obtener el código de la reserva seleccionada, cateado a entero
                int codigo = Integer.parseInt((String) model.getValueAt(tabl.getSelectedRow(), 0));
                // Establecer el código de la reserva seleccionada
                Logeo.setNumReserva(codigo);
                // Cerrar la ventana actual y abrir el formulario para eliminar la reserva
                new elimReservas();
                dispose();
            }
        });

        // Acción para el botón "Regresar"
        regBtn.addActionListener(_ -> {
            // Cerrar la ventana y abrir el menú de administrador
            new admin();
            dispose();
        });
    }

    /**
     * Método para recargar la tabla con las reservas.
     * @param res Objeto de la clase Reservas para tomar el metodo de mostrar Reservas en una tabla.
     */
    private void recargarTabla(Reservas res){
        // Crear un modelo de tabla
        res.mostrarReservasDuenio(tabl);
    }
}
