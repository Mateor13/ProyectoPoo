package Forms;
import Clases.cancha;
import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clase para agregar canchas.
 * Esta clase proporciona una interfaz gráfica para agregar canchas,
 * permitiendo al usuario ingresar el número, nombre, dirección y número de jugadores de una cancha.
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

    /**
     * Constructor de la clase adingrCan.
     * Configura la interfaz gráfica y establece los escuchadores de eventos para los botones.
     */
    public adingrCan() {
        // Configuración de la ventana
        setIconImage(new ImageIcon(getClass().getResource("../icono/cancha.png")).getImage());
        setTitle("Ingresar Cancha");
        setContentPane(regCan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Acción para el botón "Registrar"
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear objeto de la clase cancha
                cancha canc = new cancha();
                // Obtener los datos ingresados por el usuario
                canc.setNumero(num.getText());
                canc.setNombre(nom.getText());
                canc.setUbicacion(dir.getText());
                if (numJug.getSelectedIndex() == 0){
                    // Verificar si se ha seleccionado un número de jugadores
                    ver.setText("Elija el número de jugadores");
                }else{
                    // Obtener el número de jugadores seleccionado
                    canc.setNumeroJugadores(numJug.getSelectedItem().toString());
                }
                // Verificar si se han ingresado todos los campos
                canc.setNumeroJugadores(numJug.getSelectedItem().toString());
                if (canc.getNumero().isEmpty()||canc.getNombre().isEmpty()||canc.getUbicacion().isEmpty()||canc.getNumeroJugadores().isEmpty()){
                    ver.setText("Ingrese todos los campos");
                    // Verificar si el número de canchas solo contiene 5 dígitos
                }else if (!canc.vernumCan()){
                    ver.setText("El número de canchas solo puede contener 5 digitos");
                    // Verificar si el número de jugadores solo contiene 2 dígitos
                }else{
                    // Conexión a la base de datos
                    try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
                        MongoDatabase db = mongo.getDatabase("futbolito");
                        MongoCollection<Document> col = db.getCollection("Canchas");
                        // Crear un documento con los datos de la cancha
                        Document doc = new Document("id", canc.getNumero());
                        FindIterable<Document> iterable = col.find(doc);
                        // Verificar si la cancha ya existe
                        boolean numC = false;
                        for (Document document : iterable) {
                            String num = document.getString("id");
                            // Mostrar mensaje si la cancha ya existe
                            if (canc.getNumero().equals(num)){
                                ver.setText("Cancha ya existente");
                                numC = true;
                                break;
                            }
                        }
                        // Insertar la cancha en la base de datos
                        if (!numC){
                            // Crear un documento con los datos de la cancha
                            Document docu = new Document("id", canc.getNumero())
                                    .append("nombre", canc.getNombre())
                                    .append("direccion", canc.getUbicacion())
                                    .append("numJugadores", canc.getNumeroJugadores());
                            // Insertar el documento en la colección "Canchas"
                            col.insertOne(docu);
                            ver.setText("Cancha registrada");
                        }
                        // Mostrar mensaje si ocurre un error
                    } catch (Exception ex){
                        ver.setText("Error al registrar cancha"+ ex.getMessage());
                    }
                }
            }
        });

        // Acción para el botón "Regresar"
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y abrir la ventana de gestión de canchas
                new gestionCanchas();
                dispose();
            }
        });
    }
}
