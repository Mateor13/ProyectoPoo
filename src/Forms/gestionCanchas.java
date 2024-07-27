package Forms;
import Clases.cancha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gestionCanchas extends JFrame{
    private JPanel gescan;
    private JTable tablaCanchas;
    private JButton ingresarBtn;
    private JButton editarBtn;
    private JButton elimBtn;
    private JButton regresarBtn;

    public gestionCanchas (){
        setIconImage(new ImageIcon(getClass().getResource("../icono/Admin.jpg")).getImage());
        setTitle("Gestión de Canchas");
        setContentPane(gescan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,400));
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        cancha can = new cancha();
        can.mostrarCanchas(tablaCanchas);

        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new adingrCan();
                dispose();
            }
        });
        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        elimBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new admin();
                dispose();
            }
        });
    }
}
