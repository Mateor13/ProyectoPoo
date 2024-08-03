package Forms;

import Clases.Logeo;
import Clases.cancha;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Clase que permite editar las canchas.
 * Esta clase proporciona una interfaz gráfica para editar las canchas,
 * permitiendo al usuario cambiar el nombre, la dirección y el número de jugadores.
 *
 * @extends JFrame definir sin definir el Frame
 */
public class editarCanchas extends JFrame {
    private JButton actualizarBtn;
    private JButton nomBtn;
    private JButton numJBtn;
    private JButton regresarBtn;
    private JButton dirBtn;
    private JTextField nomb;
    private JTextField dire;
    private JComboBox numJug;
    private JPanel edCancha;
    private JLabel nomTxt;
    private JLabel dirTxt;
    private JLabel nTxt;
    private JLabel ver;
    private JLabel ver2;
    private JButton elegirBtn;

    // Se crea una instancia de la clase cancha.
    cancha canc = new cancha();

    /**
     * Constructor de la clase editarCanchas.
     */
    public editarCanchas() {
        // Se establece el icono de la aplicación.
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/cancha.png"))).getImage());
        setTitle("Editar Canchas");
        setContentPane(edCancha);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Se inicializa la interfaz gráfica.
        mostrarOpciones();

        // Se establece el nombre de la cancha que se está editando
        ver.setText("Editando Cancha: " + Logeo.getNombreCancha());

        // Se establece la acción del botón de nombre.
        nomBtn.addActionListener(_ -> {
            // Se muestra el campo para editar el nombre.
            mostrarCampos("nombre");
        });

        // Se establece la acción del botón de dirección.
        dirBtn.addActionListener(_ -> {
            // Se muestra el campo para editar la dirección.
            mostrarCampos("direccion");
        });

        // Se establece la acción del botón de número de jugadores.
        numJBtn.addActionListener(_ -> {
            // Se muestra el campo para editar el número de jugadores.
            mostrarCampos("numJugadores");
        });

        // Se establece la acción del botón de elegir.
        elegirBtn.addActionListener(_ -> {
            // Se muestra el campo para elegir las opciones de actualizacion de datos.
            mostrarOpciones();
        });

        // Se establece la acción del botón de actualizar.
        actualizarBtn.addActionListener(_ -> {
            // Se captura el input del usuario.
            capturarInput(canc);
            // Se actualiza el registro de la cancha.
            canc.actualizarRegistro(ver2);
        });

        // Se establece la acción del botón de regresar.
        regresarBtn.addActionListener(_ -> {
            // Se regresa a la gestión de canchas.
            new gestionCanchas();
            dispose();
        });
    }

    /**
     * Muestra las opciones de edición.
     */
    private void mostrarOpciones() {
        // Se muestran los botones para seleccionar las opciones de edición.
        nomBtn.setVisible(true);
        dirBtn.setVisible(true);
        numJBtn.setVisible(true);
        nomTxt.setVisible(false);
        nomb.setVisible(false);
        dirTxt.setVisible(false);
        dire.setVisible(false);
        nTxt.setVisible(false);
        numJug.setVisible(false);
        elegirBtn.setVisible(false);
        actualizarBtn.setVisible(false);
        ver2.setVisible(false);
        canc.setBtn(null);
        canc.setInput(null);
    }

    /**
     * Muestra los campos de edición.
     * @param campo Campo a editar.
     */
    private void mostrarCampos(String campo) {
        // Se ocultan los campos de edición.
        nomBtn.setVisible(false);
        dirBtn.setVisible(false);
        numJBtn.setVisible(false);
        // Se muestra el campo a editar.
        switch (campo) {
            case "nombre":
                nomTxt.setVisible(true);
                nomb.setVisible(true);
                break;
            case "direccion":
                dirTxt.setVisible(true);
                dire.setVisible(true);
                break;
            case "numJugadores":
                nTxt.setVisible(true);
                numJug.setVisible(true);
                break;
        }
        // Se muestran los botones de elegir y actualizar.
        elegirBtn.setVisible(true);
        actualizarBtn.setVisible(true);
        canc.setBtn(campo);
    }

    /**
     * Captura el input del usuario.
     * @param canc Cancha a editar.
     */
    private void capturarInput(cancha canc) {
        // Se determina el campo a editar.
        switch (canc.getBtn()) {
            case "nombre":
                canc.setInput(nomb.getText());
                ver.setText("editando la cancha: " + canc.getInput());
                break;
            case "direccion":
                canc.setInput(dire.getText());
                break;
            case "numJugadores":
                // Se verifica que se haya seleccionado un número de jugadores.
                if (numJug.getSelectedIndex() == 0) {
                    ver2.setText("Elija el número de jugadores");
                } else {
                    // Se captura el número de jugadores.
                    canc.setInput(Objects.requireNonNull(numJug.getSelectedItem()).toString());
                }
                break;
        }
    }
}

