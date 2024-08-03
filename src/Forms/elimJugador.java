package Forms;

import Clases.Logeo;
import Clases.Usuarios;

import javax.swing.*;
import java.util.Objects;

/**
 * Clase que muestra la ventana de eliminación de un jugador.
 * @extends JFrame definir sin definir el Frame
 *
 * @extends JFrame definir sin definir el Frame
 */
public class elimJugador extends JFrame{
    private JButton canBtn;
    private JButton acepBtn;
    private JLabel ver;
    private JPanel elimJugador;

    /**
     * Constructor de la clase eliminar Jugador.
     */
    public elimJugador() {
        // Inicialización de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/Admin.jpg"))).getImage());
        setTitle("Eliminar Jugador");
        setContentPane(elimJugador);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Label de confirmación para la eliminación del jugador
        ver.setText("¿Está seguro de eliminar el jugador " + Logeo.getNombre() + "?");

        // Acción para el botón "Aceptar"
        canBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de gestión de jugadores
            new gestionJugadores();
            dispose();
        });

        // Acción para el botón "Cancelar"
        acepBtn.addActionListener(_ -> {
            // Crear objeto de la clase Usuarios y eliminar el jugador
            Usuarios us = new Usuarios();
            us.eliminarRegistro(Logeo.getCedula());
            // Cerrar la ventana actual y abrir la ventana de gestión de jugadores
            new gestionJugadores();
            dispose();
        });
    }
}
