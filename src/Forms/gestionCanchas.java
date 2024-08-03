package Forms;
// Importar clases necesarias
import Clases.*;
// Importar librerías necesarias
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

/**
 * Clase para gestionar las canchas.
 * Esta clase proporciona una interfaz gráfica para la gestión de canchas,
 * permitiendo al usuario ingresar, editar, eliminar y ver canchas en una tabla.
 *
 * @extends JFrame sin definir el frame.
 */
public class gestionCanchas extends JFrame{
    private JPanel gescan;
    private JTable tablaCanchas;
    private JButton ingresarBtn;
    private JButton editarBtn;
    private JButton elimBtn;
    private JButton regresarBtn;
    private JLabel ver;

    /**
     * Constructor de la clase gestionCanchas.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public gestionCanchas (){
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/Admin.png"))).getImage());
        setTitle("Gestión de Canchas");
        setContentPane(gescan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,400));
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        // Crear objeto de la clase cancha
        cancha can = new cancha();
        // Actualizar la tabla de canchas
        actualizarCanchas(can);

        // Acción para el botón "Ingresar"
        ingresarBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de ingreso de canchas
            new adingrCan();
            dispose();
        });

        // Acción para el botón "Editar"
        editarBtn.addActionListener(_ -> {
            // Verificar si se ha seleccionado una cancha
            if (can.seleccionarRegitro(tablaCanchas)) {
                ver.setText("Seleccione una Cancha");
            } else {
                // Cerrar la ventana actual y abrir la ventana de edición de canchas
                ver.setText("");
                // Obtener el código y nombre de la cancha seleccionada
                DefaultTableModel model = (DefaultTableModel) tablaCanchas.getModel();
                // Establecer el código y nombre de la cancha seleccionada
                String codigo = model.getValueAt(tablaCanchas.getSelectedRow(), 0).toString();
                String nombre1 = model.getValueAt(tablaCanchas.getSelectedRow(), 1).toString();
                Logeo.setCodigo(codigo);
                Logeo.setNombreCancha(nombre1);
                new editarCanchas();
                dispose();
            }
        });

        // Acción para el botón "Eliminar"
        elimBtn.addActionListener(_ -> {
            // Verificar si se ha seleccionado una cancha
            DefaultTableModel model = (DefaultTableModel) tablaCanchas.getModel();
            if(can.seleccionarRegitro(tablaCanchas)){
                ver.setText("Seleccione una Cancha");
            }else{
                // Cerrar la ventana actual y abrir la ventana de eliminación de canchas
                ver.setText("");
                // Obtener el código y nombre de la cancha seleccionada
                String codigo = model.getValueAt(tablaCanchas.getSelectedRow(), 0).toString();
                String nombre1 = model.getValueAt(tablaCanchas.getSelectedRow(), 1).toString();
                Logeo.setCodigo(codigo);
                Logeo.setNombreCancha(nombre1);
                new elimCancha();
                dispose();
            }
        });

        // Acción para el botón "Regresar"
        regresarBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de administrador
            new admin();
            dispose();
        });
    }

    /**
     * Actualiza la tabla de canchas con la información actual.
     *
     * @param can Objeto de la clase cancha que contiene la información de las canchas.
     */
    private void actualizarCanchas(cancha can){
        // Mostrar las canchas en la tabla
        can.mostrarCanchas(tablaCanchas);
    }
}
