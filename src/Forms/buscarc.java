package Forms;
import Clases.Reservas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Clase para la interfaz gráfica de buscar cliente.
 * Esta clase proporciona una interfaz gráfica para buscar un cliente y mostrar sus reservas.
 *
 * @extends JFrame definir sin definir el Frame
 */
public class buscarc extends JFrame {
    private JTable canc;
    private JButton buscarBtn;
    private JPanel Bus;
    private JButton regBtn;
    private JScrollPane scroll;

    /**
     * Constructor de la clase buscarc.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public buscarc() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/cancha.png"))).getImage());
        setTitle("Buscar Cliente");
        setContentPane(Bus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Ocultar la tabla de reservas, el botón "Cancelar" y el botón "Regresar"
        scroll.setVisible(false);
        canc.setVisible(false);

        // Acción para el botón "Buscar"
        buscarBtn.addActionListener(_ -> {
            // Ocultar el botón "Buscar" y mostrar la tabla de reservas
            buscarBtn.setVisible(false);
            scroll.setVisible(true);
            canc.setVisible(true);
            regBtn.setVisible(true);
            // Crear objeto de la clase Reservas
            Reservas res = new Reservas();
            // Mostrar las reservas del cliente en modo Dueño
            res.mostrarReservasDuenio(canc);
        });

        // Acción para el botón "Regresar"
        regBtn.addActionListener(_ -> {
            // Redirigir a la ventana de dueño
            new duenio();
            dispose();
        });
    }
}
