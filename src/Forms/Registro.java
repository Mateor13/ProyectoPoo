package Forms;

import Clases.Usuarios;
import com.mongodb.client.*;
import com.toedter.calendar.JDateChooser;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Clase que crea el formulario de registro de usuarios.
 * Esta clase permite a los usuarios registrarse en la aplicación.
 *
 * @extends JFrame sin definir el frame
 */
public class Registro extends JFrame {
    // Componentes del formulario
    private JTextField nombre;
    private JTextField apellido;
    private JTextField ci;
    private JTextField celular;
    private JTextField email;
    private JButton registrarButton;
    private JButton cancelarButton;
    private JPanel reg;
    private JTextField clave;
    private JLabel ver;
    private JTextField claveconf;
    private JDateChooser fecha;

    /**
     * Constructor de la clase Registro.
     * Inicializa los componentes del formulario y define las acciones de los botones.
     */
    public Registro() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/User.png"))).getImage());
        setTitle("Registro");
        setContentPane(reg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 700));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Accion del botón de "Registrar"
        registrarButton.addActionListener(_ -> {
            // Creación de un objeto de la clase Usuarios
            Usuarios us = new Usuarios();
            // Asignación de los valores de los campos a las propiedades del objeto
            us.setNombre(nombre.getText());
            us.setApellido(apellido.getText());
            //.trim() elimina los espacios al inicio y al final de la cadena
            us.setEmail(email.getText().trim());
            us.setCedula(ci.getText());
            us.setTelefono(celular.getText());

            // Obtener la fecha seleccionada en el JDateChooser
            Date fechaSeleccionada = fecha.getDate();
            // Si no se ha seleccionado una fecha, no se asigna nada
            if (fechaSeleccionada != null) {
                // Crear un objeto de tipo Calendar para obtener los valores de la fecha
                Calendar cal = Calendar.getInstance();
                // Asignar la fecha seleccionada al objeto Calendar
                cal.setTime(fechaSeleccionada);
                // Asignar los valores de la fecha a las propiedades del objeto Usuarios
                us.setDiaNacimiento(cal.get(Calendar.DAY_OF_MONTH));
                // Se suma 1 al mes porque en Java los meses empiezan en 0
                us.setMesNacimiento(cal.get(Calendar.MONTH) + 1);
                us.setAnoNacimiento(cal.get(Calendar.YEAR));
            }
            // Asignar las contraseñas a las propiedades del objeto Usuarios
            us.setClave(clave.getText());
            us.setClaveconf(claveconf.getText());

            // Si alguno de los campos está vacío, se muestra un mensaje de error
            if (us.getNombre().isEmpty() || us.getApellido().isEmpty() || us.getEmail().isEmpty() || us.getCedula().isEmpty() || us.getTelefono().isEmpty() || fechaSeleccionada == null || us.getClave().isEmpty()) {
                ver.setText("Hay campos vacíos. Ingrese todos los campos");
                // Si las contraseñas no coinciden, se muestra un mensaje de error
            } else if (!us.getClave().equals(us.getClaveconf())) {
                ver.setText("Las contraseñas no están iguales");
                // Si la cédula o el teléfono no son válidos, se muestra un mensaje de error
            } else if (us.verNume() || us.verCedula()) {
                ver.setText("La cédula o el teléfono son incorrectos");
                // Si la fecha seleccionada es mayor a la fecha actual, se muestra un mensaje de error
            } else if (fechaSeleccionada.after(new Date())) {
                ver.setText("La fecha de nacimiento no puede ser mayor a la actual");
                // Si el correo no es válido, se muestra un mensaje de error
            }else if (us.verCorreo()){
                ver.setText("El email es incorrecto");
            } else {
                // Conexión a la base de datos
                try (MongoClient moncli = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                    // Conexión a la base de datos "futbolito"
                    MongoDatabase db = moncli.getDatabase("futbolito");
                    // Conexión a la colección "Usuarios"
                    MongoCollection<Document> col = db.getCollection("Usuarios");
                    // Creación de un documento con la cédula del usuario
                    Document doc = new Document("cedula", us.getCedula());
                    // Búsqueda de un documento con la cédula del usuario
                    FindIterable<Document> iterable = col.find(doc);
                    //Creación de una variable para verificar si el usuario ya existe
                    boolean userExists = false;
                    // Si el usuario es "Admin1234" o "Duenio1234", se muestra un mensaje de error
                    if (us.getEmail().equals("Admin1234") || us.getEmail().equals("Duenio1234")) {
                        ver.setText("Usuario existente");
                        // Se asigna true a la variable para indicar que el usuario ya existe
                        userExists = true;
                    } else {
                        // Recorrer los documentos encontrados
                        for (Document documents : iterable) {
                            // Se obtiene la cédula y el correo de los documentos
                            String cedula = documents.getString("cedula");
                            String email = documents.getString("email");
                            // Si la cédula o el correo del usuario ya existen, se muestra un mensaje de error
                            if (us.getCedula().equals(cedula) || us.getEmail().equals(email)) {
                                ver.setText("Usuario existente");
                                // Se asigna true a la variable para indicar que el usuario ya existe
                                userExists = true;
                                break;
                            }
                        }
                    }

                    // Si el usuario no existe, se registra en la base de datos
                    if (!userExists) {
                        // Creación de un documento con los datos del usuario
                        Document docu = new Document("nombre", us.getNombre())
                                .append("apellido", us.getApellido())
                                .append("cedula", us.getCedula())
                                .append("correo", us.getEmail())
                                .append("celular", us.getTelefono())
                                .append("fechaNacimiento", us.getDiaNacimiento()+"/"+us.getMesNacimiento()+"/"+us.getAnoNacimiento())
                                .append("clave", us.generateHash());
                        // Inserción del documento en la colección "Usuarios"
                        col.insertOne(docu);
                        // Mensaje de éxito
                        ver.setText("Usuario registrado");
                    }
                // Captura de excepciones
                } catch (Exception ex) {
                    // Mensaje de error
                    ver.setText("Error al conectar con la base de datos"+ ex);
                }
            }
        });

        // Acción del botón de "Cancelar"
        cancelarButton.addActionListener(_ -> {
            // Cerrar el formulario actual y abrir el formulario de login
            new login();
            dispose();
        });
    }

    /**
     * Método para crear el JDateChooser.
     */
    private void createUIComponents() {
        // Crear el JDateChooser
        fecha = new JDateChooser();
    }
}
