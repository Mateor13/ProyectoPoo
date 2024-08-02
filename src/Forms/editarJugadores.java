package Forms;

import Clases.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que permite editar los datos de los jugadores.
 * Esta clase proporciona una interfaz gráfica para editar los jugadores,
 * permitiendo al usuario cambiar el nombre, apellido, correo, fecha de nacimiento, teléfono y contraseña.
 */
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
    // Se crea una instancia de la clase Usuarios.
    Usuarios usu = new Usuarios();

    /**
     * Inicializa la interfaz gráfica de editar jugadores.
     */
    public editarJugadores() {
        // Se establece el icono de la aplicación.
        setIconImage(new ImageIcon(getClass().getResource("../icono/User.png")).getImage());
        setTitle("Editar Jugadores");
        setContentPane(Pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        // Se inicializan los botones para seleccionar el campo a editar.
        inicializar();
        // Se establece el nombre del jugador que se está editando.
        ver.setText("Editando a: " + Logeo.getNombre());

        // Se añade un ActionListener al botón nombre
        nomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se muestra el campo para ingresar el nuevo nombre.
                mostrarCampos("nombre");
            }
        });

        // Se añade un ActionListener al botón apellido
        apeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se muestra el campo para ingresar el nuevo apellido.
                mostrarCampos("apellido");
            }
        });

        // Se añade un ActionListener al botón correo
        corBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se muestra el campo para ingresar el nuevo correo.
                mostrarCampos("correo");
            }
        });

        // Se añade un ActionListener al botón fecha de nacimiento
        fecBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se muestra el campo para ingresar la nueva fecha de nacimiento.
                mostrarCampos("fechaNacimiento");
            }
        });

        // Se añade un ActionListener al botón teléfono
        telBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se muestra el campo para ingresar el nuevo teléfono.
                mostrarCampos("celular");
            }
        });

        // Se añade un ActionListener al botón contraseña
        conBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se muestra el campo para ingresar la nueva contraseña.
                mostrarCampos("confclave");
            }
        });

        // Se añade un ActionListener al botón verificar
        verBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se verifica si se ingresó la contraseña.
                if (cont.getPassword().length==0) {
                    ver2.setText("Ingrese la contraseña");
                    // Se verifica si la contraseña es correcta.
                } else if (usu.verClave(new String(cont.getPassword()), ver2)) {
                    // Se muestra el campo para ingresar la nueva contraseña.
                    mostrarCampos("clave");
                    con.setText("");
                    confcla.setText("");
                }
            }
        });

        // Se añade un ActionListener al botón actualizar
        actuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se verifica si los campos son válidos.
                if (validarCampos()) {
                    // Se verifica si se seleccionó el campo de contraseña.
                    if (usu.getBtn().equals("clave")){
                        // Se toma la contraseña dada
                        capturarInput(usu);
                        // Se actualiza el registro del jugador.
                        usu.actualizarRegistro(ver2);
                        // Se muestra el campo para ingresar la nueva contraseña actual, una vez ingresada la nueva contraseña.
                        mostrarCampos("confclave");
                        cont.setText("");
                        ver2.setText("");
                        // Se muestra un mensaje de confirmación del cambio de contraseña.
                        JOptionPane.showMessageDialog(null, "Contraseña actualizada");
                    }else {
                        // Se captura el input del usuario de los demás atributos.
                        capturarInput(usu);
                        // Se actualiza el registro del jugador.
                        usu.actualizarRegistro(ver2);
                    }
                }
            }
        });

        // Se añade un ActionListener al botón elegir
        elegBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se muestra el campo para elegir las opciones de actualización de datos.
                inicializar();
                ver2.setText("");
            }
        });

        // Se añade un ActionListener al botón regresar
        regrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se regresa a la gestión de jugadores.
                new gestionJugadores();
                dispose();
            }
        });
    }

    /**
     * Muestra los campos para editar los datos del jugador.
     * @param campo Campo a editar.
     */
    private void mostrarCampos(String campo) {
        // Se ponen todos los valores con la visibilidad en falso.
        resetearCampos();
        // Se establece el texto del campo a editar.
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
                // Se muestra el campo para ingresar la contraseña actual.
                txt.setText("Ingrese la contraseña actual");
                cont.setVisible(true);
                verBtn.setVisible(true);
                elegBtn.setVisible(true);
                txt.setVisible(true);
                ver2.setVisible(true);
                return; // No mostrar actuBtn en este caso
            case "clave":
                // Se muestra el campo para ingresar la nueva contraseña.
                txt.setText("Ingrese la nueva contraseña");
                con.setVisible(true);
                confcla.setVisible(true);
                break;
        }
        // Se muestra el botón de actualizar y elegir otros campos a cambiar.
        txt.setVisible(true);
        actuBtn.setVisible(true);
        elegBtn.setVisible(true);
        // Se establece el campo a editar.
        usu.setBtn(campo);
        ver2.setVisible(true);
    }

    /**
     * Valida los campos ingresados por el usuario.
     * @return true si los campos son válidos, false si no lo son.
     */
    private boolean validarCampos() {
        //Si el campo nombre es visible
        if (nom.isVisible()){
            //Si el campo nombre está vacío
            if (nom.getText().isEmpty()) {
                ver2.setText("Ingrese el nombre");
                return false;
            }
        }

        //Si el campo apellido es visible
        if (ape.isVisible()){
            //Si el campo apellido está vacío
            if (ape.getText().isEmpty()) {
                ver2.setText("Ingrese el apellido");
                return false;
            }
        }

        //Si el campo fecha de nacimiento es visible
        if (fec.isVisible()) {
            //Se obtiene la fecha seleccionada
            Date fechaSeleccionada = fec.getDate();
            Date fechaActual = new Date();
            //Si la fecha seleccionada es después de la fecha actual
            if (fechaSeleccionada != null && fechaSeleccionada.after(fechaActual)) {
                ver2.setText("La fecha de nacimiento no puede ser mayor a la actual");
                return false;
            }
            //Si no se ha seleccionado una fecha
            if (fechaSeleccionada == null) {
                ver2.setText("Ingrese la fecha de nacimiento");
                return false;
            }
        }

        //Si el campo correo es visible
        if (cor.isVisible()) {
            //Si el campo correo está vacío
            if (cor.getText().isEmpty()) {
                ver2.setText("Ingrese el email");
                return false;
            }
            //Si el correo no es válido
            if (!usu.verCorreo2(cor.getText())) {
                ver2.setText("El email es incorrecto");
                return false;
            }
        }

        //Si el campo teléfono es visible
        if (tel.isVisible()) {
            //Si el campo teléfono está vacío
            if (tel.getText().isEmpty()) {
                ver2.setText("Ingrese el número de teléfono");
                return false;
            }
            //Si el teléfono no es válido
            if (!usu.verNume2(tel.getText())) {
                ver2.setText("El número de teléfono es incorrecto");
                return false;
            }
        }

        //Si el campo contraseña es visible
        if (con.isVisible()) {
            // Si el campo contraseña está vacío
            if (con.getPassword().length == 0) {
                ver2.setText("Ingrese la contraseña");
                return false;
            }
            // Si el campo confirmar contraseña está vacío
            if (confcla.getPassword().length == 0) {
                ver2.setText("Confirme la contraseña");
                return false;
            }

            // Si las contraseñas no coinciden
            if (!(new String(confcla.getPassword()).equals(new String(con.getPassword())))) {
                ver2.setText("Las contraseñas no coinciden");
                return false;
            }
        }
        return true;
    }

    /**
     * Captura el input del usuario.
     * @param usu Clase de donde se van a tomar los métodos y se van a enviar los datos.
     */
    private void capturarInput(Usuarios usu) {
        // Se toma el campo a editar.
        // Se establece el valor del campo a editar.
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
                // Se obtiene la fecha seleccionada.
                Date fechaSeleccionada = fec.getDate();
                if (fechaSeleccionada != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaSeleccionada);
                    usu.setDiaNacimiento(cal.get(Calendar.DAY_OF_MONTH));
                    usu.setMesNacimiento(cal.get(Calendar.MONTH) + 1);
                    usu.setAnoNacimiento(cal.get(Calendar.YEAR));
                }
                // Se establece el valor de la fecha de nacimiento como (dd/mm/aaaa).
                usu.setInp(usu.getDiaNacimiento() + "/" + usu.getMesNacimiento() + "/" + usu.getAnoNacimiento());
                break;
            case "clave":
                // Se establece el valor de la contraseña.
                usu.setInp(usu.generateHash2(new String(con.getPassword())));
                break;
        }
    }

    /**
     * Pone todos los campos con la visibilidad en falso.
     */
    private void resetearCampos() {
        // Se ponen todos los campos con la visibilidad en falso.
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
        elegBtn.setVisible(false);
        txt.setVisible(false);
        ver2.setVisible(false);
    }

    /**
     * Inicializa los botones para seleccionar el campo a editar.
     */
    private void inicializar() {
        // Se inicializan los campos a editar y lo demás se pone en falso.
        resetearCampos();
        nomBtn.setVisible(true);
        apeBtn.setVisible(true);
        corBtn.setVisible(true);
        fecBtn.setVisible(true);
        telBtn.setVisible(true);
        conBtn.setVisible(true);
    }

    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    private void createUIComponents() {
        // Se inicializa el campo de fecha de nacimiento.
        fec = new JDateChooser();
    }
}
