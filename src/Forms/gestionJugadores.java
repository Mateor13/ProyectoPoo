package Forms;

import Clases.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
                DefaultTableModel model = (DefaultTableModel) tabl.getModel();
               if(!us.seleccionarRegitro(tabl)){
                     ver.setText("Seleccione un jugador");
               }else{
                    ver.setText("");
                    String cedula1 = model.getValueAt(tabl.getSelectedRow(), 0).toString();
                    String nombre1 = model.getValueAt(tabl.getSelectedRow(), 1).toString();
                    Logeo.setCedula(cedula1);
                    Logeo.setNombre(nombre1);
                   new elimJugador();
                   dispose();
               }

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
