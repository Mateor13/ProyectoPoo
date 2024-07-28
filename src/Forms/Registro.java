package Forms;

import Clases.Usuarios;
import com.mongodb.client.*;
import com.toedter.calendar.JDateChooser;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class Registro extends JFrame {
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

    public Registro() {
        setIconImage(new ImageIcon(getClass().getResource("../icono/User.png")).getImage());
        setTitle("Registro");
        setContentPane(reg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 700));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuarios us = new Usuarios();
                us.setNombre(nombre.getText());
                us.setApellido(apellido.getText());
                us.setEmail(email.getText().trim()); //Omite los espacios al inicio o al final
                us.setCedula(ci.getText());
                us.setTelefono(celular.getText());

                Date fechaSeleccionada = fecha.getDate();
                if (fechaSeleccionada != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaSeleccionada);
                    us.setDiaNacimiento(cal.get(Calendar.DAY_OF_MONTH));
                    us.setMesNacimiento(cal.get(Calendar.MONTH) + 1); // Los meses son 0-indexados
                    us.setAnoNacimiento(cal.get(Calendar.YEAR));
                }

                us.setClave(clave.getText());
                us.setClaveconf(claveconf.getText());

                if (us.getNombre().isEmpty() || us.getApellido().isEmpty() || us.getEmail().isEmpty() || us.getCedula().isEmpty() || us.getTelefono().isEmpty() || fechaSeleccionada == null || us.getClave().isEmpty()) {
                    ver.setText("Hay campos vacíos. Ingrese todos los campos");
                } else if (!us.getClave().equals(us.getClaveconf())) {
                    ver.setText("Las contraseñas no están iguales");
                } else if (!us.verNume() || !us.verCedula()) {
                    ver.setText("La cédula o el teléfono son incorrectos");
                } else if (fechaSeleccionada.after(new Date())) {
                    ver.setText("La fecha de nacimiento no puede ser mayor a la actual");
                }else if (!us.verCorreo()){
                    ver.setText("El email es incorrecto");
                } else {
                    try (MongoClient moncli = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                        MongoDatabase db = moncli.getDatabase("futbolito");
                        MongoCollection<Document> col = db.getCollection("Usuarios");
                        Document doc = new Document("cedula", us.getCedula());
                        FindIterable<Document> iterable = col.find(doc);

                        boolean userExists = false;
                        if (us.getEmail().equals("Admin1234") || us.getEmail().equals("Duenio1234")) {
                            ver.setText("Usuario existente");
                            userExists = true;
                        } else {
                            for (Document documents : iterable) {
                                String cedula = documents.getString("cedula");
                                String email = documents.getString("email");
                                if (us.getCedula().equals(cedula) || us.getEmail().equals(email)) {
                                    ver.setText("Usuario existente");
                                    userExists = true;
                                    break;
                                }
                            }
                        }

                        if (!userExists) {
                            Document docu = new Document("nombre", us.getNombre())
                                    .append("apellido", us.getApellido())
                                    .append("cedula", us.getCedula())
                                    .append("correo", us.getEmail())
                                    .append("celular", us.getTelefono())
                                    .append("fechaNacimiento", us.getDiaNacimiento()+"/"+us.getMesNacimiento()+"/"+us.getAnoNacimiento())
                                    .append("clave", us.generateHash());
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

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
    }

    private void createUIComponents() {
        fecha = new JDateChooser(); // Inicialización del JDateChooser
    }
}
