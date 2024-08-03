package Forms;

import Clases.Logeo;
import Clases.Reservas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Clase que crea el Panel de confirmación de eliminación de una reserva.
 * Esta clase es parte de la aplicación de reservas de canchas de fútbol.
 *
 * @extends JFrame definir sin definir el Frame
 */
public class elimReservas extends JFrame {
    // Componentes de la ventana
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JPanel pane;

    /**
     * Constructor de la clase eliminar Reservas.
     */
    public elimReservas() {
        // Inicialización de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/Admin.jpg"))).getImage());
        setTitle("Eliminar Reservas");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Acción para el botón "Aceptar"
        aceptarButton.addActionListener(_ -> {
            // Crear objeto de la clase Reservas y eliminar la reserva
            Reservas res = new Reservas();
            res.eliminarRegistro(Logeo.getNumReserva());
            // Cerrar la ventana actual y abrir la ventana de gestión de reservas
            new gestionReservas();
            dispose();
        });

        // Acción para el botón "Cancelar"
        cancelarButton.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de gestión de reservas
            new gestionReservas();
            dispose();
        });
    }
}
