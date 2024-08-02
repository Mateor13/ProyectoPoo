package Forms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para la interfaz gráfica del administrador.
 * Esta clase proporciona una interfaz gráfica para el administrador,
 * permitiendo al usuario gestionar jugadores, canchas y reservas.
 */
public class admin extends JFrame {
    private JButton JugBtn;
    private JPanel Adm;
    private JButton CanchaBtn;
    private JButton salirButton;
    private JButton reservasBtn;

    /**
     * Constructor de la clase admin.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public admin() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(getClass().getResource("../icono/Admin.jpg")).getImage());
        setTitle("Administrador");
        setContentPane(Adm);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Acción para el botón "Jugadores"
        JugBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirigir a la ventana de gestión de jugadores
                new gestionJugadores();
                dispose();
            }
        });

        CanchaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirigir a la ventana de gestión de canchas
                new gestionCanchas();
                dispose();
            }
        });

        reservasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirigir a la ventana de gestión de reservas
                new gestionReservas();
                dispose();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirigir a la ventana de inicio de sesión
                new login();
                dispose();
            }
        });
    }
}
