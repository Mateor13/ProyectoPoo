package Forms;

import Clases.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

public class editarJugadores extends JFrame {
    private JPanel Pane;
    private JButton nomBtn;
    private JButton apeBtn;
    private JButton corBtn;
    private JButton fecBtn;
    private JButton telBtn;
    private JButton conBtn;
    private JTextField nom;
    private JTextField ape;
    private JTextField cor;
    private JTextField tel;
    private JPasswordField cont;
    private JPasswordField con;
    private JButton verBtn;
    private JButton regrBtn;
    private JButton elegBtn;
    private JButton actuBtn;
    private JLabel txt;
    private JDateChooser fec;
    private JLabel ver2;
    private JPasswordField confcla;
    private JLabel ver;
    Usuarios usu = new Usuarios();

    public editarJugadores() {
        setIconImage(new ImageIcon(getClass().getResource("../icono/User.png")).getImage());
        setTitle("Editar Jugadores");
        setContentPane(Pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        inicializar();
        ver.setText("Editando a: " + Logeo.getNombre());

        nomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("nombre");
            }
        });
        apeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("apellido");
            }
        });
        corBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("correo");
            }
        });
        fecBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("fechaNacimiento");
            }
        });
        telBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("celular");
            }
        });
        conBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("confclave");
            }
        });
        verBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cont.getPassword().length==0) {
                    ver2.setText("Ingrese la contraseña");
                } else if (usu.verClave(new String(cont.getPassword()), ver2)) {
                    mostrarCampos("clave");
                    con.setText("");
                    confcla.setText("");
                }
            }
        });
        actuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    if (usu.getBtn().equals("clave")){
                        capturarInput(usu);
                        usu.actualizarRegistro(ver2);
                        mostrarCampos("confclave");
                        cont.setText("");
                        ver2.setText("");
                        JOptionPane.showMessageDialog(null, "Contraseña actualizada");
                    }else {
                        capturarInput(usu);
                        usu.actualizarRegistro(ver2);
                    }
                }
            }
        });

        elegBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializar();
                ver2.setText("");
            }
        });
        regrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gestionJugadores();
                dispose();
            }
        });
    }

    private void mostrarCampos(String campo) {
        resetCampos();
        switch (campo) {
            case "nombre":
                txt.setText("Ingrese el nuevo nombre");
                nom.setVisible(true);
                break;
            case "apellido":
                txt.setText("Ingrese el nuevo apellido");
                ape.setVisible(true);
                break;
            case "correo":
                txt.setText("Ingrese el nuevo correo");
                cor.setVisible(true);
                break;
            case "celular":
                txt.setText("Ingrese el nuevo teléfono");
                tel.setVisible(true);
                break;
            case "fechaNacimiento":
                txt.setText("Ingrese la nueva fecha de nacimiento");
                fec.setVisible(true);
                break;
            case "confclave":
                txt.setText("Ingrese la contraseña actual");
                cont.setVisible(true);
                verBtn.setVisible(true);
                elegBtn.setVisible(true);
                txt.setVisible(true);
                ver2.setVisible(true);
                return; // No mostrar actuBtn en este caso
            case "clave":
                txt.setText("Ingrese la nueva contraseña");
                con.setVisible(true);
                confcla.setVisible(true);
                break;
        }
        txt.setVisible(true);
        actuBtn.setVisible(true);
        elegBtn.setVisible(true);
        usu.setBtn(campo);
        ver2.setVisible(true);
    }

    private boolean validarCampos() {
        if (nom.isVisible()){
            if (nom.getText().isEmpty()) {
                ver2.setText("Ingrese el nombre");
                return false;
            }
        }

        if (ape.isVisible()){
            if (ape.getText().isEmpty()) {
                ver2.setText("Ingrese el apellido");
                return false;
            }
        }

        if (fec.isVisible()) {
            Date fechaSeleccionada = fec.getDate();
            Date fechaActual = new Date();
            if (fechaSeleccionada != null && fechaSeleccionada.after(fechaActual)) {
                ver2.setText("La fecha de nacimiento no puede ser mayor a la actual");
                return false;
            }
        }

        if (cor.isVisible()) {
            if (cor.getText().isEmpty()) {
                ver2.setText("Ingrese el email");
                return false;
            }
            if (!usu.verCorreo2(cor.getText())) {
                ver2.setText("El email es incorrecto");
                return false;
            }
        }

        if (tel.isVisible()) {
            if (tel.getText().isEmpty()) {
                ver2.setText("Ingrese el número de teléfono");
                return false;
            }
            if (!usu.verNume2(tel.getText())) {
                ver2.setText("El número de teléfono es incorrecto");
                return false;
            }
        }

        if (con.isVisible()) {
            if (con.getPassword().length == 0) {
                ver2.setText("Ingrese la contraseña");
                return false;
            }
            if (confcla.getPassword().length == 0) {
                ver2.setText("Confirme la contraseña");
                return false;
            }

            if (!(new String(confcla.getPassword()).equals(new String(con.getPassword())))) {
                ver2.setText("Las contraseñas no coinciden");
                return false;
            }
        }
        return true;
    }

    private void capturarInput(Usuarios usu) {
        switch (usu.getBtn()) {
            case "nombre":
                usu.setInp(nom.getText());
                ver.setText("Editando a: " + usu.getInp());
                break;
            case "apellido":
                usu.setInp(ape.getText());
                break;
            case "correo":
                usu.setInp(cor.getText());
                break;
            case "celular":
                usu.setInp(tel.getText());
                break;
            case "fechaNacimiento":
                Date fechaSeleccionada = fec.getDate();
                if (fechaSeleccionada != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaSeleccionada);
                    usu.setDiaNacimiento(cal.get(Calendar.DAY_OF_MONTH));
                    usu.setMesNacimiento(cal.get(Calendar.MONTH) + 1);
                    usu.setAnoNacimiento(cal.get(Calendar.YEAR));
                }
                usu.setInp(usu.getDiaNacimiento() + "/" + usu.getMesNacimiento() + "/" + usu.getAnoNacimiento());
                break;
            case "clave":
                usu.setInp(usu.generateHash2(new String(con.getPassword())));
                break;
        }
    }

    private void resetCampos() {
        nomBtn.setVisible(false);
        apeBtn.setVisible(false);
        corBtn.setVisible(false);
        fecBtn.setVisible(false);
        telBtn.setVisible(false);
        conBtn.setVisible(false);
        nom.setVisible(false);
        ape.setVisible(false);
        cor.setVisible(false);
        tel.setVisible(false);
        fec.setVisible(false);
        cont.setVisible(false);
        con.setVisible(false);
        verBtn.setVisible(false);
        actuBtn.setVisible(false);
        confcla.setVisible(false);
        txt.setVisible(false);
        ver2.setVisible(false);
    }

    private void inicializar() {
        resetCampos();
        nomBtn.setVisible(true);
        apeBtn.setVisible(true);
        corBtn.setVisible(true);
        fecBtn.setVisible(true);
        telBtn.setVisible(true);
        conBtn.setVisible(true);
        ver2.setVisible(true);
    }

    private void createUIComponents() {
        fec = new JDateChooser();
    }
}
