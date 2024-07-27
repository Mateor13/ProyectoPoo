import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuInicio extends JFrame {
    private JButton ingresarBtn;
    private JButton cerrarBtn;
    private JPanel menu;

    public MenuInicio() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("./icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Menu Inicio");
        setContentPane(menu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
        cerrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
