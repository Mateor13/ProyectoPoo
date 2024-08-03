package Forms;
import Clases.Logeo;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;
import java.util.Objects;

/**
 * Clase que muestra la ventana para seleccionar la fecha de la reserva.
 * Esta clase es utilizada para seleccionar la fecha de la reserva.
 *
 * @extends JFrame sin definir el frame.
 */
public class fecha extends JFrame {
    // Componentes de la ventana.
    private JPanel pane;
    private com.toedter.calendar.JDateChooser fec;
    private JButton buscarButton;
    private JLabel ver;
    private JButton regresarButton;

    /**
     * Constructor de la clase.
     * Inicializa los componentes de la ventana y define las acciones de los botones.
     */
    public fecha() {
        // Inicializa los componentes de la ventana.
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Fecha de reserva");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Accion del botón "Buscar".
        buscarButton.addActionListener(new ActionListener() {
            /**
             * Método que se ejecuta al hacer clic en el botón "Buscar".
             * @param e evento del botón.
             */
            @Override
        public void actionPerformed(ActionEvent e) {
            // Verifica si se ha seleccionado una fecha.
            if (fec.getDate() != null) {
                // Obtiene la fecha seleccionada.
                Date fecha1 = fec.getDate();
                // Verifica si la fecha seleccionada es anterior a la fecha actual.
                if (fecha1.before(new Date())) {
                    // Muestra un mensaje de error.
                    ver.setText("Seleccione una fecha válida");
                } else {
                    // Limpia el mensaje de error.
                    ver.setText("");
                    // Obtiene el día, mes y año de la fecha seleccionada.
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fecha1);
                    int dia = cal.get(Calendar.DAY_OF_MONTH);
                    int mes = cal.get(Calendar.MONTH) + 1;
                    int ano = cal.get(Calendar.YEAR);
                    // Guarda la fecha seleccionada en la clase Logeo.
                    Logeo.setFecha(dia + "/" + mes + "/" + ano);
                    // Abre la ventana para seleccionar la hora de la reserva y cierra
                    // la ventana fecha.
                    new Reservar();
                    dispose();
                }
            } else {
                // Muestra un mensaje de error si no se ha seleccionado la fecha.
                ver.setText("Seleccione una fecha");
            }
        }
        });

        // Acción del botón "Regresar".
        regresarButton.addActionListener(new ActionListener() {
            /**
             * Método que se ejecuta al hacer clic en el botón "Regresar".
             * @param e evento del botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de inicio y cierra la ventana fecha.
                new inicio();
                dispose();
            }
        });
    }

    /**
     * Método para crear el JDateChooser.
     */
    private void createUIComponents() {
        // Crea el JDateChooser.
        fec = new JDateChooser();
    }
}
