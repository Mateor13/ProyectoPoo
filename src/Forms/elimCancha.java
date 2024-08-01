package Forms;
import Clases.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class elimCancha extends JFrame{
    private JButton cancelarButton;
    private JButton aceptarButton;
    private JLabel ver;
    private JPanel elimCancha;

    public elimCancha() {
        setTitle("Eliminar Cancha");
        setContentPane(elimCancha);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        ver.setText("¿Está seguro de eliminar la cancha " + Logeo.getNombreCancha() + "?");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancha can = new cancha();
                can.eliminarRegistro(Logeo.getCodigo());
                new gestionCanchas();
                dispose();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gestionCanchas();
                dispose();
            }
        });
    }
}
