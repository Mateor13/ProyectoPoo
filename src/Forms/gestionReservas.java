package Forms;

import Clases.Logeo;
import Clases.Reservas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gestionReservas extends JFrame {
    private JTable tabl;
    private JButton resBtn;
    private JButton editBtn;
    private JButton elimBtn;
    private JButton regBtn;
    private JPanel pane;
    private JLabel ver;

    public gestionReservas() {
        setTitle("Gestion de Reservas");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        Reservas res = new Reservas();
        recargarTabla(res);


        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!res.seleccionarReserva(tabl)) {
                    ver.setText("Seleccione una Reserva");
                } else {
                    ver.setText("");
                    DefaultTableModel model = (DefaultTableModel) tabl.getModel();
                    int codigo = Integer.parseInt((String) model.getValueAt(tabl.getSelectedRow(), 0));
                    Logeo.setNumReserva(codigo);
                    new editarReservas();
                    dispose();
                }
            }
        });
        elimBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tabl.getModel();
                if(!res.seleccionarReserva(tabl)){
                    ver.setText("Seleccione una Reserva");
                }else{
                    ver.setText("");
                    int codigo = Integer.parseInt((String) model.getValueAt(tabl.getSelectedRow(), 0));
                    Logeo.setNumReserva(codigo);
                    new elimReservas();
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
    private void recargarTabla(Reservas res){
        res.mostrarReservasDuenio(tabl);
    }
}
