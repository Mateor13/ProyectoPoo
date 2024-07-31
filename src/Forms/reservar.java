package Forms;

import javax.swing.*;
import java.awt.*;

public class reservar extends JFrame {
    private JTable table1;
    private JPanel pane;
    private JButton reservarButton;
    private JButton regresarButton;
    private JLabel ver;

    public reservar() {
        setTitle("Reservar");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
