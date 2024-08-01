package Forms;

import Clases.Logeo;
import Clases.Usuarios;

import javax.swing.*;

public class elimJugador extends JFrame{
    private JButton canBtn;
    private JButton acepBtn;
    private JLabel ver;
    private JPanel elimJugador;
    public elimJugador(){
        setTitle("Eliminar Jugador");
        setContentPane(elimJugador);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        ver.setText("¿Está seguro de eliminar el jugador " + Logeo.getNombre() + "?");
        canBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new gestionJugadores();
                dispose();
            }
        });
        acepBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Usuarios us = new Usuarios();
                us.eliminarRegistro(Logeo.getCedula());
                new gestionJugadores();
                dispose();
            }
        });
    }
}
