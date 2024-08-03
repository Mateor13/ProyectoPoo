package Forms;
import Clases.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Clase que muestra la ventana para reservar la cancha.
 * Esta clase es utilizada para reservar la cancha.
 *
 * @extends JFrame sin definir el frame.
 */
public class Reservar extends JFrame {
    // Componentes de la ventana.
    private JTable tabl;
    private JPanel pane;
    private JButton resBtn;
    private JButton regBtn;
    private JLabel ver;
    private JComboBox hor;

    /**
     * Constructor de la clase.
     * Inicializa los componentes de la ventana y define las acciones de los botones.
     */
    public Reservar() {
        // Inicializa los componentes de la ventana.
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Reservar");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        // Creación de un objeto de la clase Reservas
        Reservas res = new Reservas();
        // Configuración de la tabla
        actualizarTabla(res);

        // Accion del botón "Regresar".
        regBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de inicio.
            new inicio();
            dispose();
        });

        // Accion del botón "Reservar".
        resBtn.addActionListener(_ -> {
            // Verifica si se ha seleccionado un horario.
            if (res.seleccionarRegistro(hor)){
                // Obtiene el horario seleccionado.
                res.tomarValor(hor);
                // Verifica si el horario está disponible.
                if (res.verificarHorario()){
                    // Reserva la cancha.
                    res.reservarCancha(ver);
                    // Actualiza la tabla de reservas.
                    actualizarTabla(res);
                } else {
                    // Muestra un mensaje de error, si selecciona un horario ya registrado.
                    ver.setText("Horario no disponible");
                }
            } else {
                // Muestra un mensaje de error, si no se ha seleccionado un horario.
                ver.setText("Seleccione un horario");
            }
        });
    }

    /**
     * Método que actualiza la tabla de reservas.
     * @param res Objeto de la clase Reservas, donde se toma el método para mostrar la tabla.
     */
    private void actualizarTabla(Reservas res) {
        // Muestra la tabla de reservas.
        res.mostrarReservas(tabl);
    }
}
