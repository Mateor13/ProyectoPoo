package Forms;

import Clases.Logeo;
import Clases.Reservas;
import Clases.cancha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class elimReservas extends JFrame {

    private JButton cancelarButton;
    private JButton aceptarButton;
    private JPanel pane;

    public elimReservas() {
        setTitle("Eliminar Reservas");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reservas res = new Reservas();
                res.eliminarRegistro(Logeo.getNumReserva());
                new gestionReservas();
                dispose();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gestionReservas();
                dispose();
            }
        });
    }
}
