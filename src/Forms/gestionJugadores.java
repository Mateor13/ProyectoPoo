package Forms;

import Clases.Usuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gestionJugadores extends JFrame {
    private JTable tabl;
    private JButton inserBtn;
    private JButton editBtn;
    private JButton elimBtn;
    private JButton regBtn;
    private JPanel Panel;
    private JLabel ver;

    Usuarios us = new Usuarios();
    public gestionJugadores() {
        setIconImage(new ImageIcon(getClass().getResource("../icono/User.png")).getImage());
        setTitle("Gestión de Jugadores");
        setContentPane(Panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        us.mostrarUsuarios(tabl);
        inserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new adingrUsu();
                dispose();
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new editarJugadores();
                dispose();
            }
        });
        elimBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                us.eliminarRegistro(tabl, ver);
            }
        });
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new admin();
                dispose();
            }
        });
    }
}
