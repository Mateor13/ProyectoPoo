package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Clase para la interfaz gráfica del dueño.
 * Esta clase proporciona una interfaz gráfica para el dueño,
 * permitiendo al usuario buscar canchas y cerrar sesión.
 *
 * @extends JFrame definir sin definir el Frame
 */
public class duenio extends JFrame{

    private JButton buscarBtn;
    private JPanel due;
    private JButton cerrarBtn;

    /**
     * Constructor de la clase duenio.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public duenio() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Menú encargados");
        setContentPane(due);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Acción para el botón "Buscar Canchas"
        buscarBtn.addActionListener(_ -> {
            // Redirigir a la ventana de búsqueda de canchas
            new buscarc();
            dispose();
        });

        // Acción para el botón "Cerrar Sesión"
        cerrarBtn.addActionListener(_ -> {
            // Redirigir a la ventana de inicio de sesión
            new login();
            dispose();
        });

        // Cambiar color de fondo al pasar el mouse por encima
        buscarBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar color de fondo al pasar el mouse por encima
                super.mouseEntered(e);
                buscarBtn.setBackground(Color.red);
            }
        });

        // Cambiar color de fondo al salir el mouse
        buscarBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                // Cambiar color de fondo al salir el mouse
                super.mouseExited(e);
                buscarBtn.setBackground(Color.black);
            }
        });
    }
}
