package Forms;
import Clases.*;

import javax.swing.*;
import java.util.Objects;

/**
 * Clase que crea el Panel de confirmación de eliminación de una cancha.
 * Esta clase es parte de la aplicación de reservas de canchas de fútbol.
 *
 * @extends JFrame definir sin definir el Frame
 */
public class elimCancha extends JFrame{
    // Componentes de la ventana
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JLabel ver;
    private JPanel elimCancha;

    /**
     * Constructor de la clase eliminar Cancha.
     */
    public elimCancha() {
        // Inicialización de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/Admin.jpg"))).getImage());
        setTitle("Eliminar Cancha");
        setContentPane(elimCancha);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        // Label de confirmación para la eliminación de la cancha
        ver.setText("¿Está seguro de eliminar la cancha " + Logeo.getNombreCancha() + "?");

        // Acción para el botón "Aceptar"
        aceptarButton.addActionListener(_ -> {
            // Crear objeto de la clase cancha y eliminar la cancha
            cancha can = new cancha();
            can.eliminarRegistro(Logeo.getCodigo());
            // Cerrar la ventana actual y abrir la ventana de gestión de canchas
            new gestionCanchas();
            dispose();
        });

        // Acción para el botón "Cancelar"
        cancelarButton.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de gestión de canchas
            new gestionCanchas();
            dispose();
        });
    }
}
