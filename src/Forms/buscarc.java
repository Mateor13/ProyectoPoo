package Forms;
import Clases.cancha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class buscarc extends JFrame {
    private JTable canc;
    private JButton buscarBtn;
    private JPanel Bus;
    private JButton regBtn;

    public buscarc() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/cancha.png"))).getImage());
        setTitle("Buscar Cliente");
        setContentPane(Bus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        canc.setVisible(false);
        regBtn.setVisible(false);
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarBtn.setVisible(false);
                canc.setVisible(true);
                regBtn.setVisible(true);
                cancha canch = new cancha();
                }
        });
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new duenio();
                dispose();
            }
        });
    }
}
