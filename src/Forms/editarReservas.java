package Forms;

import Clases.Logeo;
import Clases.Reservas;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase para editar reservas.
 * Esta clase proporciona una interfaz gráfica para editar reservas,
 * permitiendo al usuario cambiar la fecha y el horario de una reserva.
 */
public class editarReservas extends JFrame {
    private JButton fecBtn;
    private JButton horJBtn;
    private JLabel ver;
    private JLabel fecTxt;
    private JLabel horTxt;
    private JComboBox hor;
    private JDateChooser fecha;
    private JButton actBtn;
    private JButton elegBtn;
    private JButton regBtn;
    private JPanel pane;
    private JLabel ver2;
    Reservas res = new Reservas();

    /**
     * Constructor de la clase editarReservas.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public editarReservas() {
        // Configuración de la ventana
        setTitle("Editar Reservas");
        setContentPane(pane);
        setPreferredSize(new Dimension(600,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        // Mostrar el número de la reserva que se está editando
        ver.setText("Editando la Reserva #" + Logeo.getNumReserva());
        // Inicializar los campos
        inicializar();

        // Acción para el botón "Fecha"
        fecBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar los campos de fecha
                mostrarCampos("fecReservada");
            }
        });

        // Acción para el botón "Horario"
        horJBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar los campos de horario
                mostrarCampos("horReservada");
            }
        });

        // Acción para el botón "Actualizar"
        actBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si los campos son válidos
                if (validarCampos()){
                    // Capturar la entrada del usuario
                    capturarInput(res);
                    // Actualizar la reserva
                    res.actualizarRegistro(ver2);
                }
            }
        });

        // Acción para el botón "Elegir"
        elegBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Inicializar los campos
                inicializar();
                // Limpiar el campo de texto
                ver2.setText("");
            }
        });

        // Acción para el botón "Regresar"
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Regresar a la ventana de gestión de reservas
                new gestionReservas();
                dispose();
            }
        });
    }

    /**
     * Muestra los campos de fecha y horario en la interfaz gráfica al aplastar los respectivos botones.
     * @param campo Campo a mostrar.
     */
    private void mostrarCampos(String campo){
        // Ocultar los botones de fecha y horario
        fecBtn.setVisible(false);
        horJBtn.setVisible(false);
        // Mostrar los campos de texto, el textField para ingreso de datos y los botones de actualizar y elegir
        switch (campo){
            case "fecReservada":
                fecTxt.setVisible(true);
                fecha.setVisible(true);
                break;
            case "horReservada":
                horTxt.setVisible(true);
                hor.setVisible(true);
                break;
            }
        res.setBtn(campo);
        actBtn.setVisible(true);
        elegBtn.setVisible(true);
    }

    /**
     * Valida los campos que se ingresen correctamente la fecha y el horario.
     * Cuando cualquiera de los dos es visible
     * @return true si los campos son válidos, false si no.
     */
    private boolean validarCampos(){
        // Verificar si la fecha es visible
        if(fecha.isVisible()){
            // Se obtiene la fecha seleccionada
            Date fec = fecha.getDate();
            // Se obtiene la fecha actual
            Date fechaActual = new Date();
            // Verificar si se ha seleccionado una fecha
            if (fec!= null){
                // Verificar si la fecha seleccionada es anterior a la fecha actual
                if (fec.before(fechaActual)){
                    ver2.setText("La fecha seleccionada es anterior a la fecha actual");
                    return false;
                }
                // Obtener el día, mes y año de la fecha seleccionada
                Calendar cal = Calendar.getInstance();
                // .setTime(fec) establece la fecha del calendario como la fecha seleccionada
                cal.setTime(fec);
                //.get(Calendar.DAY_OF_MONTH) obtiene el día del mes
                res.setDia(cal.get(Calendar.DAY_OF_MONTH));
                // .get(Calendar.MONTH) obtiene el mes
                res.setMes(cal.get(Calendar.MONTH) + 1);
                // .get(Calendar.YEAR) obtiene el año
                res.setAnio(cal.get(Calendar.YEAR));
                // Se almacena la fecha seleccionada en el formato "dd/mm/aaaa"
                String fecSeleccionada = res.getDia()+"/"+res.getMes()+"/"+res.getAnio();
                // Se almacena la fecha seleccionada en Logeo
                if (!res.verificarDisponibilidad(fecSeleccionada, Logeo.getHorario())){
                    ver2.setText("La fecha y horario seleccionados ya están reservados");
                    return false;
                }
            }
            // Si no se ha seleccionado una fecha
            if (fec==null){
                ver2.setText("Seleccione una fecha");
                return false;
            }

        }
        //si el horario es visible
        if (hor.isVisible()){
            // Verificar si se ha seleccionado un horario
            if (hor.getSelectedIndex()==0){
                ver2.setText("Seleccione un horario");
                return false;
            }
            // Si se ha seleccionado un horario que ya se ha reservado
            if (!res.verificarDisponibilidad(Logeo.getFecha(), hor.getSelectedItem().toString())){
                ver2.setText("La fecha y horario seleccionados ya están reservados");
                return false;
            }
        }
        return true;
    }

    /**
     * Captura la entrada del usuario y la almacena en la reserva.
     * @param res Reserva a la que se le asignará la entrada.
     */
    private void capturarInput(Reservas res){
        // Capturar la entrada del usuario
        switch (res.getBtn()) {
            case "fecReservada":
                // Obtener la fecha seleccionada
                Date fechaSeleccionada = fecha.getDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(fechaSeleccionada);
                res.setDia(cal.get(Calendar.DAY_OF_MONTH));
                res.setMes(cal.get(Calendar.MONTH) + 1);
                res.setAnio(cal.get(Calendar.YEAR));
                // Almacenar la fecha seleccionada en el formato "dd/mm/aaaa"
                String nuevaFecha = res.getDia() + "/" + res.getMes() + "/" + res.getAnio();
                // Almacenar la fecha seleccionada en Logeo y en Input
                Logeo.setFecha(nuevaFecha);
                res.setInput(nuevaFecha);
                ver2.setText("La fecha ha sido actualizado con exito");
                break;
            case "horReservada":
                // Obtener el horario seleccionado del box de horarios
                String nuevoHorario = hor.getSelectedItem().toString();
                // Almacenar el horario seleccionado en Logeo y en Input
                Logeo.setHorario(nuevoHorario);
                res.setInput(nuevoHorario);
                ver2.setText("El horario ha sido actualizado con exito");
                break;
        }
    }

    /**
     * Inicializa los campos de fecha y horario.
     * Muestra los botones de fecha y horario y oculta los campos de texto y los botones de actualizar y elegir.
     */
    private void inicializar(){
        // Mostrar los botones de fecha y horario
        fecBtn.setVisible(true);
        horJBtn.setVisible(true);
        fecTxt.setVisible(false);
        horTxt.setVisible(false);
        hor.setVisible(false);
        fecha.setVisible(false);
        actBtn.setVisible(false);
        elegBtn.setVisible(false);
    }

    /**
     * Método principal de la clase editarReservas.
     * Crea un nuevo objeto de la clase editarReservas.
     */
    private void createUIComponents() {
        // Crear un nuevo objeto de la clase JDateChooser
        fecha = new JDateChooser();
    }
}
