package Forms;

import Clases.*;
import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class inicio extends JFrame {
    private JPanel inicio;
    private JLabel bien;
    private JButton buscarButton;
    private JButton cerrarSesiónButton;
    private JTable table1;
    private JLabel ver1;

    public inicio(){
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Inicio");
        setContentPane(inicio);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        bien.setText("Bienvenid@ " + Logeo.getNombre());
        cancha canc = new cancha();
        canc.mostrarCanchas(table1);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                if (table1.getSelectedRow() == -1){
                    ver1.setText("Seleccione una cancha");
                }else{
                    ver1.setText("");
                        String codigo = model.getValueAt(table1.getSelectedRow(), 0).toString();
                        String nombre = model.getValueAt(table1.getSelectedRow(), 1).toString();
                        Logeo.setCodigo(codigo);
                        Logeo.setNombreCancha(nombre);
                        new fecha();
                        dispose();
                }
            }
        });

        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });

    }

}
