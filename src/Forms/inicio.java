package Forms;

import javax.swing.*;
import java.awt.*;

public class inicio extends JFrame {
    private JPanel inicio;
    inicio(){
        setTitle("Inicio");
        setContentPane(inicio);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
