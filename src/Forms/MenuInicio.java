package Forms;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Clase que crea el formulario de inicio de sesión.
 * Esta clase proporciona una interfaz gráfica para el inicio de sesión,
 * permitiendo al usuario ingresar su correo y contraseña para acceder a la aplicación.
 *
 * @extends JFrame sin definir el frame.
 */
public class MenuInicio extends JFrame {
    // Componentes del formulario
    private JButton ingresarBtn;
    private JButton cerrarBtn;
    private JPanel menu;

    /**
     * Constructor de la clase login.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public MenuInicio() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Menu Inicio");
        setContentPane(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Acción del botón "Ingresar"
        ingresarBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir el formulario de inicio de sesión
            new login();
            dispose();
        });

        // Acción del botón "Cerrar"
        cerrarBtn.addActionListener(_ -> {
            // Cerrar la ventana actual
            dispose();
        });
    }
}
