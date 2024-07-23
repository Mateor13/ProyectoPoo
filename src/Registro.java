import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame {
    private JTextField nombre;
    private JTextField apellido;
    private JTextField ci;
    private JTextField celular;
    private JTextField email;
    private JTextField nacimiento;
    private JButton registrarButton;
    private JButton cancelarButton;
    private JPanel reg;
    private JTextField clave;
    private JLabel ver;

    public Registro() {
        setTitle("Registro");
        setContentPane(reg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 700));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuarios us = new Usuarios();
                us.setNombre(nombre.getText());
                us.setApellido(apellido.getText());
                us.setEmail(email.getText());
                us.setCedula(ci.getText());
                us.setTelefono(celular.getText());
                us.setFechaNacimiento(nacimiento.getText());
                us.setClave(clave.getText());

                if (us.getNombre().isEmpty() || us.getApellido().isEmpty() || us.getEmail().isEmpty() || us.getCedula().isEmpty() || us.getTelefono().isEmpty() || us.getFechaNacimiento().isEmpty() || us.getClave().isEmpty()) {
                    ver.setText("Hay campos vacíos. Ingrese todos los campos");
                } else {
                    try (MongoClient moncli = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                        MongoDatabase db = moncli.getDatabase("futbolito");
                        MongoCollection<Document> col = db.getCollection("Usuarios");
                        Document doc = new Document("cedula", us.getCedula());
                        FindIterable<Document> iterable = col.find(doc);

                        boolean userExists = false;
                        for (Document documents : iterable) {
                            String cedula = documents.getString("cedula");
                            if (us.getCedula().equals(cedula)) {
                                ver.setText("Usuario existente");
                                userExists = true;
                                break;
                            }
                        }

                        if (!userExists) {
                            Document docu = new Document("nombre", us.getNombre())
                                    .append("apellido", us.getApellido())
                                    .append("cedula", us.getCedula())
                                    .append("correo", us.getEmail())
                                    .append("celular", us.getTelefono())
                                    .append("nacimiento", us.getFechaNacimiento())
                                    .append("clave", us.getClave());
                            col.insertOne(docu);
                            ver.setText("Usuario registrado");
                        }
                    } catch (Exception ex) {
                        ver.setText("Error al conectar con la base de datos");
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
