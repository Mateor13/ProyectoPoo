import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame{
    private JTextField nombre;
    private JTextField apellido;
    private JTextField ci;
    private JTextField celular;
    private JTextField email;
    private JTextField nacimiento;
    private JButton registrarButton;
    private JButton cancelarButton;
    private JPanel reg;

    public Registro() {
        setTitle("Registro");
        setContentPane(reg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 600));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
