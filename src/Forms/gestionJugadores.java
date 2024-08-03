package Forms;

import Clases.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;

/**
 * Clase para gestionar los jugadores.
 * Esta clase proporciona una interfaz gráfica para la gestión de jugadores,
 * permitiendo al usuario ingresar, editar, eliminar y ver jugadores en una tabla.
 *
 * @extends JFrame sin definir el frame.
 */
public class gestionJugadores extends JFrame {
    // Componentes de la ventana.
    private JTable tabl;
    private JButton inserBtn;
    private JButton editBtn;
    private JButton elimBtn;
    private JButton regBtn;
    private JPanel Panel;
    private JLabel ver;
    // Crear objeto de la clase Usuarios
    Usuarios us = new Usuarios();

    /**
     * Constructor de la clase gestionJugadores.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public gestionJugadores() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/User.png"))).getImage());
        setTitle("Gestión de Jugadores");
        setContentPane(Panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        // Actualizar la tabla de jugadores
        us.mostrarUsuarios(tabl);

        // Acción para el botón "Ingresar"
        inserBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de ingreso de jugadores
            new adingrUsu();
            dispose();
        });

        // Acción para el botón "Editar"
        editBtn.addActionListener(_ -> {
            // Crear objeto de la clase Usuarios
            DefaultTableModel model = (DefaultTableModel) tabl.getModel();
            // Verificar si se ha seleccionado un jugador de la tabla
            if(us.seleccionarRegitro(tabl)){
                // Mostrar mensaje de error
                ver.setText("Seleccione un jugador");
            }else{
                // Limpiar mensaje de error
                ver.setText("");
                // Obtener los datos del jugador seleccionado de la tabla
                String cedula1 = model.getValueAt(tabl.getSelectedRow(), 0).toString();
                String nombre1 = model.getValueAt(tabl.getSelectedRow(), 1).toString();
                // Guardar los datos del jugador seleccionado en la clase Logeo
                Logeo.setCedula(cedula1);
                Logeo.setNombre(nombre1);
                // Cerrar la ventana actual y abrir la ventana de edición de jugadores
                new editarJugadores();
                dispose();
            }
        });

        // Acción para el botón "Eliminar"
        elimBtn.addActionListener(_ -> {
            // Crear objeto de la clase Usuarios
            DefaultTableModel model = (DefaultTableModel) tabl.getModel();
            // Verificar si se ha seleccionado un jugador de la tabla
           if(us.seleccionarRegitro(tabl)){
               // Mostrar mensaje de error
                 ver.setText("Seleccione un jugador");
           }else{
               // Limpiar mensaje de error
                ver.setText("");
                // Obtener los datos del jugador seleccionado de la tabla
                String cedula1 = model.getValueAt(tabl.getSelectedRow(), 0).toString();
                String nombre1 = model.getValueAt(tabl.getSelectedRow(), 1).toString();
                // Guardar los datos del jugador seleccionado en la clase Logeo
                Logeo.setCedula(cedula1);
                Logeo.setNombre(nombre1);
                // Cerrar la ventana actual y abrir la ventana de eliminación de jugadores
               new elimJugador();
               dispose();
           }

        });

        // Acción para el botón "Regresar"
        regBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de administrador
            new admin();
            dispose();
        });
    }
}
