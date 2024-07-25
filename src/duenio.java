import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class duenio extends JFrame{

    private JButton buscarBtn;
    private JPanel due;
    private JButton cerrarBtn;

    public duenio() {
        setTitle("Menú encargados");
        setContentPane(due);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new buscarc();
                dispose();
            }
        });
        cerrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
        buscarBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buscarBtn.setBackground(Color.red);
            }
        });
        buscarBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buscarBtn.setBackground(Color.black);
            }
        });
    }
}
