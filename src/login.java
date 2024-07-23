import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JFrame {
    private JTextField correo;
    private JPasswordField clave;
    private JButton ingresarButton;
    private JLabel Registro;
    private JPanel log;
    private JLabel ver;

    public login() {
        setTitle("Inicio de sesión");
        setContentPane(log);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ver.setText("");
                Usuarios usuario = new Usuarios();
                usuario.setEmail(correo.getText());
                usuario.setClave(new String(clave.getPassword()));

                if (usuario.getEmail().isEmpty() || usuario.getClave().isEmpty()) {
                    ver.setText("Hay datos vacíos. Ingrese todos los campos");
                } else {
                    try (MongoClient moncli = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                        MongoDatabase db = moncli.getDatabase("futbolito");
                        MongoCollection<Document> col = db.getCollection("Usuarios");
                        Document doc = new Document("correo", usuario.getEmail());
                        FindIterable<Document> iterable = col.find(doc);

                        boolean credencialesCorrectas = false;
                        for (Document document : iterable) {
                            String correo1 = document.getString("correo");
                            String clave1 = document.getString("clave");
                            if (usuario.getEmail().equals(correo1) && usuario.getClave().equals(clave1)) {
                                credencialesCorrectas = true;
                                break;
                            }
                        }

                        if (credencialesCorrectas) {
                            ver.setText("Verificación exitosa");
                            new inicio();
                            dispose();
                        } else {
                            ver.setText("Credenciales incorrectas");
                        }

                    } catch (Exception ex) {
                        ver.setText("Error al conectar con la base de datos");
                        ex.printStackTrace();
                    }
                }
            }
        });
        Registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Registro();
                dispose();
            }
        });
        Registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Registro.setForeground(Color.blue);
            }
        });

        Registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Registro.setForeground(Color.cyan);
            }
        });
    }
}
