package Forms;

import javax.swing.*;

public class gestionReservas extends JFrame {
    private JTable table1;
    private JButton realizarUnaNuevaReservaButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JButton regButton;
    private JPanel pane;

    public gestionReservas() {
        setTitle("Gestion de Reservas");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
