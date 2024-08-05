package Forms;
import Clases.Usuarios;
import Clases.Logeo;
import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Clase que crea el formulario de inicio de sesión.
 * Esta clase proporciona una interfaz gráfica para el inicio de sesión,
 * permitiendo al usuario ingresar su correo y contraseña para acceder a la aplicación.
 *
 * @extends JFrame sin definir el frame.
 */
public class login extends JFrame {
    // Componentes del formulario
    private JTextField correo;
    private JPasswordField clave;
    private JButton ingresarButton;
    private JLabel Registro;
    private JPanel log;
    private JLabel ver;
    private JButton volverAlMenúDeButton;

    /**
     * Constructor de la clase login.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public login() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Inicio de sesión");
        setContentPane(log);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 450));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Acción del botón "Ingresar"
        ingresarButton.addActionListener(_ -> {
            // Limpiar el mensaje de verificación
            ver.setText("");
            // Crear un objeto de la clase Usuarios
            Usuarios usuario = new Usuarios();
            // Obtener el correo y la contraseña ingresados por el usuario
            //.trim() elimina los espacios en blanco al principio y al final de la cadena
            usuario.setEmail(correo.getText().trim());
            // Obtener la contraseña como un arreglo de caracteres y convertirla a una cadena
            usuario.setClave(new String(clave.getPassword()));
            // Encriptar la contraseña
            usuario.setEncripclave(usuario.generateHash());
            // Verificar si el correo y la contraseña están vacíos
            if (usuario.getEmail().isEmpty() || usuario.getClave().isEmpty()) {
                // Mostrar mensaje de error
                ver.setText("Hay datos vacíos. Ingrese todos los campos");
            // Verificar si el correo y la contraseña son iguales a "Admin1234"
            } else if (usuario.getEmail().equals("Admin1234") && usuario.getClave().equals("Admin1234")) {
                // Mostrar mensaje de verificación exitosa
                ver.setForeground(Color.white);
                ver.setText("Verificación exitosa");
                // Cerrar el formulario actual y abrir el formulario de administrador
                new admin();
                dispose();
                // Verificar si el correo y la contraseña son iguales a "Duenio1234"
            } else if (usuario.getEmail().equals("Duenio1234") && usuario.getClave().equals("Duenio1234")) {
                // Mostrar mensaje de verificación exitosa
                ver.setForeground(Color.white);
                ver.setText("Verificación exitosa");
                // Cerrar el formulario actual y abrir el formulario de dueño
                new duenio();
                dispose();
            } else {
                // Conexión a la base de datos
                try (MongoClient moncli = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                    MongoDatabase db = moncli.getDatabase("futbolito");
                    MongoCollection<Document> col = db.getCollection("Usuarios");
                    // Crear un documento con el correo ingresado por el usuario
                    Document doc = new Document("correo", usuario.getEmail());
                    // Buscar el documento en la colección
                    FindIterable<Document> iterable = col.find(doc);
                    // Crear una variable para verificar si las credenciales son correctas
                    boolean credencialesCorrectas = false;

                    for (Document document : iterable) {
                        // Obtener el correo y la contraseña de la base de datos
                        String correo1 = document.getString("correo");
                        String clave1 = document.getString("clave");
                        String nombre = document.getString("nombre");
                        String cedula = document.getString("cedula");
                        // Verificar si el correo y la contraseña son iguales a los de la base de datos
                        if (usuario.getEmail().equals(correo1) && usuario.getEncripclave().equals(clave1)) {
                            // Establecer el nombre y la cédula del usuario en la clase Logeo
                            Logeo.setNombre(nombre);
                            Logeo.setCedula(cedula);
                            // Establecer las credenciales como correctas
                            credencialesCorrectas = true;
                            break;
                        }
                    }

                    // Verificar si las credenciales son correctas
                    if (credencialesCorrectas) {
                        // Mostrar mensaje de verificación exitosa
                        ver.setForeground(Color.white);
                        ver.setText("Verificación exitosa");
                        // Cerrar el formulario actual y abrir el formulario de inicio
                        new inicio();
                        dispose();
                    } else {
                        // Mostrar mensaje de error
                        ver.setText("Credenciales incorrectas");
                    }

                }
            }

        });

        // Acción del botón "Registro"
        Registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Abrir el formulario de registro y cerrar el formulario actual
                super.mouseClicked(e);
                new Registro();
                dispose();
            }
        });

        // Cambiar el color del texto al pasar el mouse sobre el texto "Registro"
        Registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                // Cambiar el color del texto a azul
                Registro.setForeground(Color.blue);
            }
        });

        // Cambiar el color del texto al salir el mouse del texto "Registro"
        Registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                // Cambiar el color del texto a cian
                Registro.setForeground(Color.cyan);
            }
        });
        volverAlMenúDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuInicio();
                dispose();
            }
        });
    }
}
