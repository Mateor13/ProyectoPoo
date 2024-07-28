package Forms;
import Clases.cancha;
import com.mongodb.client.*;
import org.bson.Document;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adingrCan extends JFrame {
    private JTextField num;
    private JTextField nom;
    private JTextField dir;
    private JComboBox numJug;
    private JButton registrarBtn;
    private JButton regresarBtn;
    private JPanel regCan;
    private JLabel ver;

    public adingrCan() {
        setIconImage(new ImageIcon(getClass().getResource("../icono/cancha.png")).getImage());
        setTitle("Ingresar Cancha");
        setContentPane(regCan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancha canc = new cancha();
                canc.setNumero(num.getText());
                canc.setNombre(nom.getText());
                canc.setUbicacion(dir.getText());
                if (numJug.getSelectedIndex() == 0){
                    ver.setText("Elija el número de jugadores");
                }else{
                    canc.setNumeroJugadores(numJug.getSelectedItem().toString());
                }
                canc.setNumeroJugadores(numJug.getSelectedItem().toString());
                if (canc.getNumero().isEmpty()||canc.getNombre().isEmpty()||canc.getUbicacion().isEmpty()||canc.getNumeroJugadores().isEmpty()){
                    ver.setText("Ingrese todos los campos");
                }else if (!canc.vernumCan()){
                    ver.setText("El número de canchas solo puede contener 5 digitos");
                }else{
                    try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
                        MongoDatabase db = mongo.getDatabase("futbolito");
                        MongoCollection<Document> col = db.getCollection("Canchas");
                        Document doc = new Document("id", canc.getNumero());
                        FindIterable<Document> iterable = col.find(doc);

                        boolean numC = false;
                        for (Document document : iterable) {
                            String num = document.getString("id");
                            if (canc.getNumero().equals(num)){
                                ver.setText("Cancha ya existente");
                                numC = true;
                                break;
                            }
                        }
                        if (!numC){
                            Document docu = new Document("id", canc.getNumero())
                                    .append("nombre", canc.getNombre())
                                    .append("direccion", canc.getUbicacion())
                                    .append("numJugadores", canc.getNumeroJugadores());
                            col.insertOne(docu);
                            ver.setText("Cancha registrada");
                        }
                    } catch (Exception ex){
                        ver.setText("Error al registrar cancha"+ ex.getMessage());
                    }
                }
            }
        });
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gestionCanchas();
                dispose();
            }
        });
    }
}
