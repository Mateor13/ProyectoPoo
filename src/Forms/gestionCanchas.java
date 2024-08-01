package Forms;
import Clases.Logeo;
import Clases.cancha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gestionCanchas extends JFrame{
    private JPanel gescan;
    private JTable tablaCanchas;
    private JButton ingresarBtn;
    private JButton editarBtn;
    private JButton elimBtn;
    private JButton regresarBtn;
    private JLabel ver;

    public gestionCanchas (){
        setIconImage(new ImageIcon(getClass().getResource("../icono/cancha.png")).getImage());
        setTitle("Gestión de Canchas");
        setContentPane(gescan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,400));
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        cancha can = new cancha();
        can.mostrarCanchas(tablaCanchas);

        ingresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new adingrCan();
                dispose();
            }
        });
        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            new editarCanchas();
            dispose();
            }
        });
        elimBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tablaCanchas.getModel();
                if(!can.seleccionarRegitro(tablaCanchas)){
                    ver.setText("Seleccione una Cancha");
                }else{
                    ver.setText("");
                    String codigo = model.getValueAt(tablaCanchas.getSelectedRow(), 0).toString();
                    String nombre1 = model.getValueAt(tablaCanchas.getSelectedRow(), 1).toString();
                    Logeo.setCodigo(codigo);
                    Logeo.setNombreCancha(nombre1);
                    new elimCancha();
                    dispose();
                }
            }
        });
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new admin();
                dispose();
            }
        });
    }
}
