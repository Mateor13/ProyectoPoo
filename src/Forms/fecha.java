package Forms;
import Clases.Logeo;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;

public class fecha extends JFrame {
    private JPanel pane;
    private com.toedter.calendar.JDateChooser fec;
    private JButton buscarButton;
    private JLabel ver;
    private JButton regresarButton;

    public fecha() {
        setTitle("Fecha");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fec.getDate() != null) {
                    Date fecha1 = fec.getDate();
                    if (fecha1.before(new Date())){
                        ver.setText("Seleccione una fecha válida");
                    }else{
                        ver.setText("");
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(fecha1);
                        int dia = cal.get(Calendar.DAY_OF_MONTH);
                        int mes = cal.get(Calendar.MONTH) + 1;
                        int ano = cal.get(Calendar.YEAR);
                        Logeo.setFecha(dia + "/" + mes + "/" + ano);
                        System.out.println(Logeo.getFecha());
                    }
                } else {
                    ver.setText("Seleccione una fecha");
                }
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new inicio();
                dispose();
            }
        });
    }

    private void createUIComponents() {
        fec = new JDateChooser();
    }
}
