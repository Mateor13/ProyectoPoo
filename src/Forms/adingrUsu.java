package Forms;

import Clases.Usuarios;
import com.mongodb.client.*;
import com.toedter.calendar.JDateChooser;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase que permite insertar un usuario en la base de datos
 * Esta clase proporciona una interfaz gráfica para insertar un usuario en la base de datos,
 * permitiendo al usuario ingresar el nombre, apellido, cédula, teléfono, email, fecha de nacimiento y contraseña de un usuario.
 */
public class adingrUsu extends JFrame{
    private JLabel ver;
    private JTextField nombre;
    private JTextField apellido;
    private JTextField ci;
    private JTextField celular;
    private JTextField email;
    private JButton cancelarButton;
    private JButton registrarButton;
    private JTextField clave;
    private JTextField claveconf;
    private JDateChooser fecha;
    private JPanel Pane;

    /**
     * Constructor de la clase adingrUsu.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public adingrUsu() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(getClass().getResource("../icono/User.png")).getImage());
        setTitle("Insertar Usuario");
        setContentPane(Pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 700));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Acción para el botón "Registrar"
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear objeto de la clase Usuarios
                Usuarios us = new Usuarios();
                // Obtener los datos ingresados por el usuario
                us.setNombre(nombre.getText());
                us.setApellido(apellido.getText());
                us.setEmail(email.getText().trim()); //.trim() omite los espacios al inicio o al final
                us.setCedula(ci.getText());
                us.setTelefono(celular.getText());

                // Obtener la fecha de nacimiento seleccionada
                Date fechaSeleccionada = fecha.getDate();
                // Verificar si se ha seleccionado una fecha
                if (fechaSeleccionada != null) {
                    // Obtener el día, mes y año de la fecha seleccionada
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaSeleccionada);
                    us.setDiaNacimiento(cal.get(Calendar.DAY_OF_MONTH));
                    us.setMesNacimiento(cal.get(Calendar.MONTH) + 1); // Los meses empiezan desde 0, por ello se debe sumar 1
                    us.setAnoNacimiento(cal.get(Calendar.YEAR));
                }
                    // Obtener las contraseñas ingresadas por el usuario
                us.setClave(clave.getText());
                us.setClaveconf(claveconf.getText());

                // Verificar si se han ingresado todos los campos
                if (us.getNombre().isEmpty() || us.getApellido().isEmpty() || us.getEmail().isEmpty() || us.getCedula().isEmpty() || us.getTelefono().isEmpty() || fechaSeleccionada == null || us.getClave().isEmpty()) {
                    ver.setText("Hay campos vacíos. Ingrese todos los campos");
                    // Verificar si las contraseñas no están iguales
                } else if (!us.getClave().equals(us.getClaveconf())) {
                    ver.setText("Las contraseñas no están iguales");
                    // Verificar si la cédula o el teléfono no son válidos
                } else if (!us.verNume() || !us.verCedula()) {
                    ver.setText("La cédula o el teléfono son incorrectos");
                    // Verificar si la fecha de nacimiento es mayor a la actual
                } else if (fechaSeleccionada.after(new Date())) {
                    ver.setText("La fecha de nacimiento no puede ser mayor a la actual");
                    // Verificar si el email no es válido
                }else if (!us.verCorreo()){
                    ver.setText("El email es incorrecto");
                } else {
                    try (MongoClient moncli = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                        MongoDatabase db = moncli.getDatabase("futbolito");
                        MongoCollection<Document> col = db.getCollection("Usuarios");
                        // Crear un documento con los datos del usuario
                        Document doc = new Document("cedula", us.getCedula());
                        // Buscar si el usuario ya existe
                        FindIterable<Document> iterable = col.find(doc);
                        // Crear una variable para verificar si el usuario ya existe
                        boolean userExists = false;
                        // Verificar si el usuario quiere registrarse con las credenciales del administrador o del dueño
                        if (us.getEmail().equals("Admin1234") || us.getEmail().equals("Duenio1234")) {
                            ver.setText("Usuario existente");
                            // Cambiar el valor de la variable para indicar que el usuario ya existe
                            userExists = true;
                        } else {
                            // Recorrer los documentos encontrados
                            for (Document documents : iterable) {
                                // Obtener la cédula y el email del documento
                                String cedula = documents.getString("cedula");
                                String email = documents.getString("email");
                                // Verificar si la cédula o el email ya existen
                                if (us.getCedula().equals(cedula) || us.getEmail().equals(email)) {
                                    ver.setText("Usuario existente");
                                    userExists = true;
                                    break;
                                }
                            }
                        }
                        // Insertar el usuario en la base de datos
                        if (!userExists) {
                            // Crear un documento con los datos del usuario
                            Document docu = new Document("nombre", us.getNombre())
                                    .append("apellido", us.getApellido())
                                    .append("cedula", us.getCedula())
                                    .append("correo", us.getEmail())
                                    .append("celular", us.getTelefono())
                                    .append("fechaNacimiento", us.getDiaNacimiento()+"/"+us.getMesNacimiento()+"/"+us.getAnoNacimiento())
                                    .append("clave", us.generateHash());
                            // Insertar el documento en la colección "Usuarios"
                            col.insertOne(docu);
                            ver.setText("Usuario registrado");
                        }
                        // Mostrar mensaje si ocurre un error
                    } catch (Exception ex) {
                        ver.setText("Error al conectar con la base de datos");
                        // Imprimir el mensaje de error en la consola
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Acción para el botón "Cancelar"
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y abrir la ventana de gestión de usuarios
                new gestionJugadores();
                dispose();
            }
        });
    }

    // Método principal de la clase adingrUsu
    private void createUIComponents() {
        // Crear un JDateChooser
        fecha = new JDateChooser();
    }
}

