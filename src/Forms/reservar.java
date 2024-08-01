package Forms;
import Clases.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class reservar extends JFrame {
    private JTable tabl;
    private JPanel pane;
    private JButton resBtn;
    private JButton regBtn;
    private JLabel ver;
    private JComboBox hor;

    public reservar() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Reservar");
        setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        Reservas res = new Reservas();
        actualizarTabla(res);

        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new inicio();
                dispose();
            }
        });
        resBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (res.seleccionarRegistro(hor)){
                    res.tomarValor(hor);
                    if (res.verificarHorario()){
                        res.reservarCancha(ver);
                        actualizarTabla(res);
                    } else {
                        ver.setText("Horario no disponible");
                    }
                } else {
                    ver.setText("Seleccione un horario");
                }
            }
        });
    }
    private void actualizarTabla(Reservas res) {
        res.mostrarReservas(tabl);
    }
}
