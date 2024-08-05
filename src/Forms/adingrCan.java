package Forms;
import Clases.cancha;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.Binary;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Clase para agregar canchas.
 * Esta clase proporciona una interfaz gráfica para agregar canchas,
 * permitiendo al usuario ingresar el número, nombre, dirección y número de jugadores de una cancha.
 * @extends JFrame definir sin definir el Frame
 */
public class adingrCan extends JFrame {
    private JTextField num;
    private JTextField nom;
    private JTextField dir;
    private JComboBox numJug;
    private JButton registrarBtn;
    private JButton regresarBtn;
    private JPanel regCan;
    private JLabel ver;
    private JLabel img;
    private JButton elegBtn;
    // Variable global para almacenar los bytes de la imagen
    private byte[] imageBytes;

    /**
     * Constructor de la clase adingrCan.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public adingrCan() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/Admin.jpg"))).getImage());
        setTitle("Ingresar Cancha");
        setContentPane(regCan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        img.setVisible(false);

        // Crear objeto de la clase cancha
        cancha canc = new cancha();

        // Acción para el botón "Elegir Imagen"
        elegBtn.addActionListener(_ -> {
            // Crear un selector de archivos
            JFileChooser fileChooser = new JFileChooser();
            // Configurar el selector de archivos
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // Configurar el filtro de archivos para imágenes
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagen", "jpg", "jpeg", "png"));
            // Mostrar el selector de archivos y esperar a que el usuario seleccione un archivo
            fileChooser.accept(fileChooser.getSelectedFile());
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (FileInputStream foto = new FileInputStream(selectedFile)) {
                    // Leer la imagen como un array de bytes
                    imageBytes = foto.readAllBytes();

                    // Mostrar la imagen en el JLabel con tamaño 100x100
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    img.setIcon(new ImageIcon(image));
                    img.setVisible(true);

                    ver.setText("Imagen subida con éxito");
                } catch (IOException e) {
                    ver.setText("Error al subir imagen: " + e.getMessage());
                }
            }
        });

// Acción para el botón "Registrar"
        registrarBtn.addActionListener(_ -> {
            // Obtener los datos ingresados por el usuario
            canc.setNumero(num.getText());
            canc.setNombre(nom.getText());
            canc.setUbicacion(dir.getText());


            // Verificar si se han ingresado todos los campos
            if (canc.getNumero().isEmpty() || canc.getNombre().isEmpty() || canc.getUbicacion().isEmpty() || numJug.getSelectedIndex() == 0) {
                ver.setText("Ingrese todos los campos");
            } else if (!canc.vernumCan()) {
                // Verificar si el número de canchas solo contiene 5 dígitos
                ver.setText("El número de canchas solo puede contener 5 dígitos");
            }else if (imageBytes == null) {
                // Verificar si se ha subido una imagen
                ver.setText("Suba una imagen de la cancha");
            } else if (canc.canExistente()) {
                ver.setText("Número de cancha ya registrado");
            } else {
                canc.setNumeroJugadores(Objects.requireNonNull(numJug.getSelectedItem()).toString());
                // Conexión a la base de datos
                try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                    MongoDatabase db = mongo.getDatabase("futbolito");
                    MongoCollection<Document> col = db.getCollection("Canchas");

                    // Crear documento con los datos de la cancha
                    Document canchaDoc = new Document("id", canc.getNumero())
                            .append("nombre", canc.getNombre())
                            .append("direccion", canc.getUbicacion())
                            .append("numJugadores", canc.getNumeroJugadores());

                    // Agregar la imagen si está disponible
                    if (imageBytes != null) {
                        canchaDoc.append("imagen", new Binary(imageBytes));
                    }

                    // Insertar el documento en la colección
                    col.insertOne(canchaDoc);

                    ver.setText("Cancha registrada con éxito");
                } catch (Exception e) {
                    ver.setText("Error al registrar cancha: " + e.getMessage());
                }
            }
        });


        // Acción para el botón "Regresar"
        regresarBtn.addActionListener(_ -> {
            // Cerrar la ventana actual y abrir la ventana de gestión de canchas
            new gestionCanchas();
            dispose();
        });
    }
}
